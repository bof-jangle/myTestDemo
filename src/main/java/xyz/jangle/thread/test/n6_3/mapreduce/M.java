package xyz.jangle.thread.test.n6_3.mapreduce;

import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;

import xyz.jangle.thread.test.n6_2.create.Person;
import xyz.jangle.thread.test.n6_2.create.PersonGenerator;

/**
 * 6.3、归约流（MapReduce)
 * 	Map、把原始元素转换为一种更适合归约操作的形式。
 *  Reduce、在所有元素之上生成一个总计结果。
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月2日 下午8:30:18
 * 
 */
public class M {

	public static void main(String[] args) {

		// Integer Double Long 对应的特有Stream
		List<Double> numbers = DoubleGenerator.generateDoubleList(10000, 1000);
		DoubleStream doubleStream = DoubleGenerator.generateStreamFromList(numbers);
		long count = doubleStream.parallel().count();
		System.out.println("doubleStream count:" + count);
		doubleStream = DoubleGenerator.generateStreamFromList(numbers);
		double sum = doubleStream.parallel().sum();
		doubleStream = DoubleGenerator.generateStreamFromList(numbers);
		double average = doubleStream.average().getAsDouble();
		doubleStream = DoubleGenerator.generateStreamFromList(numbers);
		double max = doubleStream.max().getAsDouble();
		doubleStream = DoubleGenerator.generateStreamFromList(numbers);
		double min = doubleStream.min().getAsDouble();
		System.out.println("sum:" + sum + ",average:" + average + ",max:" + max + ",min:" + min);

		// 第一种reduce方法（该方法需要符合结合律）
		List<Point> points = PointGenerator.generatorPointList(10000);
		Optional<Point> opt = points.parallelStream().reduce((p1, p2) -> {
			Point point = new Point();
			point.setX(p1.getX() + p2.getX());
			point.setY(p1.getY() + p2.getY());
			return point;
		});
		System.out.println("point:x:" + opt.get().getX() + "y:" + opt.get().getY());

		// 第二种reduce方法（两个参数：1同一律值，2结合律）
		List<Person> persons = PersonGenerator.generatePersonList(10000);
		Integer salaryTotal = persons.parallelStream().map(p -> p.getSalary()).reduce(0, (s1, s2) -> s1 + s2);
		System.out.println("SalaryTotal:" + salaryTotal);

		// 第三种reduce方法（三个参数：1同一律值，2转换运算，3结合律）
		Integer value = 0;
		value = persons.parallelStream().reduce(value, (n, p) -> p.getSalary() > 10000 ? n + 1 : n,
				(n1, n2) -> n1 + n2);
		System.out.println("薪资大于10000的人数共：" + value+"人");

	}

}
