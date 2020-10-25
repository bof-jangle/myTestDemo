package xyz.jangle.thread.test.n9_5.testforkjoin;

import java.util.concurrent.RecursiveAction;

/**
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年10月24日 下午4:41:58
 * 
 */
@SuppressWarnings("serial")
public class Task extends RecursiveAction {

	private final int array[];

	private final int start, end;

	public Task(int[] array, int start, int end) {
		super();
		this.array = array;
		this.start = start;
		this.end = end;
	}

	@Override
	protected void compute() {
		if (end - start > 100) {
			int mid = (start + end) / 2;
			var task1 = new Task(array, start, mid);
			var task2 = new Task(array, mid, end);
			task1.fork();
			task2.fork();
			task1.join();
			task2.join();
		} else {
			for (int i = start; i < end; i++) {
				array[i]++;
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
