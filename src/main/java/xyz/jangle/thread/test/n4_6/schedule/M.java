package xyz.jangle.thread.test.n4_6.schedule;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 4.6、schedule 在执行器内延迟执行任务
 * 	创建5个任务，分别延迟1 2 3 4 5 秒执行，然后等待任务完成。
 * 
 * 	注：如果希望在指定时间执行任务， 则计算当前到指定时间的间隔。
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月21日 下午7:00:39
 * 
 */
public class M {

	public static void main(String[] args) {

		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
/*
//		ScheduledThreadPoolExecutor threadPool = (ScheduledThreadPoolExecutor) scheduledExecutorService;
		// 设置false的情况下，会使得调用shutdown()后，未执行的延迟任务不再执行（默认是true，继续执行延迟的任务）
//		threadPool.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
*/
		System.out.println("Main:开始执行主程序" + new Date());
		for (int i = 0; i < 5; i++) {
			Task task = new Task("Task " + i);
			// 执行task任务，延迟i+1秒执行。
			scheduledExecutorService.schedule(task, i + 1, TimeUnit.SECONDS);
		}
		// shutdown 之后，是否继续执行延迟任务受属性executeExistingDelayedTasksAfterShutdown影响
		scheduledExecutorService.shutdown();
		try {
			// 等待所有任务执行完毕，最长等待2天时间。
			scheduledExecutorService.awaitTermination(2, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Main:结束执行" + new Date());
	}

}
