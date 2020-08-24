package xyz.jangle.thread.test.n4_9.futuretask;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 	普通的可执行的任务
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月24日 下午7:27:46
 * 
 */
public class ExecutableTask implements Callable<String> {

	private final String name;

	public String getName() {
		return name;
	}

	public ExecutableTask(String name) {
		super();
		this.name = name;
	}

	@Override
	public String call() throws Exception {
		try {
			long duration = (long) (Math.random() * 10);
			System.out.println(this.name + ":等待" + duration + "秒");
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
//			e.printStackTrace();
		}

		return "hello, I am " + this.name;
	}

}
