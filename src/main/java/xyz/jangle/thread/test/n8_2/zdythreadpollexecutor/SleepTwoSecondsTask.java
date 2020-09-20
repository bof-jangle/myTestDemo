package xyz.jangle.thread.test.n8_2.zdythreadpollexecutor;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 *  一个睡眠任务
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月20日 下午6:29:44
 * 
 */
public class SleepTwoSecondsTask implements Callable<String> {

	@Override
	public String call() throws Exception {
		TimeUnit.SECONDS.sleep(2);
		return new Date().toString();
	}

}
