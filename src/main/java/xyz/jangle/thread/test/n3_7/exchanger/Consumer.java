package xyz.jangle.thread.test.n3_7.exchanger;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * 消费者对象 先同步交换，再消费。
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月13日 下午8:02:00
 * 
 */
public class Consumer implements Runnable {

	private List<String> buffer;

	private final Exchanger<List<String>> exchanger;

	public Consumer(List<String> buffer, Exchanger<List<String>> exchanger) {
		super();
		this.buffer = buffer;
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		for (int i = 1; i < 10; i++) {
			System.out.println("消费者开始第" + i + "轮消费");
			try {
				buffer = exchanger.exchange(buffer);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("消费者完成交换，交换后列表大小：" + buffer.size() + "，对象：" + buffer);
			System.out.println("消费者开始消费");
			for (int j = 0; j < 10; j++) {
				String string = buffer.get(0);
				System.out.println("消费：" + string);
				buffer.remove(0);
			}
		}

	}

}
