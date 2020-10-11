package xyz.jangle.thread.test.n8_9.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 *  
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年10月7日 下午7:42:38
 * 
 */
public class MyLock implements Lock {

	private final AbstractQueuedSynchronizer sync;

	public MyLock() {
		super();
		sync = new MyAbstractQueuedSynchronizer();
	}

	@Override
	public void lock() {
		sync.acquire(1);
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		sync.acquireInterruptibly(1);
	}

	@Override
	public boolean tryLock() {
		try {
			return sync.tryAcquireNanos(1, 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
			return false;
		}
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return sync.tryAcquireNanos(1, TimeUnit.NANOSECONDS.convert(time, unit));
	}

	@Override
	public void unlock() {
		sync.release(1);
	}

	@Override
	public Condition newCondition() {
		return sync.new ConditionObject();
	}

}
