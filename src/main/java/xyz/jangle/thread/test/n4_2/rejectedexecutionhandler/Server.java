package xyz.jangle.thread.test.n4_2.rejectedexecutionhandler;

/**
 * 	執行器包裝類
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月17日 下午4:08:45
 * 
 */

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Server {
	private final ThreadPoolExecutor executor;

	public Server() {
		super();
		System.out.println(Runtime.getRuntime().availableProcessors());
		this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		RejectedTaskHandler handler = new RejectedTaskHandler();
		this.executor.setRejectedExecutionHandler(handler);
	}

	/**
	 * 用於接收任務
	 * 
	 * @author jangle
	 * @time 2020年8月17日 下午4:14:49
	 * @param task 任務
	 */
	public void executeTask(Task task) {
		System.out.println("Server:一个新的任务达到"+task.getName());
		executor.execute(task);
		//当前线程数 随着任务增加而增加，不超过maximumPoolSize，即最大值8
		System.out.println("Server:PoolSize:" + executor.getPoolSize());
		//线程的核心数 （(保持活动状态的最小工作线程数）
		System.out.println("Server:CorePoolSize:" + executor.getCorePoolSize());
		//同时存在于池中的最大线程数。（记录PoolSize的最大值）
		System.out.println("Server:LargestPoolSize:" + executor.getLargestPoolSize());
		//最大线程数（保持活动状态的最大工作线程数）
		System.out.println("Server:MaximumPoolSize:" + executor.getMaximumPoolSize());
		//计划执行的任务总数大概值（因为任务在一直添加，会变动）
		System.out.println("Server:TaskCount:" + executor.getTaskCount());
		//当前活动线程的大概值（因为活动线程数是变动的，但值不超過PoolSize ）
		System.out.println("Server:ActiveCount:" + executor.getActiveCount());
		//已完成的任务数（这是个近似值，但只增不减）
		System.out.println("Server:CompletedTasks:" + executor.getCompletedTaskCount());

	}

	public void endServer() {
		System.out.println("开始停止");
		executor.shutdown();
	}

}
