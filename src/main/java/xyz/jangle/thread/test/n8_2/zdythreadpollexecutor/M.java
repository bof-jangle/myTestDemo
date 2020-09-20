package xyz.jangle.thread.test.n8_2.zdythreadpollexecutor;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * 8.2、自定义一个ThreadPoolExecutor（自定义一个线程池）
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月20日 下午3:49:15
 * 
 */
public class M {

	public static void main(String[] args) {
		MyExecutor myExecutor = new MyExecutor(4, 8, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>(),
				new ConcurrentHashMap<Runnable, Date>());
		var results = new ArrayList<Future<String>>();
		for (int i = 0; i < 10; i++) {
			var task = new SleepTwoSecondsTask();
			Future<String> result = myExecutor.submit(task);
			results.add(result);
		}
		for (int i = 0; i < 5; i++) {
			try {
				String result = results.get(i).get();
				System.out.println("M:result" + i + ":" + result);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		myExecutor.shutdown();
		for (int i = 5; i < 10; i++) {
			try {
				String result = results.get(i).get();
				System.out.println("M:result" + i + ":" + result);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		try {
			myExecutor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("M:程序结束");

	}

}
