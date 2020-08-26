package xyz.jangle.thread.test.n5_2.forkjoin;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * 5.2、创建一个fork/join池
 * 	实现一个任务来更新一组产品的价格，任务使用分治法，如果产品数量超过10个，则对其进行拆分。如果小于10个，则进行价格更新。
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月26日 下午5:02:26
 * 
 */
public class M {

	public static void main(String[] args) {
		ProductListGenerator generator = new ProductListGenerator();
		List<Product> products = generator.generate(10000);
		Task task = new Task(products, 0, products.size(), 0.2);
		ForkJoinPool pool = new ForkJoinPool();
		// 提交任务
		pool.execute(task);
		// 每5秒显示池中信息
		do {
			System.out.println("Main:当前线程数量（高估）Thread Count:" + pool.getActiveThreadCount());
			System.out.println("Main:线程窃取数量（低估）Thread Steal:" + pool.getStealCount());
			System.out.println("Main:并行级别Parallelism:" + pool.getParallelism());
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (!task.isDone());
		pool.shutdown();
		if (task.isCompletedNormally()) {
			System.out.println("Main:程序正常执行完毕");
		}
		// 检查是否所有的产品价格都进行了涨幅
		for (int i = 0; i < products.size(); i++) {
			Product product = products.get(i);
			if (product.getPrice() != 12) {
				System.out.println("Product " + product.getName() + ":" + product.getPrice());
			}
		}
		/*
		 * // 检查是否所有的产品价格都进行了涨幅(使用stream框架) products.stream().filter(product -> {
		 * if(product.getPrice() != 12) {
		 * System.out.println("Product "+product.getName()+":"+product.getPrice()); }
		 * return false; }).count();
		 */
		System.out.println("Main: 程序执行结束");
	}

}
