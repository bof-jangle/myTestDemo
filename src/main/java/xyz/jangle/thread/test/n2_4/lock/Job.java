package xyz.jangle.thread.test.n2_4.lock;

/**
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年7月21日 下午10:08:09
 * 
 */
public class Job implements Runnable {

	private PrintQueue queue;

	public Job(PrintQueue queue) {
		super();
		this.queue = queue;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + "working");
		queue.printJob(new Object());
		System.out.println(Thread.currentThread().getName() + "worked");

	}

}
