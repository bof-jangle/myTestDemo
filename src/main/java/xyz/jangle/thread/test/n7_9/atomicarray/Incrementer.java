package xyz.jangle.thread.test.n7_9.atomicarray;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 *  自增线程
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月17日 下午7:00:11
 * 
 */
public class Incrementer implements Runnable {

	private final AtomicIntegerArray vector;

	public Incrementer(AtomicIntegerArray vector) {
		super();
		this.vector = vector;
	}

	@Override
	public void run() {
		for (int i = 0; i < vector.length(); i++) {
			vector.getAndIncrement(i);
		}

	}

}
