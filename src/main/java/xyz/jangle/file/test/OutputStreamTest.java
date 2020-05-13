package xyz.jangle.file.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

/** 
* @author jangle E-mail: jangle@jangle.xyz
* @version 2018年10月21日 上午10:08:11 
* 类说明 
*/
public class OutputStreamTest {
	
	private static String path = "d:/logs/test/a/a/";
	
	private static String fileNameWriter = "JangleTestWriter.txt";

	public static void main(String[] args) {
//		testOutput();
//		testInsertFile();
		testWriter();
		testFilesCopy();
	}
	
	/**
	 * 对Java 7 的NIO的使用Demo
	 */
	private static void testFilesCopy() {
		Path pathFrom = Paths.get(path,fileNameWriter);								// 1.1创建一个Path源
//		Path pathFrom = Paths.get("d:","logs","test","a","a",fileNameWriter);		// 创建一个Path,这种方式跟上面的方式等同。
		Path pathTo = Paths.get(path, "2"+fileNameWriter);	// 1.2创建一个输出Path目标
		try {
			Files.copy(pathFrom, pathTo,StandardCopyOption.REPLACE_EXISTING);	//1.3使用NIO复制文件， Option参数含义：若存在则替换
			List<String> lines = Files.readAllLines(pathTo);		// 2.1获取目标文件文本内容的所有行。（使用NIO直接获取文本内容，并输出）
			System.out.print("pathTo  的文件内容：");
			for(String line:lines) {
				System.out.println(line);	//2.2输出目标文件所有行内容
			}
			InputStream inputStream = Files.newInputStream(pathFrom);		//3.1通过路径获取源文件 输入流 （使用NIO获取输入流，再进行内容获取，并输出。）
			byte[] b = new byte[inputStream.available()];
			inputStream.read(b);	// 3.2通过输入流，将字节读入字节数组中。
			System.out.print("pathFrom的文件内容：");
			System.out.println(new String(b,StandardCharsets.UTF_8));		//3.3将原本的文本字节进行编码成字符串输出。
			System.out.println("1.5的目标文件内容和2.3的源文件的内容一致。 复制是成功的。");
			
			Files.delete(pathTo);	//4.1使用NIO删除目标文件
			boolean existsPathTo = pathTo.toFile().exists();	//4.2将Path转换为File对象，并判断是否存在文件。
			System.out.println("执行删除后，pathTo对应的文件是否存在："+existsPathTo);
			Files.move(pathFrom, pathTo);	//4.3使用NIO将文件进行移动
			System.out.println("执行移动后，pathTo  对应的文件是否存在："+pathTo.toFile().exists());
			System.out.println("执行移动后，pathFrom对应的文件是否存在："+pathTo.toFile().exists());
			Files.move(pathTo, pathFrom);	//移动回来
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 使用Writer进行文本内容输入。
	 */
	private static void testWriter() {
		File filePath = new File(path);
		if(!filePath.exists()) {
			filePath.mkdirs();
		}
		File file = new File(path+fileNameWriter);
		try {
			FileOutputStream out = new FileOutputStream(file,true);	//第二个参数表示是否添加
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(out,StandardCharsets.UTF_8);
			PrintWriter printWriter = new PrintWriter(outputStreamWriter);
			printWriter.print("欢迎访问http://jangle.xyz");
			printWriter.flush();
			printWriter.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println("ok"); 
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
