package xyz.jangle.thread.test.n7_9.atomicarray;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *  自增线程2  数组形式的Atomic
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月17日 下午7:00:11
 * 
 */
public class Incrementer2 implements Runnable {

	private final AtomicInteger[] vector;

	public Incrementer2(AtomicInteger[] vector) {
		super();
		this.vector = vector;
	}

	@Override
	public void run() {
		for (int i = 0; i < vector.length; i++) {
			vector[i].getAndIncrement();
		}
	}

}
