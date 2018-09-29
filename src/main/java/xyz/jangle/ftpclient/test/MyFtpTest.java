package xyz.jangle.ftpclient.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.SocketException;
//import java.util.Calendar;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

/**
 * @author huhj
 * @email  jangle@jangle.xyz
 * @time   2018年7月3日 下午2:44:00
 */
public class MyFtpTest {

	public static void main(String[] args) {
		FTPClient ftp = new FTPClient();
		ftp.setControlEncoding("UTF-8");
		try {
			ftp.connect("121.199.31.41", 21);
			ftp.login("gbuser", "gb2018!@#");
			int replyCode = ftp.getReplyCode(); // 是否成功登录服务器
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				System.out.println("connect failed...ftp服务器:");
				return;
			}
			System.out.println("登陆成功");
			String taskContent = "这是一个测试文件";
			//这是一个按日期年，月创建文件夹并进入的逻辑，按需求开放
//			Calendar calendar = Calendar.getInstance();
//			int month = calendar.get(Calendar.MONTH)+1;
//			int year = calendar.get(Calendar.YEAR);
//			ftp.makeDirectory(""+year+month);
//			ftp.changeWorkingDirectory(""+year+month);
			byte[] ktrBytes = taskContent.getBytes();
			ByteArrayInputStream bais = new ByteArrayInputStream(ktrBytes);
			ftp.storeFile("testhhjtxt2", bais);
			bais.close();
			ftp.logout();
			System.out.println("上传文件成功");
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		

	}

}
