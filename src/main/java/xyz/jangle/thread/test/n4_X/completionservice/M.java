package xyz.jangle.thread.test.n4_X.completionservice;

import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 4.10 CompletionService，在执行器内分离任务的启动并处理返回结果
 *  模拟两个请求，这2个请求将2个任务提交给执行器，由另外的处理线程对执行器的结果进行消费。
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月25日 下午6:00:16
 * 
 */
public class M {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		ExecutorCompletionService<String> service = new ExecutorCompletionService<String>(executor);
		// 创建2个请求
		ReportRequest aRequest = new ReportRequest("A", service);
		ReportRequest bRequest = new ReportRequest("B", service);
		Thread aThread = new Thread(aRequest);
		Thread bThread = new Thread(bRequest);
		// 创建1个处理报告结果的线程
		ReportProcessor processor = new ReportProcessor(service);
		Thread senderThread = new Thread(processor);

		System.out.println("Main:开始执行");
		aThread.start();
		bThread.start();
		senderThread.start();

		try {
			System.out.println("Main:等待生产报告的请求执行完毕（提交给执行器）");
			aThread.join();
			bThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Main:报告请求提交完毕，关闭执行器并等待执行器内的任务执行完");
		// important 此处是关闭执行器，而不是service
		executor.shutdown();
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 任务执行完毕后，让processor对结果进行处理。
		processor.stopProcessing();
		System.out.println("Main:执行完毕");

	}

}
