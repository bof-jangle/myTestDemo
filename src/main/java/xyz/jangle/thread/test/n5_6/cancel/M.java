package xyz.jangle.thread.test.n5_6.cancel;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * 5.6、取消一个任务
 * 	创建一个数组，用分治法对指定数字进行查找，若一个任务找到，则取消其他所有任务。
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月30日 下午5:55:19
 * 
 */
public class M {

	public static void main(String[] args) {
		
		ArrayGenerator generator = new ArrayGenerator();
		int[] array = generator.generateArray(1000);
		TaskManager manager = new TaskManager();
		ForkJoinPool pool = new ForkJoinPool();
		SearchNumberTask searchNumberTask = new SearchNumberTask(array, 0, 1000, 6, manager);
		pool.execute(searchNumberTask);
		pool.shutdown();
		try {
			pool.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Main:主程序執行結束");

	}

}
