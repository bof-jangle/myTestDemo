package xyz.jangle.thread.test.n3_8.completablefuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 3.8 CompletableFuture 异步地完成和关联任务
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月14日 下午7:21:58
 * 
 */
public class M {

	public static void main(String[] args) {
		System.out.println("Main:start");
		// 1、实例化一个CompletableFuture对象
		CompletableFuture<Integer> seedFuture = new CompletableFuture<>();
		Thread seedThread = new Thread(new SeedGenerator(seedFuture));
		seedThread.start();
		System.out.println("开始获取seed");
		int seed = 0;
		try {
			seed = seedFuture.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println("Main: seed = " + seed);

		System.out.println("Main: 开始创建数字列表");
		NumberListGenerator task = new NumberListGenerator(seed);
		// 2、使用CompletableFuture的supplyAsync方法来创建CompletableFuture对象（使用1的结果）
		CompletableFuture<List<Long>> startFuture = CompletableFuture.supplyAsync(task);
		System.out.println("Main:第一步");
		// 2.1、使用completableFuture和lambda表达式异步执行任务，计算最接近1000的值
		CompletableFuture<Long> step1Future = startFuture.thenApplyAsync(list -> {
			System.out.println(Thread.currentThread().getName() + "任务1、最接近1000的数");
			long selected = 0;
			long selectedDistance = Long.MAX_VALUE;
			long distance;
			for (Long number : list) {
				distance = Math.abs(number - 1000);
				if (distance < selectedDistance) {
					selected = number;
					selectedDistance = distance;
				}
			}
			System.out.println(Thread.currentThread().getName() + "任务1、计算结果：" + selected);
			return selected;
		});
		System.out.println("Main:第二步");
		// 2.2、使用CompletableFuture和lambda表达式异步执行任务，计算最大值
		CompletableFuture<Long> step2Future = startFuture.thenApplyAsync(list -> {
			System.out.println(Thread.currentThread().getName() + "任务2、开始计算最大值");
			return list.stream().max(Long::compare).get();
		});
		// 2.2.1、对2.2的计算结果进行异步地输出
		CompletableFuture<Void> write2Future = step2Future.thenAccept(result -> {
			System.out.println(Thread.currentThread().getName() + "任务2、最大值结果：" + result);
		});
		System.out.println("Main:第三步");
		NumberSelector numberSelector = new NumberSelector();
		// 2.3、使用CompletableFuture和Function对象异步执行任务
		CompletableFuture<Long> step3Future = startFuture.thenApplyAsync(numberSelector);
		System.out.println("Main:等待这3步任务完成");
		CompletableFuture<Void> waitFuture = CompletableFuture.allOf(step1Future, write2Future, step3Future);
		CompletableFuture<Void> finalFuture = waitFuture.thenAcceptAsync(p -> {
			System.out.println(Thread.currentThread().getName() + "Main:案例结束");
		});
		finalFuture.join();

	}

}
