package xyz.jangle.thread.test.n4_8.cancel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 4.8、在执行器内取消任务 
 * 	提交一个无限循环的任务，等待2秒后取消该任务。
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月23日 下午5:23:33
 * 
 */
public class M {

	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newCachedThreadPool();
		Task task = new Task();
		System.out.println("Main:开始执行任务");
		Future<String> future = threadPool.submit(task);
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Main:取消任务");
		// 参数true 如果线程正在运行则进行中断处理。
		future.cancel(true);
		System.out.println("Main:isCancelled:" + future.isCancelled());
		System.out.println("Main:isDone:" + future.isDone());
		threadPool.shutdown();
		System.out.println("Main:结束执行任务");

	}

}
