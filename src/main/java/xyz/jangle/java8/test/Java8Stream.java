package xyz.jangle.java8.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author jangle E-mail: jangle@jangle.xyz
 * @version 2020年1月8日 下午4:47:26 类说明
 */
public class Java8Stream {

	public static void main(String[] args) {
		/*
		 * List<String> list = new ArrayList<>(); list.add("1"); list.add("2");
		 * list.add("3"); list.add("3"); list.add("4"); int count = 0; for(String s :
		 * list) { if(s.equals("1")) { count++; } } System.out.println(count);
		 * 
		 * long count2 = list.stream().count(); System.out.println(count2);
		 * 
		 * long count3 = list.stream().filter(s -> s.equals("1")).count();
		 * System.out.println(count3);
		 * 
		 * long count4 = list.stream().distinct().count(); System.out.println(count4);
		 * 
		 * Optional<String> findFirst = list.stream().findFirst(); String string =
		 * findFirst.get(); System.out.println(string);
		 */
		/*
		 * List<String> collect = Stream.of("a", "b", "c").collect(Collectors.toList());
		 * List<String> asList = Arrays.asList("a", "b", "c");
		 * Arrays.asList(collect.toArray()); Object[] array = collect.toArray();
		 * Object[] array2 = asList.toArray();
		 */
		/*
		 * // T... 跟 T[] 参数的对比 JJJ.method(); JJJ.method("1", "2"); String[] s =
		 * {"1","2"}; JJJ.method(s);
		 */

//		filter();	//列表内容过滤
//		map();		//列表内容修改
//		flatMap();	//列表内容合并
//		min();		//列表内容最大值/最小值

	}
	
	public static void min() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
		Integer min = list.stream().min(Comparator.comparing(x->x.toString())).get();
		Integer max = list.stream().max(Comparator.comparing(x->x.toString())).get();
		System.out.println(min);
		System.out.println(max);
	}

	public static void filter() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
		List<Integer> collect = list.stream().filter(s -> s > 3).collect(Collectors.toList());
		System.out.println(collect);
	}
	
	public static void map() {
		List<Integer> list = Arrays.asList(1,3,5,7,9);
		List<Integer> collect = list.stream().map(i->i+1).collect(Collectors.toList());
		System.out.println(collect);
	}

	public static void flatMap() {
		List<Integer> list1 = Arrays.asList(1, 2);
		List<Integer> list2 = Arrays.asList(3, 4);
		List<Integer> collect = Stream.of(list1, list2).flatMap(x -> x.stream()).collect(Collectors.toList());
		System.out.println(collect.toString());
	}

	static class JJJ {
		static void method(String... s) {
			System.out.println(s.length);
		}

	}

}
