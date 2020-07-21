package xyz.jangle.file.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 
 * 	使用内存映射进行文件输出。
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年7月19日 上午10:40:26
 * 
 */
public class ByteArrayOutputStreamTest {

	public static void main(String[] args) throws IOException {
		
		// 创建内存映射对象缓存
		ByteArrayOutputStream memoryArray = new ByteArrayOutputStream(128);
		byte[] bytes = "123".getBytes();
		// 写入数据
		memoryArray.write(bytes);
		// 创建路径
		Path path = Paths.get("c://","d","ByteArrayOutputStreamTest");
		File dir = path.toFile();
		if(!dir.exists()) {
			dir.mkdirs();
		}
		// 创建文件、创建文件输出流
		File file = new File(path.toString()+"/test.txt");
		OutputStream out = new FileOutputStream(file);
		// 将内存映射推送至指定的输出流。
		memoryArray.writeTo(out);
		out.flush();
		out.close();
		memoryArray.close();

	}

}
