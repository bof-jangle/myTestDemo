package xyz.jangle.thread.test.n8_4.threadfactory;

import java.util.concurrent.TimeUnit;

/**
 *  一个普通任务
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月22日 下午5:45:12
 * 
 */
public class MyTask implements Runnable {

	@Override
	public void run() {

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
