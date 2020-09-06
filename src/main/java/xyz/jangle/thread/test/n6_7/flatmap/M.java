package xyz.jangle.thread.test.n6_7.flatmap;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import xyz.jangle.thread.test.n6_2.create.Person;
import xyz.jangle.thread.test.n6_2.create.PersonGenerator;

/**
 * 6.7 转换流 flatMap（中间操作） 
 * 1、使用map返回另外的类型。
 * 2、从Stream转换为IntStream、LongStream、DoubleStream。 
 * 3、使用flatMap将嵌套的流串联成一个流。
 * 4、使用分组功能
 * 5、根据生日计算年龄
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月6日 上午9:32:06
 * 
 */
public class M {

	public static void main(String[] args) {
		// 打印薪资(去重) 转换为DoubleStream
		List<Person> persons = PersonGenerator.generatePersonList(20);
		DoubleStream doubleStream = persons.parallelStream().mapToDouble(p -> p.getSalary());
		doubleStream.distinct().forEach(d -> System.out.println(d));
		// 薪资记录数量(去重)
		doubleStream = persons.parallelStream().mapToDouble(p -> p.getSalary());
		long size = doubleStream.distinct().count();
		System.out.println("Size:" + size);
		// map返回另外的类型
		List<BasicPerson> basicPersons = persons.parallelStream().map(p -> {
			BasicPerson bp = new BasicPerson();
			bp.setName(p.getFirstName() + " " + p.getLastName());
			bp.setAge(getAge(p.getBirthDate()));
			return bp;
		}).collect(Collectors.toList());
		basicPersons.forEach(bp -> System.out.println(bp.getName() + ": age is " + bp.getAge()));
		// flatMap合并流  ，并分组
		System.out.println("使用flatMap和分组功能：");
		List<String> file = FileGenerator.generateFile(100);
		ConcurrentMap<String, Long> wordCount = file.parallelStream().flatMap(line -> Stream.of(line.split("[ ,.]")))
				.filter(w -> w.length() > 0).sorted()
				.collect(Collectors.groupingByConcurrent(e -> e, Collectors.counting()));
		wordCount.forEach((k, v) -> System.out.println(k + ":" + v));

	}

	/**
	 *  根据生日计算年龄
	 * 
	 * @author jangle
	 * @time 2020年9月6日 上午10:19:01
	 * @param birthDate
	 * @return
	 */
	private static long getAge(Date birthDate) {
		LocalDate start = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate now = LocalDate.now();
		long age = ChronoUnit.YEARS.between(start, now);
		return age;
	}

}
