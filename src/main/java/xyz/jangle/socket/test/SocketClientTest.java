package xyz.jangle.socket.test;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * 此处使用java编写一个客户端，访问ServerSocket，将获取到的服务端输出信息进行打印。
 * 
 * 注：要先启动ServerSocketTest
 * 
 * 2020年5月13日 11:54:35
 * 
 * @author jangle
 *
 */
public class SocketClientTest {

	public static void main(String[] args) {
		try (Socket client = new Socket("127.0.0.1", 8189); Scanner scanner = new Scanner(client.getInputStream())) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				System.out.println(line);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
