package xyz.jangle.instanceoe.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年7月14日 上午10:10:37
 * 
 */
public class InstanceofTest {

	public static void main(String[] args) {
		HashMap<String, Object> m = new HashMap<String, Object>();
		System.out.println(m instanceof Map);	// true   子类对象及引用类型，属于其继承或者实现的类或者接口类型。
	}
}
