package xyz.jangle.thread.test.n7_2.concurrentlinkeddeque;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * 消费队列元素的类
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月10日 下午7:24:53
 * 
 */
public class PollTask implements Runnable {

	private final ConcurrentLinkedDeque<String> list;

	public PollTask(ConcurrentLinkedDeque<String> list) {
		super();
		this.list = list;
	}

	@Override
	public void run() {
		for (int i = 0; i < 5000; i++) {
			list.pollFirst();
			list.pollLast();
		}
	}

}
