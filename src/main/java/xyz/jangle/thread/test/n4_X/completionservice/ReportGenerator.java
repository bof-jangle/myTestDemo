package xyz.jangle.thread.test.n4_X.completionservice;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 	模拟生产报告的类
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月25日 下午6:00:49
 * 
 */
public class ReportGenerator implements Callable<String> {

	private final String sender;
	private final String title;

	public ReportGenerator(String sender, String title) {
		super();
		this.sender = sender;
		this.title = title;
	}

	@Override
	public String call() throws Exception {
		try {
			long duration = (long) (Math.random() * 10);
			System.out.println(this.sender + "_" + this.title + ":等待" + duration + "秒（模拟生产报告）");
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 返回报告结果
		String ret = sender + ":" + title;
		return ret;
	}

}
