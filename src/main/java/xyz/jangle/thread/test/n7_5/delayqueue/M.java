package xyz.jangle.thread.test.n7_5.delayqueue;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * 7.5、使用带延迟元素的线程安全队列DelayQueue
 * DelayQueue的元素必须实现Delayed接口
 * （Delayed接口继承自Comparable接口，故它也是个优先队列）
 * 
 * Delayed接口需要实现：getDelay方法返回延迟时间。
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月13日 下午8:02:58
 * 
 */
public class M {

	public static void main(String[] args) throws Exception {

		DelayQueue<Event> queue = new DelayQueue<>();
		Thread[] threads = new Thread[5];
		for (int i = 0; i < threads.length; i++) {
			var task = new Task(i + 1, queue);
			threads[i] = new Thread(task);
			threads[i].start();
		}
		for (int i = 0; i < threads.length; i++) {
			threads[i].join();
		}
		do {
			int counter = 0;
			Event event;
			do {
				event = queue.poll();
				if (event != null) {
					counter++;
				}
			} while (event != null);
			System.out.println(new Date()+":"+counter+" 。 Size:"+queue.size());
			TimeUnit.MILLISECONDS.sleep(500);
		} while (queue.size() > 0);

	}

}
