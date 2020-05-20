package xyz.jangle.atomic.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * 多线程，原子性。LongAdder LongAccumulator 
 * 如果预期可能存在大量竞争，只需要使用LongAdder而不是AtomicLong
 * 
 * LongAccumulator 累加“操作” 1+1+1+1
 * AtomicLong 内部提供多个变量（加数），其总和为当前值。
 * 
 * @author jangle E-mail: jangle@jangle.xyz
 * @version 2020年5月18日 下午4:01:51 类说明
 */
public class LongAccumulatorTest {

	public static void main(String[] args) throws InterruptedException {
		testLongAccumulator();
		System.out.println("----------------");
		testLongAdder();
	}

	/**
	 * 多线程累加操作
	 * 
	 * @throws InterruptedException
	 */
	public static void testLongAccumulator() throws InterruptedException {
		LongAccumulator longAccumulator = new LongAccumulator(Long::sum, 0);
		//创建100个线程执行累加操作
		for (int j = 0; j < 100; j++) {
			new Thread(() -> {
				for (int i = 0; i < 1000; i++) {
					longAccumulator.accumulate(1);
				}
			}).start();
		}
		Thread.sleep(2000);
		long count = longAccumulator.get();
		System.out.println("LongAccumulator累加结果"+count);

	}

	/**
	 * 并行累加器
	 * @throws InterruptedException 
	 */
	public static void testLongAdder() throws InterruptedException {
		ExecutorService pool = Executors.newCachedThreadPool();
		LongAdder adder = new LongAdder();
		System.out.println("初始值" + adder.sum());
		//创建100个线程放入线程池中，执行累加操作
		for (int i = 0; i < 100; i++) {
			pool.submit(() -> {
				for (int j = 0; j < 1000; j++) {
					adder.increment();
				}
			});
		}
		System.out.println("中途值："+adder.sum());
		Thread.sleep(2000L);
		System.out.println("结果："+adder.sum());
	}

}
