package xyz.jangle.thread.test.n7_5.delayqueue;

import java.util.Date;
import java.util.concurrent.DelayQueue;

/**
 * 为队列增加元素的任务类
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月13日 下午8:11:22
 * 
 */
public class Task implements Runnable {

	private final int id;

	private final DelayQueue<Event> queue;

	public Task(int id, DelayQueue<Event> queue) {
		super();
		this.id = id;
		this.queue = queue;
	}

	@Override
	public void run() {
		Date now = new Date();
		Date delay = new Date();
		delay.setTime(now.getTime() + (id * 1000));
		System.out.println("Thread " + id + "," + delay);
		for (int i = 0; i < 100; i++) {
			Event event = new Event(delay);
			queue.add(event);
		}
	}

}
