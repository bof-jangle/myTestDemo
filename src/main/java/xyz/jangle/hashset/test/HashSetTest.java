package xyz.jangle.hashset.test;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author huhj
 * @email  jangle@jangle.xyz
 * @time   2018年8月2日 下午3:01:40
 */
public class HashSetTest {

	public static void main(String[] args) {

		HashSet<String> set = new HashSet<String>();
		boolean add = set.add("123");
		System.out.println(add);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		Object put = map.put("123", new Object());
		System.out.println(put);
		System.out.println(map.get("123"));
		
		
	}

}
