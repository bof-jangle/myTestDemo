package xyz.jangle.thread.test.n8_2.zdythreadpollexecutor;

import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 实现自定义的线程池
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月20日 下午3:50:29
 * 
 */
public class MyExecutor extends ThreadPoolExecutor {

	private final ConcurrentHashMap<Runnable, Date> startTimes;

	public MyExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue, ConcurrentHashMap<Runnable, Date> startTimes) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		this.startTimes = startTimes;
	}

	@Override
	public void shutdown() {
		System.out.println("shuwdown: 已完成：" + getCompletedTaskCount() + ",正在执行：" + getActiveCount() + ",队列中等待："
				+ getQueue().size());
		super.shutdown();
	}

	@Override
	public List<Runnable> shutdownNow() {
		System.out.println("shuwdownNow: 已完成：" + getCompletedTaskCount() + ",正在执行：" + getActiveCount() + ",队列中等待："
				+ getQueue().size());
		return super.shutdownNow();
	}

	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		System.out.println("beforeExecute:线程名称" + t.getName() + ",Runnable的哈希值" + r.hashCode());
		startTimes.put(r, new Date());
		super.beforeExecute(t, r);
	}

	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		Future<?> result = (Future<?>) r;
		try {
			System.out.println("************************");
			System.out.println("afterExecute：" + result.get());
			Date startTime = startTimes.remove(r);
			Date endTime = new Date();
			long diff = endTime.getTime() - startTime.getTime();
			System.out.println("MyExecutor:耗时：" + diff);
			System.out.println("************************");
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		super.afterExecute(r, t);
	}

}
