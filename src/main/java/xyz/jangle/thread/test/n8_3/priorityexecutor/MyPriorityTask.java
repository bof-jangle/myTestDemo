package xyz.jangle.thread.test.n8_3.priorityexecutor;

import java.util.concurrent.TimeUnit;

/**
 *  一个包含优先级的任务
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月21日 下午5:59:38
 * 
 */
public class MyPriorityTask implements Runnable, Comparable<MyPriorityTask> {

	private String name;

	private Integer priority;

	public MyPriorityTask(String name, Integer priority) {
		super();
		this.priority = priority;
		this.name = name;
	}

	@Override
	public int compareTo(MyPriorityTask o) {
		return Integer.compare(o.getPriority(), this.getPriority());
	}

	@Override
	public void run() {
		System.out.println("MyPriorityTask:" + name + ":" + priority);
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public Integer getPriority() {
		return priority;
	}

}
