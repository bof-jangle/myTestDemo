package xyz.jangle.file.test;

import java.io.File;
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
	}
	
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
			out.write((" 测试文本内容的输出2").getBytes());
			out.flush();
			out.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println("ok");
	}

}
