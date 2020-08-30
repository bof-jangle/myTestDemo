package xyz.jangle.thread.test.n5_6.cancel;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * 查找的任务
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月30日 下午6:26:04
 * 
 */
public class SearchNumberTask extends RecursiveTask<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int numbers[];

	private int start, end;

	private int number;

	private TaskManager manager;

	private final static int NOT_FOUND = -1;

	public SearchNumberTask(int[] numbers, int start, int end, int number, TaskManager manager) {
		super();
		this.numbers = numbers;
		this.start = start;
		this.end = end;
		this.number = number;
		this.manager = manager;
	}

	@Override
	protected Integer compute() {
		System.out.println("Task:" + start + ":" + end);
		int ret;
		if (end - start > 10) {
			// 分割
			ret = launchTasks();
		} else {
			// 执行
			ret = lookForNumber();
		}
		return ret;
	}

	/**
	 * 执行查找，找到后取消所有其他任务。
	 * 
	 * @author jangle
	 * @time 2020年8月30日 下午6:38:47
	 * @return
	 */
	private int lookForNumber() {
		for (int i = start; i < end; i++) {
			if (numbers[i] == number) {
				System.out.println("Task:number找到了，下标为" + i);
				manager.cancelTasks(this);
				return i;
			}
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return NOT_FOUND;
	}

	/**
	 * 进行分治
	 * 
	 * @return
	 */
	private int launchTasks() {
		int mid = (start + end) / 2;
		SearchNumberTask task = new SearchNumberTask(numbers, start, mid, number, manager);
		SearchNumberTask task2 = new SearchNumberTask(numbers, mid, end, number, manager);
		manager.addTask(task);
		manager.addTask(task2);
		task.fork();
		task2.fork();
		int returnValue;
		returnValue = task.join();
		if (returnValue != -1) {
			return returnValue;
		} else {
			return task2.join();
		}
	}

	/**
	 * 取消任务时的日志信息
	 */
	public void logCancelMessage() {
		System.out.println("Task:取消了下标从" + start + "到" + end + "的任务");
	}

}
