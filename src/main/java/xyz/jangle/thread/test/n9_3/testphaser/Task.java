package xyz.jangle.thread.test.n9_3.testphaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 *  
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年10月23日 下午5:59:34
 * 
 */
public class Task implements Runnable {

	private final int time;

	private final Phaser phaser;

	public Task(int time, Phaser phaser) {
		super();
		this.time = time;
		this.phaser = phaser;
	}

	@Override
	public void run() {

		phaser.arrive();
		System.out.println(Thread.currentThread().getName() + " phaser 1 start");
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " phaser 1 done");
		phaser.arriveAndAwaitAdvance();
		System.out.println(Thread.currentThread().getName() + " phaser 2 start");
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " phaser 2 done");
		phaser.arriveAndAwaitAdvance();
		System.out.println(Thread.currentThread().getName() + " phaser 3 start");
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " phaser 3 done");
		phaser.arriveAndDeregister();

	}

}
