package xyz.jangle.thread.test.n8_8.forkjointask;

/**
 * 定义一个解决问题的任务（继承fork/join抽象类）
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年10月6日 上午10:37:43
 * 
 */
@SuppressWarnings("serial")
public class Task extends MyWorkerTask {

	private int array[];
	private int start, end;

	public Task(String name, int[] array, int start, int end) {
		super(name);
		this.array = array;
		this.start = start;
		this.end = end;
	}

	@Override
	protected void compute() {
		if (end - start > 100) {
			int mid = (start + end) / 2;
			var t1 = new Task(getName(), array, start, mid);
			var t2 = new Task(getName(), array, mid, end);
			invokeAll(t1, t2);
		} else {
			for (int i = start; i < end; i++) {
				array[i]++;
			}
		}
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
