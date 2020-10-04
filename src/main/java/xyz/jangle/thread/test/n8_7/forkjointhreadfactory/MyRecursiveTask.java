package xyz.jangle.thread.test.n8_7.forkjointhreadfactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * 分治任务类
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年10月3日 下午5:53:45
 * 
 */
public class MyRecursiveTask extends RecursiveTask<Integer> {

	private static final long serialVersionUID = 1L;

	private int array[];

	private int start, end;

	public MyRecursiveTask(int[] array, int start, int end) {
		super();
		this.array = array;
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		Integer ret;
		MyWorkerThread thread = (MyWorkerThread) Thread.currentThread();
		thread.addTask();
		if (end - start <= 100) {
			// 计算
			int add = 0;
			for (int i = start; i < end; i++) {
				add += array[i];
			}
			ret = add;
		} else {
			// 分治
			int mid = (start + end) / 2;
			var task1 = new MyRecursiveTask(array, start, mid);
			var task2 = new MyRecursiveTask(array, mid, end);
			invokeAll(task1, task2);
			ret = addResults(task1, task2);
		}
		try {
			TimeUnit.MILLISECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return ret;
	}

	private Integer addResults(MyRecursiveTask task1, MyRecursiveTask task2) {
		int value;
		try {
			value = task1.get().intValue() + task2.get().intValue();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			value = 0;
		}
		return value;
	}

}
