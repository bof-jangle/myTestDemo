package xyz.jangle.socket.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 简单的一个socketServer，启动后使用本地电脑执行telnet 127.0.0.1 8189 进行访问。
 * 
 * 通过Telnet客户端输入内容，服务端将客户端输入的内容进行打印。 若客户端输入，Bye, 则客户端与服务器断开连接，服务端也停止服务。
 * 
 * 客户端输入操作步骤： Enter键开始， 键入文本， Enter键发送。 如此循环进行通信。
 * 
 * 2020年5月13日 11:21:26
 * 
 * @author jangle
 * 
 *
 */
public class ServerSocketTest {

	public static void main(String[] args) throws IOException {
		ServerSocket socketServer = null;
		Scanner scanner = null;
		try {
			socketServer = new ServerSocket(8189);
			Socket socket = socketServer.accept();
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();

			scanner = new Scanner(inputStream);		//构造扫描器，扫描客户端的输入
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
			PrintWriter printWriter = new PrintWriter(outputStreamWriter, true);	//构造写入器，向客户端输出提示信息
			printWriter.println("Hello! Enter Bye to exits.");
			boolean done = false;
			while (!done && scanner.hasNextLine()) {
				String line = scanner.nextLine();
				System.out.println("Echo:" + scanner.nextLine());
				if (line.trim().equals("Bye")) {
					done = true;
				}
			}
		} catch (Exception e) {
			
		} finally {
			if (socketServer != null) {
				socketServer.close();
			}
			if (scanner != null) {
				scanner.close();
			}
		}

	}

}
