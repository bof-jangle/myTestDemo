package xyz.jangle.thread.test.n5_5.exception;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 *  5.5、在任务中抛出异常
 *  isCompletedAbnormally()，任务或子任务是否抛出异常。 
 *  getException()获取抛出的异常。
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月29日 下午5:58:32
 * 
 */
public class M {

	public static void main(String[] args) {
		int[] array = new int[100];
		Task task = new Task(array, 0, 100);
		ForkJoinPool pool = new ForkJoinPool();
		pool.execute(task);
		pool.shutdown();
		try {
			pool.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(task.isCompletedAbnormally()) {
			System.out.println("Main:存在异常");
			System.out.println("Main:"+task.getException());
		}
		// 任务中如果抛出非受检异常join方法会抛出RuntimeException异常。
		System.out.println("Main:结果："+task.join());
		/*
		 * try { // 任务中如果抛出非受检异常get方法会抛出ExecutionException异常。
		 * System.out.println("Main:结果："+task.get()); } catch (InterruptedException |
		 * ExecutionException e) { e.printStackTrace(); }
		 */

	}

}
