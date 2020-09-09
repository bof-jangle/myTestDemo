package xyz.jangle.thread.test.n6_x.subscriber;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

/**
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月9日 下午4:43:31
 * 
 */
public class Consumer2 implements Subscriber<Item> {

	private Subscription subscription;

	@Override
	public void onSubscribe(Subscription subscription) {
		// 发布者在调用subscribe()方法后，执行该方法
		System.out.println(Thread.currentThread().getName() + ":Consumer 2: 收到订阅（subscription），请求一个Item");
		this.subscription = subscription;
		this.subscription.request(1);
	}

	@Override
	public void onNext(Item item) {
		// 发布者有新元素被请求时，该方法被调用。
		System.out.println(
				Thread.currentThread().getName() + ":Consumer 2:onNext方法：" + item.getTitle() + "," + item.getContent()+"...并请求下一个元素");
		// 该订阅者在此处迭代请求。
		this.subscription.request(1);
	}

	@Override
	public void onError(Throwable throwable) {
		System.out.println(Thread.currentThread().getName() + ":Consumer 2：异常");

	}

	@Override
	public void onComplete() {
		System.out.println(Thread.currentThread().getName() + ":Consumer 2:完成");
	}

}
