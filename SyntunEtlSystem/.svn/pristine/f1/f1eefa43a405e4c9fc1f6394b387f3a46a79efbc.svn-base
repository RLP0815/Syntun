package com.syntun.etl.tools;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class FileTools {
	@SuppressWarnings("deprecation")
	public static void deleteFile(String outPut,Configuration conf ) throws IOException {
		FileSystem hdfs = FileSystem.get(conf);
		Path path = new Path(outPut);
		System.out.println(hdfs.delete(path));
		hdfs.close();
	}

}
