package xyz.jangle.thread.test.n8_6.schedule;

import java.util.concurrent.TimeUnit;

/**
 *  普通的任务
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月25日 下午4:02:28
 * 
 */
public class Task implements Runnable {

	@Override
	public void run() {
		System.out.println("Task: Begin.");
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Task: End.");
	}

}
