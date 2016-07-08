package com.xie.day10_listviewpage_internet.utils;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * ��������Ĺ�����
 * 
 * @author Administrator
 * 
 */
public class HttpUtils {
	/**
	 * ���������ȡjson�ַ���
	 * 
	 * @param path
	 *            ��ǰ���ʵ������ַ
	 * @return �õ�json�ַ���
	 */
	public static String getHttpResult(String path) {
		String jsonString = "";
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(path);
		try {
			HttpResponse httpResponse = httpClient.execute(httpGet);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				jsonString = EntityUtils.toString(httpResponse.getEntity());
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonString;
	}
}