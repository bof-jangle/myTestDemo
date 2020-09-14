package xyz.jangle.thread.test.n7_6.concurrentskiplistmap;

import java.util.concurrent.ConcurrentSkipListMap;

/**
 * 	任务类（为ConcurrentSkipListMap添加元素）
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月14日 下午5:04:09
 * 
 */
public class Task implements Runnable {

	private final ConcurrentSkipListMap<String, Contact> map;

	private final String id;

	public Task(ConcurrentSkipListMap<String, Contact> map, String id) {
		super();
		this.map = map;
		this.id = id;
	}

	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			var contact = new Contact(id, String.valueOf(i + 1000));
			map.put(id + contact.getPhone(), contact);
		}

	}

}
