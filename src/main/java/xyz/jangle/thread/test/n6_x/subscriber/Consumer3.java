package xyz.jangle.thread.test.n6_x.subscriber;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

/**
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月9日 下午4:59:27
 * 
 */
public class Consumer3 implements Subscriber<Item> {
	
	private Subscription subscription;

	@Override
	public void onSubscribe(Subscription subscription) {
		// 当该对象被订阅到发布者中时，该方法被调用
		System.out.println(Thread.currentThread().getName()+":Consumer 3:收到订阅（subscription）无Item请求");
		this.subscription = subscription;
		// 该订阅者只请求3个
		this.subscription.request(3);
	}

	@Override
	public void onNext(Item item) {
		// 发布者有新元素被请求时，该方法被调用。
		System.out.println(Thread.currentThread().getName()+":Consumer 3:onNext方法： "+item.getTitle()+","+item.getContent());
	}

	@Override
	public void onError(Throwable throwable) {
		System.out.println(Thread.currentThread().getName()+":Consumer 3:异常");
		throwable.printStackTrace(System.err);
	}

	@Override
	public void onComplete() {
		System.out.println(Thread.currentThread().getName()+":Consumer 3:完成");
	}
	
	public void request(long n) {
		this.subscription.request(n);
	}

}
