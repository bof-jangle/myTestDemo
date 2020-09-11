package xyz.jangle.thread.test.n7_3.linkedblockingdeque;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * 	给队列写入元素的任务类
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月11日 下午8:39:36
 * 
 */
public class Client implements Runnable {

	private final LinkedBlockingDeque<String> requestList;

	public Client(LinkedBlockingDeque<String> requestList) {
		super();
		this.requestList = requestList;
	}

	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 5; j++) {
				StringBuilder b = new StringBuilder();
				b.append(i);
				b.append(":");
				b.append(j);
				try {
					requestList.put(b.toString());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Client: 添加内容： " + b.toString() + "。当前时间：." + new Date());
			}
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Client: End");
	}

}
