package xyz.jangle.thread.test.n8_x.prioritytransfer;

import java.util.concurrent.TimeUnit;

/**
 *  8.10、实现一个基于优先级的传递队列
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年10月9日 上午10:48:06
 * 
 */
public class M {

	public static void main(String[] args) throws Exception {

		var buffer = new MyPriorityTransferQueue<Event>();
		var producer = new Producer(buffer);
		var producerThreads = new Thread[10];
		for (int i = 0; i < producerThreads.length; i++) {
			producerThreads[i] = new Thread(producer);
			producerThreads[i].start();
		}
		var consumer = new Consumer(buffer);
		var consumerThread = new Thread(consumer);
		consumerThread.start();
		System.out.println("M:Buffer:consumer count:" + buffer.getWaitingConsumerCount());
		// 使用transfer传递事件给消费者。
		var event = new Event("Core Event", 10);
		buffer.transfer(event);
		System.out.println("Main: 使用transfer传输事件对象");
		for (int i = 0; i < producerThreads.length; i++) {
			try {
				producerThreads[i].join();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		TimeUnit.SECONDS.sleep(1);
		System.out.println("M:Buffer : Consumer count:"+buffer.getWaitingConsumerCount());
		event = new Event("Core Event 2 ", 0);
		buffer.transfer(event);
		consumerThread.join();
		System.out.println("M:程序结束");

	}

}
