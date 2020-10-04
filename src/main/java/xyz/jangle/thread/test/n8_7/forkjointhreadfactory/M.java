package xyz.jangle.thread.test.n8_7.forkjointhreadfactory;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 *  8.7、实现自定义的fork/join线程类（拓展fork/join线程类的功能）
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年10月3日 下午5:33:20
 * 
 */
public class M {

	public static void main(String[] args) throws Exception {
		// 创建线程工厂
		var factory = new MyWorkerThreadFactory();
		// 使用上述工厂，构建线程池
		var pool = new ForkJoinPool(4, factory, null, false);
		
		int array[] = new int[100000];
		for (int i = 0; i < array.length; i++) {
			array[i] = 1;
		}
		// 创建解决问题的任务对象
		var task = new MyRecursiveTask(array, 0, array.length);
		pool.execute(task);
		task.join();
		pool.shutdown();
		pool.awaitTermination(1, TimeUnit.DAYS);
		System.out.println("Main: resutl:"+task.get());
		System.out.println("Main:结束");

	}

}
