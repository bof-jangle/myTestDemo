package xyz.jangle.thread.test.n6_5.foreach;

import java.util.List;

import xyz.jangle.thread.test.n6_2.create.Person;
import xyz.jangle.thread.test.n6_2.create.PersonGenerator;
import xyz.jangle.thread.test.n6_3.mapreduce.DoubleGenerator;

/**
 * 6.5、遍历流（forEach）
 *  forEach无法保证动作是有序的应用到流上的。
 *  forEachOrdered(): 需要先使用sorted方法排序，再使用该方法，该方法可以保证动作是有序应用到流上的
 *  peek(): 这是个中间操作，是懒执行的，只有调用结尾操作时，动作才会真正应用到流的消费元素上。
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月4日 下午4:04:05
 * 
 */
public class M {

	public static void main(String[] args) {
		List<Double> doubleList = DoubleGenerator.generateDoubleList(10, 100);
		System.out.println("**使用排序后的流来遍历：");
		doubleList.parallelStream().sorted().forEachOrdered(d -> System.out.println(d));
		System.out.println("**不使用排序遍历：");
		doubleList.parallelStream().sorted().forEach(d -> System.out.println(d));

		List<Person> persons = PersonGenerator.generatePersonList(10);
		System.out.println("**不使用排序遍历：");
		persons.parallelStream().forEach(p -> System.out.println(p.getLastName() + "," + p.getFirstName()));
		System.out.println("**使用排序后的流来遍历：");
		persons.parallelStream().sorted()
				.forEachOrdered(p -> System.out.println(p.getLastName() + "," + p.getFirstName()));

		doubleList.parallelStream().peek(d -> System.out.println("1 Step  :" + d))
				.peek(d -> System.out.println("2 Step :" + d)).forEach(d -> System.out.println("Last Step N:" + d));

	}

}
