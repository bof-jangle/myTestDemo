package xyz.jangle.thread.test.n7_4.priorityblockingqueue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * 7.4、优先级排序的阻塞线程安全队列
 * 所有插入PriorityBlockingQueue的元素都必须实现Comparable接口，该接口需要重写compareTo方法，
 * 它比较当前对象与参数对象的优先级，如果对象的优先级更高，则返回-1，参数的优先级更高，则返回1，相同返回0。
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月12日 上午9:56:45
 * 
 */
public class M {

	public static void main(String[] args) {
		PriorityBlockingQueue<Event> queue = new PriorityBlockingQueue<>();
		Thread[] threads = new Thread[5];
		for (int i = 0; i < threads.length; i++) {
			var task = new Task(i, queue);
			threads[i] = new Thread(task);
		}
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
		// 等待5个线程执行完成
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("M: QueueSize:" + queue.size());
		for (int i = 0; i < threads.length * 1000; i++) {
			var event = queue.poll();
			System.out.println("Thread " + event.getThread() + ": Priority " + event.getPriority());
		}
		System.out.println("M: Queue Size : " + queue.size());
		System.out.println("M: End ... ");

	}

}
