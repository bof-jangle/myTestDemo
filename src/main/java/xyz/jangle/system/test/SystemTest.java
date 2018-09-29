package xyz.jangle.system.test;

import java.util.Properties;

/**
 * @author huhj
 * @email  jangle@jangle.xyz
 * @time   2018年8月14日 下午6:10:42
 */
public class SystemTest {

	public static void main(String[] args) {
		Properties props=System.getProperties(); //获得系统属性集  
		String osName = props.getProperty("os.name"); //操作系统名称
		System.out.println(osName);
		if(osName.startsWith("Win")){
		}

	}

}
