package xyz.jangle.hashset.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * @author huhj
 * @email  jangle@jangle.xyz
 * @time   2018年8月2日 下午3:01:40
 */
public class HashSetTest {

	public static void main(String[] args) {

//		testHashSetOrder();
		testTreeSetOrder();
	}
	
	/**
	 * 树集的排序
	 */
	public static void testTreeSetOrder() {
		TreeSet<String> set = new TreeSet<String>();
		set.add("1");
		set.add("2");
		set.add("3");
		set.add("asd");
		set.add("asdf");
		set.add("asdfg");
		
//		set.forEach(e -> System.out.println(e));
		set.add("aaa");
		set.add("bbb");
		set.forEach(e -> System.out.println(e));
		
	}
	
	/**
	 * 测试散列set集的排序。
	 */
	public static void testHashSetOrder() {
		HashSet<String> set = new HashSet<String>();
		set.add("1");
		set.add("2");
		set.add("3");
		set.add("asd");
		set.add("asdf");
		set.add("asdfg");
		
//		set.forEach(e -> System.out.println(e));
		set.add("aaa");
		set.add("bbb");
		set.forEach(e -> System.out.println(e));
		
		
	}
	
	public static void oldMain() {
		HashSet<String> set = new HashSet<String>();
		boolean add = set.add("123");
		System.out.println(add);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		Object put = map.put("123", new Object());
		System.out.println(put);
		System.out.println(map.get("123"));
	}

}
