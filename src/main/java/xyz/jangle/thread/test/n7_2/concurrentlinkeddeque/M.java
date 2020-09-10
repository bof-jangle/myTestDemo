package xyz.jangle.thread.test.n7_2.concurrentlinkeddeque;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * 7.2、非阻塞线程安全的双端队列
 * add：向队列尾部添加元素
 * pollFirst/pollLast：获取并删除队首/队尾元素
 * getFirst/getLast：获取（不删除）队首/队尾元素，如果无元素，则抛出异常NoSuchElementException
 * peekFirst/peekLast：获取（不删除）队首/队尾元素，如果无元素，则返回Null
 * remove/removeFirst/removeLast：删除队首/队首/队尾元素，如果无元素，则抛出异常NoSuchElementException
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月10日 下午7:21:39
 * 
 */
public class M {

	public static void main(String[] args) {
		var list = new ConcurrentLinkedDeque<String>();
		var threads = new Thread[100];
		for (int i = 0; i < threads.length; i++) {
			var task = new AddTask(list);
			threads[i] = new Thread(task);
			threads[i].start();
		}
		System.out.println("M:" + threads.length + "个线程往队列中添加元素");
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// 100 * 10000 个元素
		System.out.println("元素添加完毕，队列长度：" + list.size());
		System.out.println("开始创建消费线程");
		for (int i = 0; i < threads.length; i++) {
			var task = new PollTask(list);
			threads[i] = new Thread(task);
			threads[i].start();
		}
		System.out.println("M:" + threads.length + "个消费线程向队列获取元素");
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// 消费 100 * 2 * 5000 剩余0个元素
		System.out.println("M:消费完后，队列长度：" + list.size());

	}

}
