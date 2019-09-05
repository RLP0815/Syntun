package com.syntun.webget;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import com.syntun.webget.ResolveWebPage;
import com.syntun.webget.UrlSupervise;

/**
 * 基于hadoop2.4的插入类
 * 
 * @author tuo
 * 
 */
public class FileToHadoop24 {
	public boolean fileToDb(ContentFile contentFile) {
		return filetoHadoop(contentFile.getFileName(), contentFile.getFileName());
	}

	private static String MASTER_URI = "hdfs://hadoop100:8020";

	static Configuration conf = new Configuration();
	static {
		conf.set("fs.defaultFS", "hdfs://hadoop100:8020");
		conf.set("dfs.client.failover.proxy.provider.myhadoop",
				"org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");
	}

	public boolean filetoHadoop(String localFile, String upFile) {
		if (UrlSupervise.isFirst == 0 || UrlSupervise.isUpload == 0) {
			return true;
		}
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(localFile));
			upFile = "/" + ResolveWebPage.upFile + localFile.substring(localFile.indexOf("/20"), localFile.length());
			FileSystem fs = FileSystem.get(URI.create(upFile), conf);
			OutputStream out = fs.create(new Path(upFile));
			IOUtils.copyBytes(in, out, 4068, true);
			System.out.println("success");
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 查看目录下面的文件
	 * 
	 * @param conf
	 * @param uri
	 * @param folder
	 * @throws IOException
	 */
	public static void ls(Configuration conf, String uri, String folder, String likeFile, String localFile)
			throws IOException {
		Path path = new Path(folder);
		FileSystem fs = FileSystem.get(URI.create(uri), conf);
		FileStatus[] list = fs.listStatus(path);
		System.out.println("ls: " + folder);
		System.out.println("==========================================================");
		for (FileStatus f : list) {
			System.out.printf("name: %s, folder: %s, size: %d\n", f.getPath(), f.isDirectory(), f.getLen());
			System.out.println(f.getPath().getName());
			FileStatus[] list1 = fs.listStatus(f.getPath());
			if (!f.getPath().getName().contains(likeFile)) {
				continue;
			}
			File folderResult = new File(localFile + folder + "/" + f.getPath().getName());
			if (!folderResult.exists()) {
				folderResult.mkdir();
			}
			for (FileStatus f1 : list1) {
				System.out.printf("name: %s, folder: %s, size: %d\n", f1.getPath(), f1.isDirectory(), f1.getLen());
				String hadoopFile = folder + "/" + f.getPath().getName() + "/" + f1.getPath().getName();
				String local = localFile + folder + "/" + f.getPath().getName() + "/" + f1.getPath().getName();
				testDownload(hadoopFile, local);
			}
			System.out.println();
		}
		System.out.println("==========================================================");
		fs.close();
	}

	/**
	 * 测试下载文件
	 * 
	 * @throws Exception
	 */
	public static void testDownload(String hadoopFile, String localFile) {
		Configuration conf = new Configuration();
		FileSystem fs;
		try {
			System.out.println(localFile);
			fs = FileSystem.get(new URI(MASTER_URI), conf);
			InputStream in = fs.open(new Path(hadoopFile));
			OutputStream out = new FileOutputStream(localFile);
			IOUtils.copyBytes(in, out, conf);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (URISyntaxException e) {
			System.out.println(e.getMessage());
		}

	}

	public static void main(String[] args) {
		try {
			String localFolder = args[0];
			String likeFile = args[1];
			String hadoopFile = args[2];
			ls(conf, MASTER_URI, hadoopFile, likeFile, localFolder);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
