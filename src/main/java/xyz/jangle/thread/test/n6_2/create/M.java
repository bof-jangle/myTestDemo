package xyz.jangle.thread.test.n6_2.create;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 * 6.2、使用不同的源创建流
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月1日 下午6:03:15
 * 
 */
public class M {

	public static void main(String[] args) {

		// 从一个Collection中创建流
		System.out.println("从一个Collection中创建流：");
		List<Person> persons = PersonGenerator.generatePersonList(10000);
		Stream<Person> parallelStream = persons.parallelStream();
		System.out.println("流总数：" + parallelStream.count());

		// 从Supplier中创建流
		System.out.println("从Supplier中创建流：");
		MySupplier mySupplier = new MySupplier();
		Stream<String> generate = Stream.generate(mySupplier);
		generate.parallel().limit(10).forEach(p -> System.out.println(p));

		// 从一个定义好的元素集合上创建流
		System.out.println("从一个定义好的元素集合上创建流：");
		Stream<String> stream = Stream.of("1", "2", "3", "4A");
		stream.parallel().forEach(p -> System.out.println(p));

		// 从一个文件中创建流
		System.out.println("从一个文件中创建流：");
		try (BufferedReader br = new BufferedReader(new FileReader("C:\\logs\\myTestDemo\\log.log"));) {
			Stream<String> lines = br.lines();
			System.out.println("文件总共行数" + lines.count());
			System.out.println("***********************");
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 创建一个目录流
		System.out.println("创建一个目录流：");
		try {
			Stream<Path> directoryContent = Files.list(Paths.get(System.getProperty("user.home")));
			System.out
					.println(System.getProperty("user.home") + "目录下的文件和文件夹数量为：" + directoryContent.parallel().count());
//			Files.list(Paths.get(System.getProperty("user.home"))).parallel().forEach(p -> System.out.println(p.getFileName()));
			directoryContent.close();
			System.out.println("***********************");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 使用一个数组创建流
		System.out.println("使用一个数组创建流：");
		String array[] = { "1", "2", "3", "4", "5" };
		Stream<String> streamFromArray = Arrays.stream(array);
		streamFromArray.parallel().forEach(p -> System.out.println(p));

		// 创建一个双精度型数字流
		System.out.println("创建一个双精度型数字流：");
		Random random = new Random();
		DoubleStream doubleStream = random.doubles(10);
		double asDouble = doubleStream.parallel().peek(p -> System.out.println(p)).average().getAsDouble();
		System.out.println("平均值" + asDouble);

		// 连接两个流
		System.out.println("连接两个流：");
		Stream<String> s1 = Stream.of("1", "2", "3", "4", "5");
		Stream<String> s2 = Stream.of("0", "9", "8", "7", "6");
		Stream<String> concat = Stream.concat(s1, s2);
		concat.parallel().forEach(p -> System.out.println(p));

	}

}
