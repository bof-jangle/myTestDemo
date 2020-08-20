package xyz.jangle.thread.test.n4_5.invokeall;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 	4.5 invokeAll() 运行N个任务并处理全部返回结果
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月20日 下午5:02:19
 * 
 */
public class M {

	public static void main(String[] args) {
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		ArrayList<Task> list = new ArrayList<Task>();
		// 模拟10个任务
		for (int i = 0; i < 10; i++) {
			Task task = new Task("Task-" + i);
			list.add(task);
		}
		List<Future<Result>> resultList = null;
		try {
			// invokeAll方法会被阻塞，直到所有任务完成。
			resultList = cachedThreadPool.invokeAll(list);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Main:shutdown");
		cachedThreadPool.shutdown();
		System.out.println("Main:打印结果");
		for (Future<Result> future : resultList) {
			try {
				Result result = future.get();
				System.out.println(result.getName() + "：" + result.getValue());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

	}

}
