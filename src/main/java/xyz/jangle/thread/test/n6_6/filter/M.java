package xyz.jangle.thread.test.n6_6.filter;

import java.util.Arrays;
import java.util.List;

import xyz.jangle.thread.test.n6_2.create.Person;
import xyz.jangle.thread.test.n6_2.create.PersonGenerator;

/**
 * 6.6、过滤流（(filter）
 * 	distinct	去重
 * 	sorted	排序
 * 	mapToInt、mapToDouble 转换类型
 * 	takeWhile()、dropWhile()		另一种过滤方式（基于排序）
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月5日 下午5:01:47
 * 
 */
public class M {

	public static void main(String[] args) {
		List<Person> personList = PersonGenerator.generatePersonList(10);
		personList.parallelStream().forEach(p -> System.out.println(p.getLastName() + "," + p.getFirstName()));
		// 去除重复(distinct无法在并行流上获取性能优势）
		System.out.println("去除重复：");
		personList.stream().distinct()
				.forEach(p -> System.out.println(p.getLastName() + "," + p.getFirstName()));
		// 去除重复
		Integer[] numbers = { 1, 3, 2, 1, 2, 2, 1, 3, 2, 2, 1, 2 };
		Arrays.asList(numbers).parallelStream().mapToInt(n -> n).distinct()
				.forEach(p -> System.out.println("number:" + p));
		// 筛选工资小于3万的人员
		personList.parallelStream().filter(p -> p.getSalary() < 30000).forEach(
				p -> System.out.println(p.getLastName() + "," + p.getFirstName() + ",Salary:" + p.getSalary()));
		// 筛选小于2的数字
		Arrays.asList(numbers).parallelStream().mapToInt(n -> n).filter(n -> n < 2)
				.forEach(n -> System.out.println("number:" + n));
		// 测试排序：
		System.out.println("测试排序：使用parallelStream");
		Arrays.asList(numbers).parallelStream().sorted().forEach(n -> System.out.println(n));
		System.out.println("测试排序：使用stream");
		Arrays.asList(numbers).stream().sorted().forEach(n -> System.out.println(n));
		// 此处使用串行stream，因为parallelStream的结果是不正确的。
		personList.stream().mapToDouble(p -> p.getSalary()).sorted().skip(5)
				.forEach(salary -> System.out.println(salary));
		
		// 測試takeWhile（该方法一般用于串行有序流。）
		System.out.println("测试takeWhile:获取符合条件的元素，直到断言不成立，返回获取的元素");
		Arrays.asList(numbers).stream().mapToInt(n -> n).sorted().takeWhile(n -> n==1).forEach(n -> System.out.println(n));
		System.out.println("测试dropWhile:去除符合条件的元素，直到断言不成立，返回剩余的元素");
		Arrays.asList(numbers).stream().mapToInt(n -> n).sorted().dropWhile(n -> n==1).forEach(n -> System.out.println(n));

	}

}
