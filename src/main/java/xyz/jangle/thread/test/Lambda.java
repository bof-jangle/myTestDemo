package xyz.jangle.thread.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 
 * 	线程间传参
 * 	
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年7月20日 上午10:56:27
 * 
 */
public class Lambda {

	public static void main(String[] args) {

		int n = 5;

		List<List<String>> list2 = new ArrayList<>();
		Collection<List<String>> list = Collections.synchronizedCollection(list2);

		List<Thread> lt = new ArrayList<Thread>();

		for (int i = 0; i < n; i++) {
			Thread thread = new Thread(() -> {
				List<String> l = new ArrayList<String>();
				l.add("1");
				l.add("2");
				list.add(l);
			});
			lt.add(thread);
			thread.start();
		}

		for (Thread t : lt) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(list);

	}

}
