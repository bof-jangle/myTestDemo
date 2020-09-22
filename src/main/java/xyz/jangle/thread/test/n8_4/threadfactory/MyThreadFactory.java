package xyz.jangle.thread.test.n8_4.threadfactory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  线程工厂
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月22日 下午5:41:43
 * 
 */
public class MyThreadFactory implements ThreadFactory {

	private AtomicInteger counter;

	private String prefix;

	public MyThreadFactory(String prefix) {
		super();
		this.prefix = prefix;
		counter = new AtomicInteger(1);
	}

	@Override
	public Thread newThread(Runnable r) {
		var t = new MyThread(r, prefix + "-" + counter.getAndIncrement());
		return t;
	}

}
