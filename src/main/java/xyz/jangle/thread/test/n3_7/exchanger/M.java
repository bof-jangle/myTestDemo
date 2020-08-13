package xyz.jangle.thread.test.n3_7.exchanger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * 3.7 Exchanger 两个并发任务间的数据交换
 * 	模拟1对1的生产者和消费者问题
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月13日 下午7:46:31
 * 
 */
public class M {

	public static void main(String[] args) {

		List<String> buffer1 = new ArrayList<>();
		List<String> buffer2 = new ArrayList<>();
		Exchanger<List<String>> exchanger = new Exchanger<List<String>>();
		Producer producer = new Producer(buffer1, exchanger);
		Consumer consumer = new Consumer(buffer2, exchanger);
		new Thread(producer).start();
		new Thread(consumer).start();

	}

}
