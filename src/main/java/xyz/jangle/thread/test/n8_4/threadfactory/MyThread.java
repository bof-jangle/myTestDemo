package xyz.jangle.thread.test.n8_4.threadfactory;

import java.util.Date;

/**
 *  自定义的线程
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月22日 下午5:22:24
 * 
 */
public class MyThread extends Thread {

	private final Date creationDate;

	private Date startDate;

	private Date finishDate;

	public MyThread(Runnable r, String name) {
		super(r, name);
		creationDate = new Date();
	}

	@Override
	public void run() {
		setStartDate(new Date());
		super.run();
		setFinishDate(new Date());
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public synchronized void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public synchronized long getExecutionTime() {
		return finishDate.getTime() - startDate.getTime();
	}

	@Override
	public synchronized String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(getName());
		buffer.append(": ");
		buffer.append(" Create :");
		buffer.append(creationDate);
		buffer.append(": Running time : ");
		buffer.append(getExecutionTime());
		buffer.append(" Milliseconds.");
		return buffer.toString();
	}

}
