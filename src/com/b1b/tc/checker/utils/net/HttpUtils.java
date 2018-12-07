package com.b1b.tc.checker.utils.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;

public class HttpUtils {
	public static String getPostResult(String urlstr, String json) throws IOException {
		return getResult(urlstr, json, "POST");
	}

	public static String getResult(String urlstr, String json, String reqMethod)
			throws IOException {
		URL url = new URL(urlstr);
		if (urlstr.startsWith("https")) {
			SSLContext sc = null;
			try {
				sc = SSLContext.getInstance("TLS");
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			try {
				sc.init(null,
						new TrustManager[] {
								new MySSLProtocolSocketFactory.TrustAnyTrustManager() },
						new SecureRandom());
			} catch (KeyManagementException e) {
				e.printStackTrace();
			}
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {

				@Override
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			});
		}
		HttpURLConnection conns = (HttpURLConnection) url.openConnection();
		conns.setRequestMethod(reqMethod);
		if (json != null) {
			conns.setRequestProperty("Content-Type", "application/json");
			conns.setDoOutput(true);
			OutputStream outputStream = conns.getOutputStream();
			outputStream.write(json.getBytes("utf-8"));
		}
		InputStream in = conns.getInputStream();
		String res = StreamRead.readFrom(in);
		return res;
	}

	public static String getGetResult(String urlstr, String json) throws IOException {
		return getResult(urlstr, json, "GET");
	}
}
