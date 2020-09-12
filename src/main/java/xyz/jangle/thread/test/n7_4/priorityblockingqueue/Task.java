package xyz.jangle.thread.test.n7_4.priorityblockingqueue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * 	任务类（为队列添加元素）
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月12日 上午10:01:01
 * 
 */
public class Task implements Runnable {

	// 身份ID
	private final int id;

	private final PriorityBlockingQueue<Event> queue;

	public Task(int id, PriorityBlockingQueue<Event> queue) {
		super();
		this.id = id;
		this.queue = queue;
	}

	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			Event event = new Event(id, i);
			queue.add(event);
		}
	}

}
