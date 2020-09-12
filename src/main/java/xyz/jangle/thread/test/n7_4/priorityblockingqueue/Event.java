package xyz.jangle.thread.test.n7_4.priorityblockingqueue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * 	用于插入队列的元素类
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月12日 上午9:57:50
 * 
 */
public class Event implements Comparable<Event> {

	private final int thread;

	private final int priority;

	public Event(int thread, int priority) {
		super();
		this.thread = thread;
		this.priority = priority;
	}

	public int getThread() {
		return thread;
	}

	public int getPriority() {
		return priority;
	}

	@Override
	public int compareTo(Event o) {
		if (this.priority > o.getPriority())
			return -1;	// 对象比参数优先，返回-1 ， 否则返回1
		if (this.priority < o.getPriority())
			return 1;
		return 0;
	}
	
	public static void main(String[] args) {
		PriorityBlockingQueue<Event> queue = new PriorityBlockingQueue<Event>();
		Event event = new Event(1,2);
		Event event2 = new Event(3,4);
		queue.add(event);
		queue.add(event2);
		System.out.println(queue.poll().getPriority());
	}

}
