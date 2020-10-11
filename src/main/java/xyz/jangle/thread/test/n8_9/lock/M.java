package xyz.jangle.thread.test.n8_9.lock;

import java.util.concurrent.TimeUnit;

/**
 * 8.9、自定义Lock类
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年10月7日 下午7:33:31
 * 
 */
public class M {

	public static void main(String[] args) {
		var lock = new MyLock();
		for (int i = 0; i < 10; i++) {
			var task = new Task(lock, "Task-" + i);
			var thread = new Thread(task);
			thread.start();
		}
		// 用tryLock尝试获取锁，每一秒一次，直到成功
		boolean value;
		do {
			try {
				value = lock.tryLock(1, TimeUnit.SECONDS);
				if (!value) {
					System.out.println("M: 尝试获取锁");
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				value = false;
			}
		} while (!value);
		System.out.println("M: 成功获得锁");
		lock.unlock();
		System.out.println("M: 程序结束");

	}
}
