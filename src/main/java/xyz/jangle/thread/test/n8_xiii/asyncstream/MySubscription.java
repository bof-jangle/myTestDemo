package xyz.jangle.thread.test.n8_xiii.asyncstream;

import java.util.concurrent.Flow.Subscription;

/**
 *  订阅
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年10月16日 下午3:37:50
 * 
 */
public class MySubscription implements Subscription {

	private boolean canceled = false;

	private long requested = 0;

	@Override
	public void request(long n) {
		requested += n;
	}

	@Override
	public void cancel() {
		canceled = true;
	}

	public boolean isCanceled() {
		return canceled;
	}

	public long getRequested() {
		return requested;
	}

	public void decreaseRequested() {
		requested--;
	}

}
