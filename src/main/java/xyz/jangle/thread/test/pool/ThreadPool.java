package xyz.jangle.thread.test.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * 线程池
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年7月20日 上午11:56:06
 * 
 */
public class ThreadPool {

	public static void main(String[] args) {
		Runnable task = null;
		Object result = null;

		/*
		 * 创建一个线程池，该线程池重用固定数量的线程，并从共享的无边界队列运行。在任何时候，最多有nThreads线程处于活动处理状态任务。
		 * 如果当所有线程都处于活动状态时，它们将在队列中等待，直到有一个线程处于活动状态可用。如果任何线程在执行过程中由于失败而终止在关闭之前，如果需要执行后续任务
		 * ，将由一个新的线程代替它。池中的线程将一直存在，直到显式关闭。
		 */
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4);
		fixedThreadPool.submit(task, result);
		fixedThreadPool.submit(task);
		fixedThreadPool.submit(task);

		// 缓冲线程池
		/*
		 * 创建一个线程池，该线程池根据需要创建新线程，但在以前构建的线程可用时将重用这些线程。这些池通常会提高执行许多短期异步的程序的性能任务。
		 * 通话执行将重用以前构造的线程（如果可用）。如果没有可用的线程，将创建一个newthread并将其添加到池中。60秒未使用的线程将被终止并从缓存中删除。因此
		 * ，一个空闲时间足够长的池将不会消耗任何资源。请注意，可以使用ThreadPoolExecutor构造函数创建具有相似属性但不同详细信息（例如，超时参数）
		 * 的池。
		 */
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

		/*
		 * 创建一个执行器，该执行器在无界队列下使用单个工作线程操作。（但是请注意，如果这个单线程在执行期间在关闭之前由于失败而终止，则在需要执行子任务时，
		 * 将替换一个新的线程。）任务被保证按顺序执行，并且在任何给定的时间都不会有多个任务处于活动状态。与其他等效的newFixedThreadPool（1）不同，
		 * 返回的执行器保证不会被重新配置以使用其他线程。
		 */
		ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

	}

}
