package xyz.jangle.thread.test.n6_3.mapreduce;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.DoubleStream.Builder;

/**
 * 随机Double列表工厂
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月2日 下午8:33:34
 * 
 */
public class DoubleGenerator {

	/**
	 * 	构造一个Double列表
	 * 
	 * @author jangle
	 * @time 2020年9月2日 下午8:43:13
	 * @param size
	 * @param max
	 * @return
	 */
	public static List<Double> generateDoubleList(int size, int max) {
		Random r = new Random();
		ArrayList<Double> numbers = new ArrayList<Double>();
		for (int i = 0; i < size; i++) {
			double value = r.nextDouble() * max;
			numbers.add(value);
		}
		return numbers;
	}

	/**
	 *  使用DoubleStream.Builder 来构造DoubleStream
	 * 
	 * @author jangle
	 * @time 2020年9月2日 下午8:41:37
	 * @param list
	 * @return
	 */
	public static DoubleStream generateStreamFromList(List<Double> list) {
		Builder builder = DoubleStream.builder();
		for (Double number : list) {
			builder.add(number);
		}
		return builder.build();
	}

}
