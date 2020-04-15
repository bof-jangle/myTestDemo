package xyz.jangle.httpclient.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientTest {

	public static void main(String[] args) {
		try {
			httpPost("http://www.jangle.xyz");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void httpPost(String url) throws ClientProtocolException, IOException {

		CloseableHttpClient client = HttpClientBuilder.create().build();

		HttpPost httpPostRequest = new HttpPost(url);
//		post.addHeader(name, value);	// 添加请求头
//		post.addHeader(name, value);	// 添加header

		// 添加参数列表
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("name", "value")); // 添加参数
//		parameters.add(new BasicNameValuePair(name, value));	// 添加参数
		HttpEntity entity = new UrlEncodedFormEntity(parameters, "UTF-8");
		httpPostRequest.setEntity(entity); // 存入参数
		CloseableHttpResponse response = client.execute(httpPostRequest);
		System.out.println(response.getStatusLine()); // 响应状态
		HttpEntity responseEntity = response.getEntity(); // 响应实体
		String string = EntityUtils.toString(responseEntity); // 字符串实体
		System.out.println(string);
		client.close();
		response.close();
	}

}
