package com.b1b.tc.checker.utils.net;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamRead {
	public static final String DEF_CHARSET = "utf-8";
	public static final String tokenPath = "d:/dyj/wx_token.txt";

	public static String readFrom(InputStream in) throws IOException {
		return readFrom(in, DEF_CHARSET);
	}

	public static String readFrom(InputStream in, String charSet) throws IOException {
		StringBuilder sb = new StringBuilder();
//		byte[] b = new byte[1024];
//		int len = 0;
//		while ((len = in.read(b)) != -1) {
//			sb.append(new String(b, 0, len, charSet));
//		}
		String temps = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(in, charSet));
		while ((temps = reader.readLine()) != null) {
			sb.append(temps);
			sb.append("\n");
		}
		if(sb.toString().endsWith("\n")){
			sb.deleteCharAt(sb.length()-1);
		}
		return sb.toString();
	}

	public static String readFromFile(String filePath) throws IOException {
		FileInputStream in = new FileInputStream(filePath);
		return readFrom(in);
	}

	private static void writeToFile(String filePath, String contents, String charSet)
			throws IOException {
		FileOutputStream fis = new FileOutputStream(filePath);
		fis.write(contents.getBytes(charSet));
		fis.close();
	}

	public static void writeToFile(String filePath, String contents) throws IOException {
		writeToFile(filePath, contents, DEF_CHARSET);
	}

	public synchronized static void writeTokenToFile(String token) throws IOException {
		writeToFile(tokenPath, token);
	}

	public synchronized static String readTokenFromFile() throws IOException {
		File file = new File(tokenPath);
		if (!file.exists()) {
			return "";
		}
		InputStream in = new FileInputStream(tokenPath);
		return readFrom(in);
	}
}
