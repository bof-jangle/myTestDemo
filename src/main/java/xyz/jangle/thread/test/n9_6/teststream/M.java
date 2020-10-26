package xyz.jangle.thread.test.n9_6.teststream;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 9.6、监测流
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年10月25日 上午11:43:25
 * 
 */
public class M {

	public static void main(String[] args) {

		var counter = new AtomicLong(0);
		var random = new Random();
		// 此处的peek不会执行。因为count的结果不受peek影响。（peek是懒执行的）
		var streamCounter = random.doubles(1000).parallel()
				.peek(n -> System.out.println(counter.incrementAndGet() + "-" + n)).count();
		System.out.println("Counter:" + counter.get());
		System.out.println("Stream Counter:" + streamCounter);
		counter.set(0);
		random.doubles(1000).parallel().peek(n -> System.out.println("Peek: " + counter.incrementAndGet() + "-" + n))
				.forEach(n -> System.out.println("ForEach: " + n));
		System.out.println("Counter: " + counter.get());

	}

}
