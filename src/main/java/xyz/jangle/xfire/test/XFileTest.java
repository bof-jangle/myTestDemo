package xyz.jangle.xfire.test;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.xfire.client.Client;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class XFileTest {

	public static void main(String[] args) {
		try {
			Client client = new Client(new URL("http://172.23.31.240//services/UtilForSXInter?wsdl"));
//			Object[] res = client.invoke("getFileFromOAForXml", new String[]{"suK74bDso6iy4srU08OjqQ==","2018-01-01 14:00:00","2019-12-31 15:00:00","2"});
			Object[] res = client.invoke("getDetailForXml", new String[]{"suK74bDso6iy4srU08OjqQ==","1776970"});
			//invoke方法有两个参数，一个是接口的方法名，一个是接口方法的参数列表
			String re = (String) res[0];
//			System.out.println(re);
			Map<String, String> fileInfo = getFileInfo(re);
			File outFile = new File("D:/d/"+fileInfo.get("filename"));
			FileOutputStream out = new FileOutputStream(outFile);
			byte[] decoded = Base64.getDecoder().decode(fileInfo.get("file"));
			out.write(decoded);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static Map<String, String> getFileInfo(String fileXml){
		Map<String, String> map = new HashMap<String, String>();
		//创建xml文档对象
		try {
			Document document = DocumentHelper.parseText(fileXml);
			Element rootElement = document.getRootElement();
			Element fileEntities = rootElement.element("fileEntities");
			Element fileEntity = fileEntities.element("fileEntity");
			Element filename = fileEntity.element("filename");
			Element file = fileEntity.element("file");
			System.out.println(file.getText());
			System.out.println(filename.getText());
			map.put("file", file.getText());
			map.put("filename", filename.getText());
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return map;
	}

}
