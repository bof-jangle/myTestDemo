package xyz.jangle.thread.test.n8_8.forkjointask;

import java.util.Date;
import java.util.concurrent.ForkJoinTask;

/**
 * 自定义用于fork/join框架中的任务抽象类
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年10月6日 上午10:31:55
 * 
 */
public abstract class MyWorkerTask extends ForkJoinTask<Void> {

	private String name;

	private static final long serialVersionUID = 1L;

	public MyWorkerTask(String name2) {
		this.name = name2;
	}

	@Override
	public Void getRawResult() {
		return null;
	}

	@Override
	protected void setRawResult(Void value) {
	}

	@Override
	protected boolean exec() {
		var startDate = new Date();
		compute();
		var endDate = new Date();
		long diff = endDate.getTime() - startDate.getTime();
		System.out.println("MyWorkerTask: " + name + ":" + diff);
		return true;
	}

	/**
	 * 用于子类重写
	 */
	protected abstract void compute();

	public String getName() {
		return name;
	}

}
