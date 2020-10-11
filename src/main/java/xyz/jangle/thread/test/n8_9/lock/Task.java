package xyz.jangle.thread.test.n8_9.lock;

import java.util.concurrent.TimeUnit;

/**
 *  使用自定义Lock的任务
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年10月8日 上午9:20:41
 * 
 */
public class Task implements Runnable {

	private final MyLock lock;

	private final String name;

	public Task(MyLock lock, String name) {
		super();
		this.lock = lock;
		this.name = name;
	}

	@Override
	public void run() {
		lock.lock();
		System.out.println("Task:" + name + " 开始加锁");
		try {
			TimeUnit.SECONDS.sleep(2);
			System.out.println("Task:" + name + "释放锁");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}
