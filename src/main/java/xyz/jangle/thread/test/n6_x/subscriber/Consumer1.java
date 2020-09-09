package xyz.jangle.thread.test.n6_x.subscriber;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

/**
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月9日 下午4:19:44
 * 
 */
public class Consumer1 implements Subscriber<Item> {

	@Override
	public void onSubscribe(Subscription subscription) {
		// 发布者在调用subscribe()方法后，执行该方法
		System.out.println(Thread.currentThread().getName() + ":Consumer 1: 收到订阅（subscription）无Item请求");
	}

	@Override
	public void onNext(Item item) {
		// 发布者有新元素被请求时，该方法被调用。
		System.out.println(Thread.currentThread().getName() + ":Consumer 1:新增Item " + item.getTitle() + ",Content:"
				+ item.getContent());
	}

	@Override
	public void onError(Throwable throwable) {
		// 当出现错误必须通知订阅者时，该方法被调用
		System.out.println(Thread.currentThread().getName()+":Consumer 1:出现异常");
		throwable.printStackTrace();
	}

	@Override
	public void onComplete() {
		// 发布者在完成自己的任务之后，调用订阅者的onComplete()方法，也就是该方法。
		System.out.println(Thread.currentThread().getName()+":Consumer 1:完成执行");
	}

}
