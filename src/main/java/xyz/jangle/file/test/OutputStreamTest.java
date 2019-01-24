package xyz.jangle.file.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/** 
* @author jangle E-mail: jangle@jangle.xyz
* @version 2018年10月21日 上午10:08:11 
* 类说明 
*/
public class OutputStreamTest {

	public static void main(String[] args) {
		testOutput();
		testInsertFile();
	}
	
	/**
	 * 向文件添加内容
	 */
	private static void testOutput() {
		String path = "d:/logs/zjoa/a/a/";
		String fileName = "exchange.txt";
		File filePath = new File(path);
		if(!filePath.exists()) {
			filePath.mkdirs();
		}
		File file = new File(path+fileName);
		try {
			FileOutputStream out = new FileOutputStream(file,true);	//第二个参数表示是否添加
			//不添加第二个参数时候，将对文件进行重写（即默认false）
			out.write((" \n测试文本内容的输出2 \n</beans>").getBytes());
			out.flush();
			out.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println("ok");
	}
	/**
	 * 修改文件内容
	 */
	private static void testInsertFile() {
		String path = "d:/logs/zjoa/a/a/";
		String fileName = "exchange.xml";
		File filePath = new File(path);
		if(!filePath.exists()) {
			filePath.mkdirs();
		}
		File file = new File(path+fileName);
		try {
			FileInputStream in = new FileInputStream(file);
			byte[] b = new byte[in.available()];
			in.read(b);
			in.close();
			String content = new String(b);
			System.out.println(content);
			int indexOf = content.indexOf("</beans>");
			System.out.println(indexOf);
			String insertStr = "		<action name=\"oaDemoAction\" class=\"com.xxxx.action.OaDemoAction\">\r\n" + 
					"			<result name=\"success\" type=\"json\">\r\n" + 
					"				<param name=\"root\">dataMap</param>\r\n" + 
					"			</result>\r\n" + 
					"		</action>";
			insertStr += "\n</beans>";
//			content.replace("</beans>", insertStr);
			String newContent = content.substring(0, indexOf);
			newContent = newContent + insertStr;
			FileOutputStream out = new FileOutputStream(file,false);	//第二个参数表示是否添加
			//不添加第二个参数时候，将对文件进行重写（即默认false）
			out.write(newContent.getBytes());
			out.flush();
			out.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
