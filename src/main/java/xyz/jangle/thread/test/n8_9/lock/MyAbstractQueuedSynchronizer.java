package xyz.jangle.thread.test.n8_9.lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 *  	实现同步器
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年10月7日 下午7:37:11
 * 
 */
@SuppressWarnings("serial")
public class MyAbstractQueuedSynchronizer extends AbstractQueuedSynchronizer {

	private final AtomicInteger state;

	public MyAbstractQueuedSynchronizer() {
		super();
		state = new AtomicInteger(0);
	}

	@Override
	protected boolean tryAcquire(int arg) {
		return state.compareAndSet(0, 1);
	}

	@Override
	protected boolean tryRelease(int arg) {
		return state.compareAndSet(1, 0);
	}

}
