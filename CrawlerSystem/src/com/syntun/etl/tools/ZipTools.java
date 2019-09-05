package com.syntun.etl.tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import org.apache.commons.codec.binary.Base64;

public class ZipTools {
	
	public static void main(String[] args) {
		System.out.println(unzip("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;"));
	}
	
	/**
	 * 使用zip进行压缩
	 * @param str
	 *            压缩前的文本
	 * @return 返回压缩后的文本
	 */
	public static final String zip(String str) {
		if (str == null)
			return null;
		byte[] compressed;
		ByteArrayOutputStream out = null;
		ZipOutputStream zout = null;
		String compressedStr = null;
		try {
			out = new ByteArrayOutputStream();
			zout = new ZipOutputStream(out);
			zout.putNextEntry(new ZipEntry("0"));
			zout.write(str.getBytes());
			zout.closeEntry();
			compressed = out.toByteArray();
			compressedStr = new String(new Base64().encode(compressed));
		} catch (IOException e) {
			compressed = null;
		} finally {
			if (zout != null) {
				try {
					zout.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
		return compressedStr;
	}

	/**
	 * 使用zip进行解压缩
	 * 
	 * @param compressed
	 *            压缩后的文本
	 * @return 解压后的字符串
	 */
	public static final String unzip(String compressedStr) {
		if (compressedStr == null) {
			return null;
		}

		ByteArrayOutputStream out = null;
		ByteArrayInputStream in = null;
		ZipInputStream zin = null;
		String decompressed = null;
		try {
			byte[] compressed = new Base64().decode(compressedStr.getBytes());
			out = new ByteArrayOutputStream();
			in = new ByteArrayInputStream(compressed);
			zin = new ZipInputStream(in);
			zin.getNextEntry();
			byte[] buffer = new byte[1024];
			int offset = -1;
			while ((offset = zin.read(buffer)) != -1) {
				out.write(buffer, 0, offset);
			}
			decompressed = out.toString();
		} catch (IOException e) {
			decompressed = null;
		} finally {
			if (zin != null) {
				try {
					zin.close();
				} catch (IOException e) {
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
		return decompressed;
	}
}
