package xyz.jangle.thread.test.n7_9.atomicarray;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 *  7.9、原子性数组
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月17日 上午10:24:17
 * 
 */
public class M {

	public static void main(String[] args) {

		final int THREADS = 100;

		AtomicIntegerArray vector = new AtomicIntegerArray(1000);
		AtomicInteger[] vector2 = new AtomicInteger[1000];

		var incrementer = new Incrementer(vector);
		var decrementer = new Decrementer(vector);
		var incrementer2 = new Incrementer2(vector2);
		var decrementer2 = new Decrementer2(vector2);

		var incrementers = new Thread[THREADS];
		var decrementers = new Thread[THREADS];
		var incrementers2 = new Thread[THREADS];
		var decrementers2 = new Thread[THREADS];

		for (int i = 0; i < THREADS; i++) {
			incrementers[i] = new Thread(incrementer);
			decrementers[i] = new Thread(decrementer);
			incrementers[i].start();
			decrementers[i].start();
		}
		// 同步
		for (int i = 0; i < THREADS; i++) {
			try {
				incrementers[i].join();
				decrementers[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		int errors = 0;
		for (int i = 0; i < vector.length(); i++) {
			if (vector.get(i) != 0) {
				System.out.println("Vector[" + i + "] :" + vector.get(i));
				errors++;
			}
		}
		System.out.println("ERROR :" + errors);
		
		// ******** 以下是普通数组形式的   执行会发生异常。
		for (int i = 0; i < THREADS; i++) {
			incrementers2[i] = new Thread(incrementer2);
			decrementers2[i] = new Thread(decrementer2);
			incrementers2[i].start();
			decrementers2[i].start();
		}
		// 同步
		for (int i = 0; i < THREADS; i++) {
			try {
				incrementers2[i].join();
				decrementers2[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		errors = 0;
		for (int i = 0; i < vector2.length; i++) {
			if (vector.get(i) != 0) {
				System.out.println("Vector[" + i + "] :" + vector.get(i));
				errors++;
			}
		}
		System.out.println("ERROR2 :" + errors);

	}

}
