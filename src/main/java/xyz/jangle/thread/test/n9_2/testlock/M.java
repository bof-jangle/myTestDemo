package xyz.jangle.thread.test.n9_2.testlock;

import java.util.concurrent.TimeUnit;

/**
 * 9.2、监测Lock接口
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年10月21日 下午9:20:12
 * 
 */
public class M {

	public static void main(String[] args) throws Exception {
		var lock = new MyLock();
		Thread threads[] = new Thread[5];
		for (int i = 0; i < 5; i++) {
			var task = new Task(lock);
			threads[i] = new Thread(task);
			threads[i].start();
		}
		for (int i = 0; i < 15; i++) {
			System.out.println("M:Log :**********");
			System.out.println("Lock:Owner:"+lock.getOwnerName());
			if(lock.hasQueuedThreads()) {
				System.out.print("Lock:排队中的线程："+lock.getQueueLength()+"个 :");
				var lockedThreads = lock.getThreads();
				for (Thread thread : lockedThreads) {
					System.out.print(thread.getName()+" | ");
				}
			}
			System.out.println("\n Lock:fairness(是否公平锁):"+lock.isFair()+"、Locked(锁状态):"+lock.isLocked());
			TimeUnit.SECONDS.sleep(1);
		}

	}

}
