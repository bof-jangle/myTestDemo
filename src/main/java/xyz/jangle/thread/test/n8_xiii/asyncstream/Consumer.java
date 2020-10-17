package xyz.jangle.thread.test.n8_xiii.asyncstream;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

/**
 * 订阅者（消费者）
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年10月16日 下午3:25:14
 * 
 */
public class Consumer implements Subscriber<News> {

	private Subscription subscription;

	private String name;

	public Consumer(String name) {
		super();
		this.name = name;
	}

	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		subscription.request(1);
		System.out.println(Thread.currentThread().getName() + ":onSubscribe() ******");
	}

	@Override
	public void onNext(News item) {
		System.out.println(Thread.currentThread().getName() + ":" + name + ":onNext() ******" + item);
		subscription.request(1);

	}

	@Override
	public void onError(Throwable throwable) {
		System.out.println(Thread.currentThread().getName() + ":onError() ******");
	}

	@Override
	public void onComplete() {
		System.out.println(Thread.currentThread().getName() + ":onComplete() ******");
	}

}
