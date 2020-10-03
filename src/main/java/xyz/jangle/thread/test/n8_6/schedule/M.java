package xyz.jangle.thread.test.n8_6.schedule;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 	8.6、自定义在周期调度线程池中运行的任务类
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月25日 上午9:20:05
 * 
 */
public class M {

	public static void main(String[] args) throws Exception {
		// 使用自定义周期线程池
		MyScheduledThreadPoolExecutor executor = new MyScheduledThreadPoolExecutor(4);
		Task task = new Task();
		System.out.println("Main: " + new Date());
		executor.schedule(task, 1, TimeUnit.SECONDS);
		TimeUnit.SECONDS.sleep(6);
		task = new Task();
		System.out.println("Main:" + new Date());
		executor.scheduleAtFixedRate(task, 1, 3, TimeUnit.SECONDS);
		TimeUnit.SECONDS.sleep(10);
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.DAYS);
		System.out.println("Main:End of the program.");
	}

}
