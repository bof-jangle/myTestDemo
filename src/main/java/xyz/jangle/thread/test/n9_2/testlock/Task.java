package xyz.jangle.thread.test.n9_2.testlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年10月21日 下午9:29:11
 * 
 */
public class Task implements Runnable {

	private final Lock lock;

	public Task(Lock lock) {
		super();
		this.lock = lock;
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			lock.lock();
			System.out.println(Thread.currentThread().getName() + ":获取了当前锁");
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}

	}

}
