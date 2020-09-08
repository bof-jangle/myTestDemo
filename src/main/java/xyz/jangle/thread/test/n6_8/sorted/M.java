package xyz.jangle.thread.test.n6_8.sorted;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import xyz.jangle.thread.test.n6_2.create.Person;
import xyz.jangle.thread.test.n6_2.create.PersonGenerator;

/**
 * 6.8、排序流
 * sorted() 排序流
 * unordered() 消除相遇顺序（以提升并行流的性能）注：并行流在处理有序数据结构时，性能会有很大影响）
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月7日 上午11:52:44
 * 
 */
public class M {

	public static void main(String[] args) {

		int[] numbers = { 1, 2, 3, 3, 2, 2, 1, 6, 7, 8, 5, 7, 8, 6, 5, 9 };
		// 排序并打印
		Arrays.stream(numbers).parallel().sorted().forEachOrdered(n -> System.out.println(n));
		// 人名排序
		System.out.println("查看人名的排序");
		List<Person> persons = PersonGenerator.generatePersonList(10);
		persons.parallelStream().sorted()
				.forEachOrdered(p -> System.out.println(p.getLastName() + "," + p.getFirstName()));
		// 对比消除相遇顺序前后的取值。
		System.out.println("比较消除数据结构的相遇顺序：");
		TreeSet<Person> personSet = new TreeSet<Person>(persons);
		for (int i = 0; i < 10; i++) {
			Person person = personSet.stream().parallel().limit(1).collect(Collectors.toList()).get(0);
			System.out.println("未消除:"+person.getFirstName() + " " + person.getLastName());
		}
		for (int i = 0; i < 10; i++) {
			Person person = personSet.stream().unordered().parallel().limit(1).collect(Collectors.toList()).get(0);
			System.out.println("消除:"+person.getFirstName() + " " + person.getLastName());
		}

	}

}
