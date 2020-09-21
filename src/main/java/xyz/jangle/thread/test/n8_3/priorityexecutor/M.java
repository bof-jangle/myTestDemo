package xyz.jangle.thread.test.n8_3.priorityexecutor;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 	8.3、实现基于优先级的Executor类
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月21日 下午5:49:33
 * 
 */
public class M {

	public static void main(String[] args) throws Exception {
		// 使用优先队列来创建线程池
		var pool = new ThreadPoolExecutor(4, 4, 1, TimeUnit.SECONDS, new PriorityBlockingQueue<Runnable>());
		for (int i = 0; i < 10; i++) {
			var task = new MyPriorityTask("task" + i, i);
			pool.execute(task);
		}
		TimeUnit.SECONDS.sleep(1);
		for (int i = 10; i < 20; i++) {
			var task = new MyPriorityTask("task" + i, i);
			pool.execute(task);
		}
		pool.shutdown();
		pool.awaitTermination(1, TimeUnit.DAYS);
	}

}
