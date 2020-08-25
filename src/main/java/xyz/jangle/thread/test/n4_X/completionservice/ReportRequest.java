package xyz.jangle.thread.test.n4_X.completionservice;

import java.util.concurrent.CompletionService;

/**
 * 	模拟生成报告的请求
 * 	该线程的任务是将生产动作提交给执行器进行生产执行。
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月25日 下午6:07:54
 * 
 */
public class ReportRequest implements Runnable {

	private final String name;

	private final CompletionService<String> service;

	public ReportRequest(String name, CompletionService<String> service) {
		this.name = name;
		this.service = service;
	}

	@Override
	public void run() {
		ReportGenerator reportGenerator = new ReportGenerator(name, "ReportTitle");
		// reportGenerator作为任务提交给执行器ExecutorCompletionService。
		// 任务完成之后ExecutorCompletionService会将结果添加到它的Queue中。其他线程调用它的poll方法可以获取到。
		// 详见源码
		service.submit(reportGenerator);

	}

}
