package xyz.jangle.thread.test.n8_7.forkjointhreadfactory;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinPool.ForkJoinWorkerThreadFactory;
import java.util.concurrent.ForkJoinWorkerThread;

/**
 *  ForkJoin线程工厂
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年10月3日 下午5:43:11
 * 
 */
public class MyWorkerThreadFactory implements ForkJoinWorkerThreadFactory {

	@Override
	public ForkJoinWorkerThread newThread(ForkJoinPool pool) {
		return new MyWorkerThread(pool);
	}

}
