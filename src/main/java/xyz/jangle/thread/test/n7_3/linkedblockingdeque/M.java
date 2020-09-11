package xyz.jangle.thread.test.n7_3.linkedblockingdeque;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * 7.3、阻塞线程安全的双端队列LinkedBlockingDeque
 * put:添加，满元素则阻塞。
 * add:添加，满元素则异常。
 * take:获取，移除。无元素则阻塞。
 * pool:获取，移除。无元素则null
 * peek:读取，不移除。无元素则null
 * get:读取，不移除。无元素则异常
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月11日 下午8:38:57
 * 
 */
public class M {

	public static void main(String[] args) throws Exception {

		var list = new LinkedBlockingDeque<String>(3);
		Client client = new Client(list);
		Thread thread = new Thread(client);
		thread.start();

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 3; j++) {
				String string = list.take();
				System.out.println("M:获取并移除：" + string + "... 时间：" + new Date() + "。列表剩余大小：" + list.size());
			}
			TimeUnit.MILLISECONDS.sleep(300);
		}
		System.out.println("M:End");

	}

}
