package xyz.jangle.thread.test.n3_8.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 	生成1-10之間的數字，然後調用complete()方法完成completableFuture。
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月14日 下午7:25:10
 * 
 */
public class SeedGenerator implements Runnable {

	private CompletableFuture<Integer> resultCommunicator;

	public SeedGenerator(CompletableFuture<Integer> resultCommunicator) {
		super();
		this.resultCommunicator = resultCommunicator;
	}

	@Override
	public void run() {
		System.out.println("生成种子");
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int seed = (int) Math.rint(Math.random()*10);
		System.out.println("seed:"+seed);
		resultCommunicator.complete(seed);
	}
	
}
