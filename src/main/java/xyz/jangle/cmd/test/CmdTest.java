package xyz.jangle.cmd.test;

import java.io.IOException;

/** 
 * 调用windows系统资源，执行相应的操作
* @author jangle E-mail: jangle@jangle.xyz
* @version 2018年9月30日 上午9:39:27 
* 类说明 
*/
public class CmdTest {

	public static void main(String[] args) {
		try {
			String exe = "C:\\Windows\\explorer.exe ";	//执行器
			String path = "D:\\workspace";				//执行参数
			String cmd = exe+path;
			Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
