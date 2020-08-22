package xyz.jangle.thread.test.n4_7.schedulefixed;

import java.util.Date;

/**
 * 	普通的任务
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月22日 下午6:43:52
 * 
 */
public class Task implements Runnable {

	private final String name;

	public Task(String name) {
		super();
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println(this.name + "正在执行" + new Date());
	}

}
