package xyz.jangle.thread.test.n9_4.testexecutor;

import java.util.concurrent.TimeUnit;

/**
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年10月24日 下午4:05:48
 * 
 */
public class Task implements Runnable {

	private final long milliseconds;

	public Task(long milliseconds) {
		super();
		this.milliseconds = milliseconds;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + ":Begin ");
		try {
			TimeUnit.MILLISECONDS.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " :End ");

	}

}
