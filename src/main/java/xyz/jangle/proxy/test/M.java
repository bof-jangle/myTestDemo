package xyz.jangle.proxy.test;

import java.lang.reflect.Proxy;
import java.util.Arrays;

/** 
* @author jangle E-mail: jangle@jangle.xyz
* @version 2020年5月23日 下午7:19:51 
* 类说明 
*/
public class M {


	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		
		var arr = new Object[1000];
		for(int i=0;i<arr.length;i++) {
			Integer value = i+1;
			var handler = new TranceHandler(value);
			Object proxyInstance = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[] {Comparable.class}, handler);
			arr[i] = proxyInstance;
		}
		
//		int search = Arrays.binarySearch(arr, Integer.valueOf(256));
//		System.out.println(search);
		
		var<Integer> c = (Comparable<Integer>)arr[1];
		c.compareTo(Integer.valueOf(1));
		String string = c.toString();
		System.out.println(string);

	}

}
