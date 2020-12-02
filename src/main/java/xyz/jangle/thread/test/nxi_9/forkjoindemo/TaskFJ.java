package xyz.jangle.thread.test.nxi_9.forkjoindemo;

import java.util.Date;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 *  11.9、一个简单易懂的fork/join框架demo。
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年11月30日 下午6:48:31
 * 
 */
public class TaskFJ extends RecursiveAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int array[];
	private final int start, end;

	public TaskFJ(int[] array, int start, int end) {
		super();
		this.array = array;
		this.start = start;
		this.end = end;
	}

	@Override
	protected void compute() {
		if (end - start > 1000) {
			// 拆分
			int mid = (start + end) / 2;
			var task1 = new TaskFJ(array, start, mid);
			var task2 = new TaskFJ(array, mid, end);
//			task1.fork();
//			task2.fork();
//			task1.join();
//			task2.join();
			invokeAll(task1, task2);
		} else {
			// 处理
			for (int i = start; i < end; i++) {
				array[i]++;
				System.out.println(Thread.currentThread().getName() + ":::" + i + ":::" + array[i]);
				try {
					TimeUnit.MILLISECONDS.sleep(20L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public static void main(String[] args) {
		int[] array = new int[10000];
		var taskFJ = new TaskFJ(array, 1, array.length);
		var pool = new ForkJoinPool();
		var start = new Date();
		pool.execute(taskFJ);
		pool.shutdown();
		try {
			pool.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		var end = new Date();
		System.out.println("耗時" + (end.getTime() - start.getTime()));
	}

}
