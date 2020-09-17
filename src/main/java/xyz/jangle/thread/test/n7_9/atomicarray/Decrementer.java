package xyz.jangle.thread.test.n7_9.atomicarray;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 *  自降线程
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月17日 下午7:03:15
 * 
 */
public class Decrementer implements Runnable {

	private AtomicIntegerArray vector;

	public Decrementer(AtomicIntegerArray vector) {
		super();
		this.vector = vector;
	}

	@Override
	public void run() {
		for (int i = 0; i < vector.length(); i++) {
			vector.getAndDecrement(i);
		}
	}

}
