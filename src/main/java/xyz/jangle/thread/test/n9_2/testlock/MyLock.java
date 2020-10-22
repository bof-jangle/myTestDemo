package xyz.jangle.thread.test.n9_2.testlock;

import java.util.Collection;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年10月21日 下午9:22:34
 * 
 */
@SuppressWarnings("serial")
public class MyLock extends ReentrantLock {

	/**
	 * 
	 * 获取当前持锁线程名称
	 * @return
	 */
	public String getOwnerName() {
		if(this.getOwner() == null) {
			return "None";
		}
		return this.getOwner().getName();
	}
	/**
	 * 
	 * 获取当前线程队列
	 * @return
	 */
	public Collection<Thread> getThreads() {
		return this.getQueuedThreads();
	}
	
}
