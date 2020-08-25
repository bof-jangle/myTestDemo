package xyz.jangle.thread.test.n4_X.completionservice;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 专门来处理报告的生产结果（类似与消费线程）
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月25日 下午6:16:12
 * 
 */
public class ReportProcessor implements Runnable {

	private final CompletionService<String> service;
	// 结束任务的开关
	private volatile boolean end;

	public ReportProcessor(CompletionService<String> service) {
		this.service = service;
		this.end = false;
	}

	@Override
	public void run() {
		System.out.println("End:" + end);
		while (!end) {
			try {
				// 获取结果队列的第一个元素，如果没有，则等待2秒，若等待2秒还没有，则返回null
				Future<String> future = this.service.poll(2, TimeUnit.SECONDS);
				if (future != null) {
					String report = future.get();
					System.out.println("ReportProcessor:report的结果：" + report);
				} else {
					System.out.println("future:" + future);
				}
			} catch (InterruptedException | ExecutionException e) {
				// ExecutionException 任务执行时，因为异常而结束的，对其进行获取结果操作时，会抛出ExecutionException
				e.printStackTrace();
			}
		}
		System.out.println("发送者:End");
	}

	public void stopProcessing() {
		System.out.println("结束process的运行");
		this.end = true;
	}

}
