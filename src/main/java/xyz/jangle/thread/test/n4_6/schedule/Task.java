package xyz.jangle.thread.test.n4_6.schedule;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 * 	普通的任务
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月21日 下午7:02:27
 * 
 */
public class Task implements Callable<String> {

	private final String name;

	public Task(String name) {
		super();
		this.name = name;
	}

	@Override
	public String call() throws Exception {
		System.out.println(this.name + "开始执行任务" + new Date());
		return "hello,schedule task";
	}

}
