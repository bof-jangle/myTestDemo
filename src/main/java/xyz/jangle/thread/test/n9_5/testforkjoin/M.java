package xyz.jangle.thread.test.n9_5.testforkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * 9.5、监测fork/join任务池
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年10月24日 下午4:34:27
 * 
 */
public class M {

	public static void main(String[] args) throws InterruptedException {

		ForkJoinPool pool = new ForkJoinPool();
		int array[] = new int[10000];
		var task = new Task(array, 0, array.length);
		pool.execute(task);
		while (!task.isDone()) {
			showLog(pool);
			TimeUnit.SECONDS.sleep(1);
		}
		pool.shutdown();
		pool.awaitTermination(1, TimeUnit.DAYS);
		showLog(pool);
		System.out.println("M:End ");

	}

	private static void showLog(ForkJoinPool pool) {
		System.out.println("*****************************");
		System.out.println("pool.getParallelism():" + pool.getParallelism());
		System.out.println("pool.getPoolSize():" + pool.getPoolSize());
		System.out.println("pool.getActiveThreadCount():" + pool.getActiveThreadCount());
		System.out.println("pool.getRunningThreadCount():" + pool.getRunningThreadCount());
		System.out.println("pool.getQueuedSubmissionCount():" + pool.getQueuedSubmissionCount());
		System.out.println("pool.getQueuedTaskCount():" + pool.getQueuedTaskCount());
		System.out.println("pool.hasQueuedSubmissions():" + pool.hasQueuedSubmissions());
		System.out.println("pool.getStealCount():" + pool.getStealCount());
		System.out.println("pool.isTerminated():" + pool.isTerminated());
		System.out.println("*****************************");

	}

}
