package com.demo.rsa.BigDataTest.httpclient;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;


public class HTTPSend {

	public static String sendGet(String url, List<HTTPParam> list) throws IOException {
		StringBuffer buffer = new StringBuffer(); // ����ƴ�Ӳ���
		StringBuffer result = new StringBuffer(); // �������ܷ���ֵ
		URL httpUrl = null;
		URLConnection connection = null; // ������http����
		BufferedReader bufferedReader = null; // ���������ܵĲ���

		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				buffer.append(list.get(i).getKey()).append("=")
						.append(URLEncoder.encode(list.get(i).getValue(), "utf-8"));

				if ((i + 1) < list.size()) {
					buffer.append("&");
				}
			}
			url = url + "?" + buffer.toString();
		}
		// ����URL
		httpUrl = new URL(url);
		// ��������
		connection = httpUrl.openConnection();
		connection.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		connection.setRequestProperty("connection", "keep-alive");
		connection.setRequestProperty("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0");
		connection.connect();
		// �������ӷ��ز���
		bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			result.append(line);
		}
		bufferedReader.close();
		return result.toString();
	}

	/**
	 * ����Post����
	 *
	 * @param url
	 *            ������?
	 * @param list
	 *            �������?
	 *
	 * @return ������
	 *
	 * @throws IOException
	 */
	public static String sendPost(String url, List<HTTPParam> list) throws IOException {
		StringBuffer buffer = new StringBuffer(); // ����ƴ�Ӳ���
		StringBuffer result = new StringBuffer(); // �������ܷ���ֵ
		URL httpUrl = null; // HTTP URL�� �����������������?
		URLConnection connection = null; // ������http����
		PrintWriter printWriter = null;
		BufferedReader bufferedReader; // ���������ܵĲ���
		// ����URL
		httpUrl = new URL(url);
		// ��������
		connection = httpUrl.openConnection();
		connection.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		connection.setRequestProperty("connection", "keep-alive");
		// connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT
		// 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0");
		connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		connection.setDoOutput(true);

		connection.setDoInput(true);
		printWriter = new PrintWriter(connection.getOutputStream());
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				buffer.append(list.get(i).getKey()).append("=")
						.append(URLEncoder.encode(list.get(i).getValue(), "utf-8"));

				if ((i + 1) < list.size()) {
					buffer.append("&");
				}
			}
		}
		printWriter.print(buffer.toString());
		printWriter.flush();
		connection.connect();
		// �������ӷ��ز���
		bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			result.append(line);
		}
		bufferedReader.close();
		return result.toString();
	}

	public static String HtmlGet(String url, String param) throws IOException {
		String result = "";
		BufferedReader in = null;
		String urlName = url + param;
		URL realUrl = new URL(urlName);
		URLConnection conn = realUrl.openConnection();
		conn.setRequestProperty("accept", "*/*");
		conn.setRequestProperty("connection", "keep-Alive");
		conn.setRequestProperty("user-agent", "Mozilla/4.0(compatible;MSIE 6.0;Window NT 5.1;SV1)");
		conn.connect();
		in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		String line;
		while ((line = in.readLine()) != null) {
			result += "\n" + line;
		}
		return result;

	}

	/**
	 * 
	 * @Title: setGET @Description: 爬去网页 path 网页url 返回类型 网页数据
	 */
	public static String setGET(String path) throws IOException {
		URL url = new URL(path);
		String str = "";
		try {
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestProperty("contentType", "UTF-8");
			conn.setConnectTimeout(5 * 10000);
			conn.setRequestMethod("GET");

			InputStream inStream = conn.getInputStream();

			BufferedReader in = new BufferedReader(new InputStreamReader(inStream, "UTF-8"));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = in.readLine()) != null) {
				buffer.append(line);
				str = buffer.toString();
			}
		} catch (Exception ex) {

		}

		return str;
	}

	public static String GetHttp(String url, List<HTTPParam> list) throws IOException {
		StringBuffer buffer = new StringBuffer();
		StringBuffer result = new StringBuffer();
		URL httpUrl = null;
		URLConnection connection = null;
		BufferedReader bufferedReader = null;

		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				buffer.append(list.get(i).getKey()).append("=")
						.append(URLEncoder.encode(list.get(i).getValue(), "utf-8"));

				if ((i + 1) < list.size()) {
					buffer.append("&");
				}
			}
			url = url + "?" + buffer.toString();
		}

		httpUrl = new URL(url);

		connection = httpUrl.openConnection();
		connection.setRequestProperty("Authorization",
				"");
		connection.setRequestProperty("Content-Type", "application");
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("User-Agent", "Mozilla/4.0(compatible;MSIE 6.0;Window NT 5.1;SV1)");
		connection.connect();
		// �������ӷ��ز���
		bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			result.append(line);
		}
		bufferedReader.close();
		return result.toString();
	}

	public static String setPost(String path) throws IOException {
		URL url = new URL(path);
		String str = "";
		try {
			Long start = System.currentTimeMillis();
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestProperty("contentType", "application/x-www-form-urlencoded;charset=utf-8");
			conn.setConnectTimeout(5 * 10000);
			conn.setRequestMethod("POST");

			InputStream inStream = conn.getInputStream();

			BufferedReader in = new BufferedReader(new InputStreamReader(inStream, "UTF-8"));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = in.readLine()) != null) {
				buffer.append(line);
				str = buffer.toString();
			}
			
			Long end = System.currentTimeMillis();
			
			
			//System.out.println("����һ�η�������ʱ��"+(end-start));
		} catch (Exception ex) {

		}

		return str;
	}

}
