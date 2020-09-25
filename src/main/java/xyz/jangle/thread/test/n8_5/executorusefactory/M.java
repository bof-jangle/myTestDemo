package xyz.jangle.thread.test.n8_5.executorusefactory;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import xyz.jangle.thread.test.n8_4.threadfactory.MyTask;
import xyz.jangle.thread.test.n8_4.threadfactory.MyThreadFactory;

/**
 * 8.5、在Executor对象中使用ThreadFactory
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月23日 下午4:47:55
 * 
 */
public class M {

	public static void main(String[] args) throws Exception {
		// 1、创建线程工厂
		var factory = new MyThreadFactory("jangle");
		// 2、创建线程池（使用1创建的线程工厂）
		var executor = Executors.newCachedThreadPool(factory);
		var task = new MyTask();
		// 3、提交任务
		Future<?> thread = executor.submit(task);
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.DAYS);
		System.out.println("M: 线程信息："+thread);
		System.out.println("M:End...");
	}

}
