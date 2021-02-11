package xyz.jangle.javabase.n2.b1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *  2.1、输入输出流
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2021年2月10日 下午2:44:10
 * 
 */
public class M {

	public static void main(String[] args) throws IOException {
		
		Path path = Paths.get("这里是文件的路径");
		Path pathOut = Paths.get("这里是输出文件的路径");
		InputStream in = Files.newInputStream(path);
		// 1、从数据中读取1个字节，并返回该字节。 ps:方法碰到流结尾时返回-1。
		int readByte = in.read();
		// 2、从数据中读取100个字节（readBytes.length字节），返回实际读入的字节数。
		//（ps：若文件实际只有50字节，则返回50。若实际有150字节，则只读入100字节，返回100。
		byte[] readBytes = new byte[100];
		int returnNum = in.read(readBytes);
		// 3、通常，上述方法这样使用： in.availabel() 方法返回在不阻塞的情况下可获取的字节数
		byte[] readBytes2 = new byte[in.available()];
		int returnNum2 = in.read(readBytes2);
		// 4、如果未阻塞(read)，则读入10字节，读入的值将放置readBytes2的off开始位置。
		// 返回实际读入的字节数，或者结尾时返回-1。
		int size = 0;
		int off = 0;
		for(;;) {
			size = in.read(readBytes2, off, 10);
			if(size == -1)break;
			off += size;
		}
		// *5、产生一个数组，包含可以从当前流中读入的所有字节。
		byte[] readAllBytes = in.readAllBytes();
		// *6、将当前输入流中的所有字节传送到给定的输出流，返回传递的字节数。
		OutputStream out = Files.newOutputStream(pathOut);
		in.transferTo(out);
		
		// 1、查看这个流是否支持标记功能
		boolean markSupported = in.markSupported();
		int readlimit = 10;
		// 2、在流的当前readlimit位置打一个标记
		in.mark(readlimit);
		// 3、返回到最后一个标记
		in.reset();
		
		// 1、写出一个字节的数据
		int n =in.read();
		out.write(n);
		// 2、从数组b中，写出b.length字节的数据到输出流中。
		byte[] b = in.readAllBytes();
		out.write(b);
		out.write(b, 0, b.length);
		
		// last、注意关闭流
		in.close();
		// 冲刷输出流
		out.flush();
		out.close();
		

	}

}
