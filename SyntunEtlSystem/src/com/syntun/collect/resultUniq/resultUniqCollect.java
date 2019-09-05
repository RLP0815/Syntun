package com.syntun.collect.resultUniq;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.GzipCodec;
import org.apache.hadoop.mapred.lib.CombineFileInputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.syntun.etl.tools.FileTools;
import com.syntun.etl.tools.SyntunDate;

public class resultUniqCollect extends Configured implements Tool {
	public static void main(String[] args) throws Exception {
		int res = ToolRunner.run(new Configuration(), new resultUniqCollect(), args);
		System.exit(res);
	}

	/**
	 * @see http://www.w3.org/TR/2004/REC-xml-20040204/#charsets All supported
	 *      characters
	 * @param data
	 *            content in each field
	 * @return regular content is filtered from illegal XML char
	 */
	public static String checkXmlChar(String data) {
		StringBuffer appender = new StringBuffer("");
		if (StringUtils.isNotBlank(data)) {
			appender = new StringBuffer(data.length());

			for (int i = 0; i < data.length(); i++) {
				char ch = data.charAt(i);
				if ((ch == 0x9) || (ch == 0xA) || (ch == 0xD) || ((ch >= 0x20) && (ch <= 0xD7FF))
						|| ((ch >= 0xE000) && (ch <= 0xFFFD)) || ((ch >= 0x10000) && (ch <= 0x10FFFF)))
					appender.append(ch);
			}
		}
		String result = appender.toString();
		return result.replaceAll("]]>", "");
	}

	@SuppressWarnings("deprecation")
	@Override
	public int run(String[] arg0) throws Exception {
		if (arg0.length < 1) {
			System.out.println("Please tell me the correct parameters");
			System.exit(0);
		}
		Configuration conf = getConf();
		conf.set("mapreduce.job.maps", "1");
		conf.set("mapreduce.job.reduces", "150");
		conf.set("yarn.nodemanager.pmem-check-enabled", "false");
		conf.set("yarn.nodemanager.vmem-check-enabled", "false");
		conf.set("yarn.nodemanager.vmem-pmem-ratio", "4");
		conf.set("mapreduce.reduce.memory.mb", "20480");//4096、8192
		conf.set("mapreduce.reduce.java.opts", "-Xmx20480M");
		// map 阶段缓存大小，默认100
		conf.set("io.sort.mb", "256");
		// 当map的中间结果非常大，调大它，有利于减少merge次数，进而减少map对磁盘的读写频率，默认10
		conf.set("io.sort.factor", "20");
		// 读写文件的buffer设置
		conf.set("io.file.buffer.size", "262144");
		// reduce 阶段，同时从map结果下载数据的线程数，默认是5
		conf.set("mapreduce.reduce.shuffle.parallelcopies", "20");
		conf.set("mapreduce.task.timeout", "6000000");
		// conf.set("mapreduce.input.fileinputformat.split.minsize",
		// "268435456");
		conf.set("mapreduce.input.fileinputformat.split.minsize", "536870912");
		// 重用JVM
		conf.set("mapreduce.job.jvm.numtasks", "-1");
		conf.set("mapreduce.map.java.opts", "-Xmx3072M");

		String beginEnd = arg0[0];
		if (beginEnd.indexOf("_") == -1 && beginEnd.split("_").length != 2) {
			System.out.println("Please tell me the correct parameters");
			System.exit(0);
		}
		//String start = beginEnd.split("_")[0];
		String end = beginEnd.split("_")[1];
		String Edate = SyntunDate.nowJieDate("7", end);
		//String input = "/tmp/SyntunCommentResultOut/Uniq/2014-10-01_2018-12-02/part-r-00"+ beginEnd +".gz";
		//String input = "/tmp/SyntunCommentResultOut/Uniq/2014-10-01_2018-12-02/";
		String input1 = "/tmp/SyntunCommentResultOut/48W/";
		String input2 = "/tmp/SyntunCommentResultOut/UniqCommentNo/";
		String output = "/tmp/SyntunCommentResultOut/UniqCommentNo/"+ "2014-10-01_"+end;
		FileTools.deleteFile(output,conf);
		
		conf.setBoolean("mapred.output.compress", true);// 配置方式
		conf.setClass("mapred.output.compression.codec", GzipCodec.class, CompressionCodec.class);// 配置方式

		Job job = new Job(conf, "commentResultUniq job");
		job.setJarByClass(resultUniqCollect.class);
		job.setMapperClass(CollectMap.class);
		job.setReducerClass(CollectReduce.class);
		
		FileSystem hdfs = null;
		try {
			hdfs = FileSystem.get(conf);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String st = input2 + "2014-10-01_"+Edate+"/";
		Path pt = new Path(st);
		if (hdfs.listStatus(pt).length != 0) {
			CombineFileInputFormat.addInputPath(job, new Path(st));
		}else {
			CombineFileInputFormat.addInputPath(job, new Path(input2 + "2014-10-01_2018-12-02/"));
		}
		CombineFileInputFormat.addInputPath(job, new Path(input1+ beginEnd + "/*"));
		//CombineFileInputFormat.addInputPath(job, new Path(input));
		
		FileOutputFormat.setOutputPath(job, new Path(output));
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		try {
			System.exit(job.waitForCompletion(true) ? 0 : 1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return 1;
	}
}