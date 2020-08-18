package xyz.jangle.thread.test.n4_3.callable0future;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 	4.3、在一个执行器中执行任务，并返回结果。
 * 	案例功能：创建10个阶乘任务保存执行结果Future对象，并等待执行完毕，最终把执行结果输出。
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月18日 下午2:49:10
 * 
 */
public class M {

	public static void main(String[] args) {
		var executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
		var result = new ArrayList<Future<Integer>>();
		var random = new Random();
		//	创建10个阶乘计算的对象，提交给执行器执行，并把返回结果存到List中。
		for (int i = 0; i < 10; i++) {
			int number = random.nextInt(10);
			FactorialCalculator factorialCalculator = new FactorialCalculator(number);
			Future<Integer> future = executorService.submit(factorialCalculator);
			result.add(future);
		}
		// 等待所有线程执行完毕
		do {
			System.out.println("Main: 线程完成数量：" + executorService.getCompletedTaskCount());
			for (int i = 0; i < result.size(); i++) {
				Future<Integer> future = result.get(i);
				System.out.println("Main:Task " + i + ":" + future.isDone());
			}
			try {
				TimeUnit.MILLISECONDS.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (executorService.getCompletedTaskCount() < result.size());
		System.out.println("Main:每个线程的执行结果：");
		// 輸出每个线程的执行结果
		for (int i = 0; i < result.size(); i++) {
			Future<Integer> future = result.get(i);
			Integer number = null;
			try {
				number = future.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
			System.out.println("Main:Task" + i + ":" + number);
		}
		// 停止执行器
		executorService.shutdown();

	}

}
