package xyz.jangle.thread.test.n7_2.concurrentlinkeddeque;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 *  添加元素的任务
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月10日 下午7:22:43
 * 
 */
public class AddTask implements Runnable {

	private final ConcurrentLinkedDeque<String> list;

	public AddTask(ConcurrentLinkedDeque<String> list) {
		super();
		this.list = list;
	}

	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		for (int i = 0; i < 10000; i++) {
			list.add(name +":Element "+i);
		}
	}

}
