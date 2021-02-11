package xyz.jangle.javabase.n2.b1;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PushbackInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 2.1.3、组合输入/输出流过滤器
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2021年2月11日 上午9:24:17
 * 
 */
public class M3 {
	
	public static String testFile = "test.txt";
	
	public static String testPathFile = M3.class.getResource("/").getPath()+testFile;
	
	public static String pathFileStr = testPathFile.substring(1);
	

	public static void main(String[] args) throws IOException {
		
		
		System.out.println(M3.class.getResource("/").getPath());
		System.out.println(Paths.get(new File("").getAbsolutePath()));
		System.out.println(testPathFile);
		System.out.println(pathFileStr);
		// 1、将FileInputStream构造的对象传递给DateInputStream对象，
		//	如此来从文件中读取数字（等Java基本数据类型）
		var fin = new FileInputStream(M3.class.getResource("/").getPath() +testFile);
		var din = new DataInputStream(fin);
//		double readDouble = din.readDouble();
//		System.out.println(readDouble);
//		var dout = new DataOutputStream(Files.newOutputStream(Paths.get(M3.class.getResource("/").getPath()+testFile)));
		var dout = new DataOutputStream(new FileOutputStream(M3.class.getResource("/").getPath()+testFile));
		dout.writeInt(1);
		dout.writeInt(1);
		dout.writeInt(1);
		dout.writeInt(1);
		dout.writeInt(1);
		dout.writeInt(1);
		dout.close();
		
		System.out.println(din.readInt());
		din.close();
		
		// 2、使用缓冲区，每次从操作系统中获取缓冲区大小的数据（否则如1则每次只获取1字节）
		// 如下则可以使用带缓冲机制的read方法。
		var din2 = new DataInputStream(new BufferedInputStream(Files.newInputStream(Paths.get(pathFileStr))));
		double readDouble2 = din2.readDouble();
		System.out.println(readDouble2);
		din2.close();
		// 3、可回推输入流（当你预读一个字节，不符合你的期望时，可以对其进行推回。）
		var pbin = new PushbackInputStream(new BufferedInputStream(Files.newInputStream(Paths.get(pathFileStr))));
		int read = pbin.read();
		System.out.println("read:"+read);
		if(read != 'a')pbin.unread(read);
		// 当你希望预览数字时，再嵌套一层数据输入流
		var din3 = new DataInputStream(pbin);
		int readInt = din3.readInt();
		System.out.println("readInt:"+readInt);
		if(readInt!= 3)pbin.unread(readInt);
		din3.close();

	}

}
