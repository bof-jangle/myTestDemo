package xyz.jangle.thread.test.n2_4.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年7月21日 下午10:00:23
 * 
 */
public class PrintQueue {

	private Lock queueLock;

	public PrintQueue(boolean fair) {
		super();
		this.queueLock = new ReentrantLock(fair);
	}

	public void printJob(Object document) {
		queueLock.lock();
		Long l = (long) (Math.random() * 1000);
		try {
			Thread.sleep(l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			queueLock.unlock();
		}

	}

}
