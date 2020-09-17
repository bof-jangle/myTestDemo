package xyz.jangle.thread.test.n7_9.atomicarray;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自降线程2 数组形式的atomic
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月17日 下午7:03:15
 * 
 */
public class Decrementer2 implements Runnable {

	private AtomicInteger[] vector;

	public Decrementer2(AtomicInteger[] vector) {
		super();
		this.vector = vector;
	}

	@Override
	public void run() {
		for (int i = 0; i < vector.length; i++) {
			vector[i].getAndDecrement();
		}
	}

}
