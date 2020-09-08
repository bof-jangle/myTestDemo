package xyz.jangle.thread.test.n6_9.match;

import java.util.List;

import xyz.jangle.thread.test.n6_2.create.Person;
import xyz.jangle.thread.test.n6_2.create.PersonGenerator;

/**
 * 6.9、验证流
 * allMatch:全部匹配
 * anyMatch:任一匹配
 * noneMatch:无一匹配
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月8日 下午9:01:58
 * 
 */
public class M {

	public static void main(String[] args) {
		List<Person> persons = PersonGenerator.generatePersonList(10);
		Integer max = persons.parallelStream().map(p -> p.getSalary()).max(Integer::compare).get();
		int min = persons.parallelStream().mapToInt(p -> p.getSalary()).min().getAsInt();
		System.out.println("Max:" + max + ",min:" + min);
		boolean condition;
		condition = persons.parallelStream().allMatch(p -> p.getSalary() > 0);
		System.out.println("所有的工资都大于0：" + condition);
		condition = persons.parallelStream().allMatch(p -> p.getSalary() > 10000);
		System.out.println("所有工资都大于10000：" + condition);
		condition = persons.parallelStream().allMatch(p -> p.getSalary() > 30000);
		System.out.println("所有工资都大于30000：" + condition);
		condition = persons.parallelStream().anyMatch(p -> p.getSalary() > 50000);
		System.out.println("存在工资大于50000的员工" + condition);
		condition = persons.parallelStream().noneMatch(p -> p.getSalary() > 100000);
		System.out.println("不存在工资大于100000的员工：" + condition);

		// 获取任一元素
		Person person = persons.parallelStream().findAny().get();
		System.out.println(person.getFirstName() + person.getLastName() + "," + person.getSalary());
		// 获取第一个元素
		person = persons.parallelStream().findFirst().get();
		System.out.println(person.getFirstName() + person.getLastName() + "," + person.getSalary());
		// 获取最低工资的员工
		person = persons.parallelStream().sorted((p1, p2) -> p1.getSalary() - p2.getSalary()).findFirst().get();
		System.out.println(person.getFirstName() + person.getLastName() + "," + person.getSalary());

	}

}
