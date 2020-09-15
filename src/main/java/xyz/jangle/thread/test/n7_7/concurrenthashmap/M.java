package xyz.jangle.thread.test.n7_7.concurrenthashmap;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * 7.7、线程安全的HashMap(ConcurrentHashMap)
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月15日 下午5:20:15
 * 
 */
public class M {

	public static void main(String[] args) {

		var userHash = new ConcurrentHashMap<String, ConcurrentLinkedDeque<Operation>>();
		var hashFiller = new HashFiller(userHash);
		Thread[] threads = new Thread[10];
		for (int i = 0; i < 10; i++) {
			threads[i] = new Thread(hashFiller);
			threads[i].start();
		}
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Size:" + userHash.size());
		// 用10个并发线程打印map信息
		userHash.forEach(10, (k, v) -> System.out.println(Thread.currentThread().getName() + ":" + k + ":" + v.size()));
		// 用10个并发线程打印map信息
		userHash.forEachEntry(10, entry -> System.out
				.println(Thread.currentThread().getName() + ":" + entry.getKey() + ":" + entry.getValue().size()));
		// 用10个并发线程查找元素
		Operation search = userHash.search(10, (k, v) -> {
			for (Operation o : v) {
				if (o.getOperation().endsWith("1")) {
					return o;
				}
			}
			return null;
		});
		System.out.println("查找到结尾为1的元素" + search.getUser() + "," + search.getOperation() + "," + search.getTime());
		// 查找value列表大于10的user。
		ConcurrentLinkedDeque<Operation> search2 = userHash.search(10, (k, v) -> {
			if (v.size() > 10) {
				return v;
			}
			return null;
		});
		System.out.println("查找到长度大于10的元素：" + search2.getFirst().getUser() + ",size:" + search2.size());
		// 计算散列里面的value列表总值
		Integer totalSize = userHash.reduce(10, (k, v) -> v.size(), (n1,n2) -> n1+n2);
		System.out.println("散列里value列表总值："+totalSize);
		
	}

}
