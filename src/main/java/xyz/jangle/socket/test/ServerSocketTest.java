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
		newMain();
//		oldMain();

	}

	/**
	 * jdk7之后的，try-with-resource 的写法 try块正常退出或者异常退出时，都会自动调用close方法。
	 * 
	 * 如果try块抛出异常，而且close方法也抛出异常。 那么原来的异常会被重新抛出。
	 * close异常会被挂起并由addSupperessed方法增加到原来的异常，而后用getSupperessed方法获取被挂起的close方法异常数组。
	 */
	public static void newMain() {
		try (ServerSocket socketServer = new ServerSocket(8189);
				Socket socket = socketServer.accept();
				InputStream inputStream = socket.getInputStream();
				Scanner scanner = new Scanner(inputStream);) {

			OutputStream outputStream = socket.getOutputStream();
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
			PrintWriter printWriter = new PrintWriter(outputStreamWriter, true); // 构造写入器，向客户端输出提示信息
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

		}
	}

	/**
	 * jdk7 以前的写法
	 */
	public static void oldMain() {
		ServerSocket socketServer = null;
		Scanner scanner = null;
		try {
			try {
				socketServer = new ServerSocket(8189);
				Socket socket = socketServer.accept();
				InputStream inputStream = socket.getInputStream();
				OutputStream outputStream = socket.getOutputStream();

				scanner = new Scanner(inputStream); // 构造扫描器，扫描客户端的输入
				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
				PrintWriter printWriter = new PrintWriter(outputStreamWriter, true); // 构造写入器，向客户端输出提示信息
				printWriter.println("Hello! Enter Bye to exits.");
				boolean done = false;
				while (!done && scanner.hasNextLine()) {
					String line = scanner.nextLine();
					System.out.println("Echo:" + scanner.nextLine());
					if (line.trim().equals("Bye")) {
						done = true;
					}
				}
			} finally {
				if (socketServer != null) {
					socketServer.close();
				}
				if (scanner != null) {
					scanner.close();
				}
			}
		} catch (Exception e) {
			// 此处用于捕获包含close() 方法调用的异常的 异常捕获。
		}

	}

}
