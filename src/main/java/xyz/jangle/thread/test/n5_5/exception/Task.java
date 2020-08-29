package xyz.jangle.thread.test.n5_5.exception;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月29日 下午5:59:22
 * 
 */
public class Task extends RecursiveTask<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int array[];
	private int start, end;

	public Task(int[] array, int start, int end) {
		super();
		this.array = array;
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		System.out.println("Task:开始执行从" + start + "到" + end);
		if (end - start < 10) {
			if (start < 3 && end > 3) {
				throw new RuntimeException("这个任务抛出了一个异常，任务下标从" + start + "到" + end);
				// 也可以使用这种方式抛出异常
//				completeExceptionally(new Exception("这个任务抛出了一个异常，任务下标从" + start + "到" + end));
			}
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			int mid = (end+start)/2;
			Task task = new Task(array, start, mid);
			Task task2 = new Task(array, mid, end);
			invokeAll(task, task2);
			System.out.println("Task:"+start+"--"+mid+":"+task.join());
			System.out.println("Task:"+mid+"--"+end+":"+task2.join());

		}
		System.out.println("Task:"+start+"--"+end+":结束");
		return 0;
	}

}
