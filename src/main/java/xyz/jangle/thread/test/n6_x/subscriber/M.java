package xyz.jangle.thread.test.n6_x.subscriber;

import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

/**
 * 6.10、编程流（发布与订阅）
 * Flow.Publisher:发布者（提供一个方法来接收Subscriber）
 * Flow.Subscriber:订阅者（提供4个方法，分别用于：订阅完毕时，出现异常时，新元素被请求时，发布者注册订阅者时。）
 * Flow.Subscription:订阅（用于订阅者向发布者请求新元素）
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月9日 下午4:18:41
 * 
 */
public class M {

	public static void main(String[] args) {

		var consumer1 = new Consumer1();
		var consumer3 = new Consumer3();
		SubmissionPublisher<Item> publisher = new SubmissionPublisher<Item>();
		// 发布者添加订阅者（进行订阅操作）
		publisher.subscribe(consumer1);
		publisher.subscribe(new Consumer2());
		publisher.subscribe(consumer3);
		for (int i = 0; i < 10; i++) {
			// 发布者添加主题
			var item = new Item();
			item.setTitle("title " + i);
			item.setContent("这个是Item的内容" + i);
			publisher.submit(item);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// 订阅者3手动请求
		consumer3.request(3);
		// 再次发布主题元素
		publisher.submit(new Item("last title","last context"));
		publisher.close();
	}

}
