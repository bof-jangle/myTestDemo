package xyz.jangle.thread.test.n7_5.delayqueue;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 	延迟队列的元素类
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月13日 下午8:03:24
 * 
 */
public class Event implements Delayed {

	private final Date startDate;

	public Event(Date startDate) {
		super();
		this.startDate = startDate;
	}

	@Override
	public int compareTo(Delayed o) {
		long result = this.getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
		if (result < 0)
			return -1;
		if (result > 0)
			return 1;
		return 0;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		Date now = new Date();
		long diff = startDate.getTime() - now.getTime();
		// 由毫秒转换为纳秒
		return unit.convert(diff,TimeUnit.MICROSECONDS);
	}

}
