package xyz.jangle.thread.test.n6_4.collect;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import xyz.jangle.thread.test.n6_2.create.Person;
import xyz.jangle.thread.test.n6_2.create.PersonGenerator;

/**
 * 6.4、收集流
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月3日 下午9:27:11
 * 
 */
public class M {

	public static void main(String[] args) {
		// 1、收集成一个Map
		List<Person> persons = PersonGenerator.generatePersonList(100);
		ConcurrentMap<String, List<Person>> personByName = persons.parallelStream()
				.collect(Collectors.groupingByConcurrent(Person::getFirstName));
		// 遍历这个Map，并打印相关信息
		personByName.keySet().forEach(key -> {
			List<Person> list = personByName.get(key);
			System.out.println(key + " : " + list.size());
		});

		// 2、串联方式收集
		String resultName = persons.parallelStream().map(p -> p.toString()).collect(Collectors.joining(","));
		System.out.println("串联方式收集的名称：" + resultName);

		// 3、拆分为2组 薪资大于50000 和小于等于50000的
		Map<Boolean, List<Person>> part = persons.parallelStream()
				.collect(Collectors.partitioningBy(p -> p.getSalary() > 50000));
		part.keySet().forEach(k -> {
			List<Person> list = part.get(k);
			System.out.println(k + ":" + list.size());
		});

		// 4、收集成一个Map (firstName作key， LastName做value ,当存在相同的key时，将2个value用s1+","+s2的方式拼接
		Map<String, String> map = persons.parallelStream()
				.collect(Collectors.toMap(p -> p.getFirstName(), p -> p.getLastName(), (s1, s2) -> s1 + "," + s2));
		map.forEach((k, v) -> {
			System.out.println(k + ":" + v);
		});

		// 5、并行处理列表，再合并列表
		ArrayList<Object> highSalaryPerson = persons.parallelStream().collect(ArrayList::new, (list, p) -> {
			if (p.getSalary() > 50000) {
				list.add(p);
			}
		}, ArrayList::addAll);
		System.out.println("高新人数：" + highSalaryPerson.size());

		// 6、收集为ConcurrentHashMap
		ConcurrentHashMap<String, Counter> peopleNames = persons.parallelStream().collect(
				// 构造一个ConcurrentHashMap
				ConcurrentHashMap::new,
				// 向m中添加p
				(m, p) -> {
					// key存在时
					m.computeIfPresent(p.getFirstName(), (name, counter) -> {
						counter.increment();
						return counter;
					});
					// key不存在时
					m.computeIfAbsent(p.getFirstName(), name -> {
						Counter counter = new Counter();
						counter.setValue(name);
						return counter;
					});
				}, (map1, map2) -> {
					// 合并2个map
					map2.forEach(10, (k, v) -> {
						map1.merge(k, v, (v1, v2) -> {
							v1.setCounter(v1.getCounter() + v2.getCounter());
							return v1;
						});
					});
				});
		peopleNames.forEach((name, counter) -> {
			System.out.println(name + ":" + counter.getCounter());
		});

	}

}
