package xyz.jangle.thread.test.n4_5.invokeall;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 *  任务（实现Callable接口的任务）
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月20日 下午8:00:45
 * 
 */
public class Task implements Callable<Result> {

	private final String name;

	public Task(String name) {
		super();
		this.name = name;
	}

	@Override
	public Result call() throws Exception {
		System.out.println(this.name + "开始...");
		try {
			long duration = (long) (Math.random() * 10);
			System.out.println(this.name + ":等待" + duration + "秒，以获取结果");
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int value = 0;
		for (int i = 0; i < 5; i++) {
			value += (Math.random() * 100);
		}
		Result result = new Result();
		result.setName(name);
		result.setValue(value);
		System.out.println(this.name + "结束...");
		return result;
	}

}
