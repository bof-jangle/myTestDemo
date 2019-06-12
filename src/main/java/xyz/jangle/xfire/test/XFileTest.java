package xyz.jangle.xfire.test;

import java.net.URL;

import org.codehaus.xfire.client.Client;

public class XFileTest {

	public static void main(String[] args) {
		try {
			Client client = new Client(new URL("http://172.23.31.240//services/UtilForSXInter?wsdl"));
			Object[] res = client.invoke("getFileFromOAForXml", new String[]{"suK74bDso6iy4srU08OjqQ==","2018-01-01 14:00:00","2019-12-31 15:00:00","2"});
			//invoke方法有两个参数，一个是接口的方法名，一个是接口方法的参数列表
			String re = (String) res[0];
			System.out.println(re);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
