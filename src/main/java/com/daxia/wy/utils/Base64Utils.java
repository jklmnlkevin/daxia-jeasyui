package com.daxia.wy.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;
import org.springframework.security.crypto.codec.Base64;

public class Base64Utils {
	private static Logger logger = Logger.getLogger(Base64Utils.class);

	public static void main(String[] args) throws Exception {
		String base64 = "3470AFBC71ED6301CF646DBB862549CC "; //args[0];
		String result = fromBase64(base64);
		System.out.println(result);
	}

	public static String fromBase64(String base64) {
		try {
			byte[] data = Base64.decode(base64.getBytes("utf-8"));
			return new String(data, "utf-8");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public static String toBase64(String str) {
		try {
			byte[] data = Base64.encode(str.getBytes("gbk"));
			return new String(data, "gbk");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	public static String toBase642(String str) {
		try {
			byte[] data = Base64.encode(str.getBytes("utf-8"));
			return new String(data, "utf-8");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	/**
	 * 从文件生成base64的字符串形式
	 * 
	 * @param file
	 * @return
	 */
	public static String fromFileToString(File file) {
		InputStream in = null;
		byte[] data = null;
		try {
			in = new FileInputStream(file);
			data = new byte[in.available()];
			in.read(data);
			in.close();
			byte[] result = Base64.encode(data);
			return new String(result, "utf-8");
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static boolean fromStrToFile(String str, File outputFile) { // 对字节数组字符串进行Base64解码并生成图片
		if (str == null) {
			return false;
		}

		try {
			byte[] b = Base64.decode(str.getBytes("utf-8"));
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			OutputStream out = new FileOutputStream(outputFile);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			logger.error(e, e);
			return false;
		}
	}
}
