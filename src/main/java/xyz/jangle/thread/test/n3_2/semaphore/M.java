package xyz.jangle.thread.test.n3_2.semaphore;

/**
 * Semaphore DEMO 使用信号量控制对资源的N个副本的并发访问
 * 	该DEMO模拟20个任务，共享3个打印机的过程。并使其有序使用打印机。
 * 
 * 	Semaphore的功能类型的同步锁的机制，但其区别是，其并发执行的数量并不限制为1个线程。
 * 	由 new Semaphore(3)参数3进行设置同步数量。
 * 	由 semaphore.acquire()来进行获取，acquire(2)可获取2个信号量。
 * 	由 semaphore.release()来进行释放，release(2)可释放2个信号量。
 * 
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月8日 下午4:47:53
 * 
 */
public class M {

	public static void main(String[] args) {

		PrintQueue printQueue = new PrintQueue();
		for (int i = 0; i < 20; i++) {
			createThread(printQueue, i).start();
		}

	}

	/**
	 * 创建线程
	 * 
	 * @author jangle
	 * @time 2020年8月8日 下午5:21:23
	 * @param printQueue
	 * @return
	 */
	private static Thread createThread(PrintQueue printQueue, int i) {
		return new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + "开始执行");
			printQueue.printJob(new Object());
			System.out.println(Thread.currentThread().getName() + "完成执行");
		}, "Thread" + i);
	}

}
