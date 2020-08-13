package xyz.jangle.thread.test.n3_7.exchanger;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * 生产者对象 先生产，再同步交换
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月13日 下午7:48:06
 * 
 */
public class Producer implements Runnable {

	private List<String> buffer;

	private final Exchanger<List<String>> exchanger;

	public Producer(List<String> buffer, Exchanger<List<String>> exchanger) {
		super();
		this.buffer = buffer;
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		for (int i = 1; i < 10; i++) {
			System.out.println("生产者第" + i + "期");
			for (int j = 0; j < 10; j++) {
				String msg = "" + (i * 10 + j);
				System.out.println("生产："+msg);
				buffer.add(msg);
			}
			try {
				buffer = exchanger.exchange(buffer);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("生产者：交换后的缓冲区列表大小：" + buffer.size() + "对象：" + buffer);
		}

	}

}
