package xyz.jangle.mouse.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 读取配置文件
 * 
 * @author jangle E-mail: jangle@jangle.xyz
 * @version 2019年10月25日 下午2:14:50 类说明
 */
public class P {

	// 文件路径及名称
	public static final String fileName = "C://lscsP.txt";

	// 全局属性
	public static final Map<String, Object> propertyMap = new HashMap<String, Object>();

	public P() {
		super();
		init();
	}

	public static void main(String[] args) {
		new P();
		for (String key : propertyMap.keySet()) {
			System.out.println(key + ":" + propertyMap.get(key));
		}
	}

	/**
	 *  初始化，加载配置文件
	 */
	public void init() {
		File file = new File(fileName);
		try {
			InputStream in = new FileInputStream(file);
			byte[] b = new byte[in.available()];
			in.read(b);
			in.close();
			String txt = new String(b);
			String[] kvs = txt.split(";");
			for (String kv : kvs) {
				String[] kvArr = kv.split("=");
				propertyMap.put(kvArr[0], kvArr[1]);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
	}

}
