package xyz.jangle.thread.test.n9_4.testexecutor;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 9.4、监测Executor类
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年10月24日 下午4:01:48
 * 
 */
public class M {

	public static void main(String[] args) throws InterruptedException {
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		var random = new Random();
		for (int i = 0; i < 10; i++) {
			var task = new Task(random.nextInt(10000));
			executor.submit(task);
		}
		for (int i = 0; i < 5; i++) {
			showLog(executor);
			TimeUnit.SECONDS.sleep(1);
		}
		executor.shutdown();
		for (int i = 0; i < 5; i++) {
			showLog(executor);
			TimeUnit.SECONDS.sleep(1);
		}
		executor.awaitTermination(1, TimeUnit.DAYS);
		System.out.println("M:End of the program.");
	}

	private static void showLog(ThreadPoolExecutor executor) {
		System.out.println("**************************");
		System.out.println("executor.getCorePoolSize(): " + executor.getCorePoolSize());
		System.out.println("executor.getPoolSize(): " + executor.getPoolSize());
		System.out.println("executor.getActiveCount(): " + executor.getActiveCount());
		System.out.println("executor.getTaskCount(): " + executor.getTaskCount());
		System.out.println("executor.getCompletedTaskCount(): " + executor.getCompletedTaskCount());
		System.out.println("executor.isShutdown(): " + executor.isShutdown());
		System.out.println("executor.isTerminating(): " + executor.isTerminating());
		System.out.println("executor.isTerminated(): " + executor.isTerminated());
		System.out.println("**************************");

	}

}
