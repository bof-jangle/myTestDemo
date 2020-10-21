package xyz.jangle.thread.test.n8_6.schedule;

import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *  自定义（继承重写）周期调度线程池
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月25日 下午3:15:44
 * 
 */
public class MyScheduledThreadPoolExecutor extends ScheduledThreadPoolExecutor {

	public MyScheduledThreadPoolExecutor(int corePoolSize) {
		super(corePoolSize);
	}

	// 该方法会被 schedule、scheduleAtFixedRate、scheduleWithFixedDelay 方法内部调用
	// 这个方法的任务是对原任务进行包装。
	// 包装后的任务必须是实现RunnableScheduledFuture接口的类（因为线程池需要判断是否是周期任务）
	@Override
	protected <V> RunnableScheduledFuture<V> decorateTask(Runnable runnable, RunnableScheduledFuture<V> task) {
		// 4个参数，1、将要在任务中执行的Runnable对象.2、任务返回的对象 3、被取代的任务 4、执行器
		return new MyScheduledTask<V>(runnable, null, task, this);
	}

	@Override
	public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
		// scheduleAtFixedRate 方法会调用decorateTask方法，所以会返回MyScheduledTask类型的对象
		ScheduledFuture<?> task = super.scheduleAtFixedRate(command, initialDelay, period, unit);
		MyScheduledTask<?> myTask = (MyScheduledTask<?>) task;
		myTask.setPeriod(TimeUnit.MILLISECONDS.convert(period, unit));
		return myTask;
	}

}
