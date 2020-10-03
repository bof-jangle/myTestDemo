package xyz.jangle.thread.test.n8_6.schedule;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *  自定义在周期调度线程池中运行的任务类
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月25日 上午9:22:49
 * 
 */
public class MyScheduledTask<V> extends FutureTask<V> implements RunnableScheduledFuture<V> {
	// 原任务
	private RunnableScheduledFuture<V> task;
	// 线程池
	private ScheduledThreadPoolExecutor executor;
	// 运行周期
	private long period;
	// 用于周期任务（开始执行的时间）
	private long startDate;

	public MyScheduledTask(Runnable runnable, V result, RunnableScheduledFuture<V> task,
			ScheduledThreadPoolExecutor executor) {
		super(runnable, result);
		this.task = task;
		this.executor = executor;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		if (!isPeriodic()) {
			return task.getDelay(unit);
		} else {
			if (startDate == 0) {
				return task.getDelay(unit);
			} else {
				var now = new Date();
				long delay = startDate - now.getTime();
				long res = unit.convert(delay, TimeUnit.MILLISECONDS);
//				System.out.println("******delay:" + delay + ",res" + res);
				return res;
			}
		}
	}

	@Override
	public int compareTo(Delayed o) {
		return task.compareTo(o);
	}

	@Override
	public boolean isPeriodic() {
		return task.isPeriodic();
	}

	@Override
	public void run() {
		if (isPeriodic() && (!executor.isShutdown())) {
			var now = new Date();
			startDate = now.getTime() + period;
			executor.getQueue().add(this);
		}
		System.out.println("MyScheduledTask-Pre:" + new Date());
		System.out.println("MyScheduledTask: 是否是周期性任务：" + isPeriodic());
		super.runAndReset();
		System.out.println("MyScheduledTask-Post:" + new Date());
		System.out.println("*************************");
	}

	public void setPeriod(long period) {
		this.period = period;
	}

}
