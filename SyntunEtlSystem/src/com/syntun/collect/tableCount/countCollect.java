package com.syntun.collect.tableCount;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
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

import com.syntun.etl.tools.BaseDao;
import com.syntun.etl.tools.ConvertTools;
import com.syntun.etl.tools.FileTools;
import com.syntun.etl.tools.SyntunDate;
/*
 * 业务说明：
 * 	1、统计当天的采集数据条数；
 * 	2、表格唯一键存放在132数据库中
 * 	3、若果传入参数日期，则统计这天数据；若果不传入日期参数，则执行当天的前一天的数据；
 */
public class countCollect extends Configured implements Tool {
	public static void main(String[] args) throws Exception {
		int res = ToolRunner.run(new Configuration(), new countCollect(), args);
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
//			System.out.println("Please tell me  input and output filterTabelName(opetion)");
//			System.exit(0);
			Date day = new Date();    
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String dateStr = df.format(day);
			arg0 = new String[]{SyntunDate.jieDate(dateStr)};
			//arg0 = new String[]{dateStr};
		}
		System.out.println(arg0[0]);
		
		Configuration conf = getConf();
		conf.set("mapreduce.job.maps", "1");
		conf.set("mapreduce.job.reduces", "50");
		conf.set("yarn.nodemanager.pmem-check-enabled", "false");
		conf.set("yarn.nodemanager.vmem-check-enabled", "false");
		conf.set("yarn.nodemanager.vmem-pmem-ratio", "4");
		conf.set("mapreduce.reduce.memory.mb", "16384");
		conf.set("mapreduce.reduce.java.opts", "-Xmx16384M");
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
		String input = "/backup/jiadian_data_init_clear/" + beginEnd + "*";
		String output = "/tmp/TableCountList";
		FileTools.deleteFile(output,conf);
		
		HashMap<String, String> filter = new HashMap<String, String>();
        filter.put("get_date", beginEnd);
        BaseDao.deleteTable("xitong.table_count_list", filter);
		
		conf.set("date", beginEnd);// 读取天猫下架表的字段列表
		HashMap<String, String> uniqueKey = BaseDao.getTbaleUniqueMap("table_unique_key");
		conf.set("uniqueKey", checkXmlChar(ConvertTools.convertMapToMaohaoStr(uniqueKey)));

		conf.setBoolean("mapred.output.compress", true);// 配置方式
		conf.setClass("mapred.output.compression.codec", GzipCodec.class, CompressionCodec.class);// 配置方式

		Job job = new Job(conf, beginEnd+"tableCountList job");
		job.setJarByClass(countCollect.class);
		job.setMapperClass(countMap.class);
		job.setReducerClass(countReduce.class);

		CombineFileInputFormat.addInputPath(job, new Path(input));
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