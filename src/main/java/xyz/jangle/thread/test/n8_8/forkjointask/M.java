package xyz.jangle.thread.test.n8_8.forkjointask;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 *  8.8、自定义fork/join任务抽象类
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年10月4日 下午2:33:28
 * 
 */
public class M {

	public static void main(String[] args) throws Exception {
		int array[] = new int[10000];
		var pool = new ForkJoinPool();
		var task = new Task("Taskk", array, 0, array.length);
		pool.execute(task);
		pool.shutdown();
		pool.awaitTermination(1, TimeUnit.DAYS);
		System.out.println("Main:End...");

	}

}
