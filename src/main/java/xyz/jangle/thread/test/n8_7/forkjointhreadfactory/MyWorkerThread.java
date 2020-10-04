package xyz.jangle.thread.test.n8_7.forkjointhreadfactory;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;

/**
 *  自定义fork/join线程类
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年10月3日 下午5:34:00
 * 
 */
public class MyWorkerThread extends ForkJoinWorkerThread {

	// 用于计算执行了几个任务
	private final static ThreadLocal<Integer> taskCounter = new ThreadLocal<Integer>();

	protected MyWorkerThread(ForkJoinPool pool) {
		super(pool);
	}

	@Override
	protected void onStart() {
		super.onStart();
		System.out.println("MyWorkerThread: onStart  getId():" + getId());
		taskCounter.set(0);
	}

	@Override
	protected void onTermination(Throwable exception) {
		System.out.println("MyWorkerThread: onTermination " + getId() + ":" + taskCounter.get());
		super.onTermination(exception);
	}

	/**
	 *  增加任务计数。
	 */
	public void addTask() {
		taskCounter.set(taskCounter.get() + 1);
	}

}
