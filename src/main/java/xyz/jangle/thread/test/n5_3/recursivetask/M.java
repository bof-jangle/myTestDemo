package xyz.jangle.thread.test.n5_3.recursivetask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * 	5.3、合并任务的执行结果
 * 	模拟一个文档，在文档中查找匹配单词的数量。
 * 	每10行以内作为一个1级任务，该1级任务再细分为每一行的2级任务。
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月27日 下午6:13:34
 * 
 */
public class M {

	public static void main(String[] args) {
		DocumentMock mock = new DocumentMock();
		String[][] document = mock.generateDocument(100, 10000, "java");
		DocumentTask task = new DocumentTask(document, 0, 100, "java");
		// 获取默认的ForkJoinPool
		ForkJoinPool pool = ForkJoinPool.commonPool();
		pool.execute(task);
		do {
			System.out.println("***********************");
			System.out.println("Main:Active Thread活跃的线程数:" + pool.getActiveThreadCount());
			System.out.println("Main:Task Count任务数:" + pool.getQueuedTaskCount());
			System.out.println("Main:Steal Count线程窃取数量:" + pool.getStealCount());
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (!task.isDone());
		pool.shutdown();
		try {
			pool.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			System.out.println("Main:单词统计结果：" + task.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

	}

}
