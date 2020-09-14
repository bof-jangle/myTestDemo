package xyz.jangle.thread.test.n7_6.concurrentskiplistmap;

import java.util.Map;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 *  7.5、线程安全的navigable map
 *  ConcurrentSkipListMap DEMO
 *  
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月14日 下午4:59:55
 * 
 */
public class M {

	public static void main(String[] args) {

		var map = new ConcurrentSkipListMap<String, Contact>();
		var threads = new Thread[26];
		int counter = 0;

		for (char i = 'A'; i <= 'Z'; i++) {
			var task = new Task(map, String.valueOf(i));
			threads[counter] = new Thread(task);
			threads[counter].start();
			counter++;
		}
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("M:map Size:" + map.size());
		Map.Entry<String, Contact> element;
		Contact contact;
		// 获取第一个元素
		element = map.firstEntry();
		contact = element.getValue();
		System.out.println("M: 第一个元素：" + contact.getName() + "," + contact.getPhone());
		System.out.println("M: 获取子集A1991 到B1001");
		// 获取子集
		ConcurrentNavigableMap<String, Contact> subMap = map.subMap("A1991", "B1001");
		do {
			element = subMap.pollFirstEntry();
			if (element != null) {
				contact = element.getValue();
				System.out.println(contact.getName() + ":" + contact.getPhone());
			}
		} while (element != null);

	}

}
