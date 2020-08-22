package xyz.jangle.thread.test.n4_7.schedulefixed;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 4.7、scheduleAtFixedRate() 在执行器内周期性地运行任务
 * 
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月22日 下午6:42:45
 * 
 */
public class M {

	public static void main(String[] args) {
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);
		/*
		ScheduledThreadPoolExecutor p = (ScheduledThreadPoolExecutor) scheduledThreadPool;
		// 如果设置策略，在shutdown之后，继续执行周期性任务，则线程不会因为shutdown()方法的调用而停止。
		p.setContinueExistingPeriodicTasksAfterShutdownPolicy(true);
		*/
		System.out.println("Main:开始运行..." + new Date());
		var task = new Task("Task");
		// 在task开始运行时，等待2秒，执行task（这样，如果task运行时间超过2秒的话，就会由N个任务重复运行）
		ScheduledFuture<?> result = scheduledThreadPool.scheduleAtFixedRate(task, 1, 2, TimeUnit.SECONDS);
		// 在task结束运行后，等待2秒，再次执行task（这样，保证每次只有一个task在运行）
//		ScheduledFuture<?> result = scheduledThreadPool.scheduleWithFixedDelay(task, 1, 2, TimeUnit.SECONDS);
		for (int i = 0; i < 10; i++) {
			System.out.println("Main:Delay " + result.getDelay(TimeUnit.MILLISECONDS));
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("是否完成："+result.isDone());
		System.out.println("是否取消"+result.isCancelled());
		// 默认情况下，shutdown会结束周期性任务（除非进行策略设置）
		scheduledThreadPool.shutdown();
		System.out.println("是否完成："+result.isDone());
		System.out.println("是否取消"+result.isCancelled());
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Main:结束运行..."+new Date());

	}

}
