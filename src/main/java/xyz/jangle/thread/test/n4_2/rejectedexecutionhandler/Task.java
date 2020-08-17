package xyz.jangle.thread.test.n4_2.rejectedexecutionhandler;

/**
 * 	普通的任務綫程
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月17日 下午3:55:52
 * 
 */

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Task implements Runnable {

	private final Date initDate;
	private final String name;

	public Task(String name) {
		super();
		this.initDate = new Date();
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + "，創建時間：" + initDate);
		System.out.println(Thread.currentThread().getName() + "，執行時間：" + new Date());
		try {
			TimeUnit.SECONDS.sleep((long) (Math.random() * 10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "，任務" + name + ",完成時間" + new Date());

	}

	public String getName() {
		return name;
	}

}
