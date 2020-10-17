package xyz.jangle.thread.test.n8_xiii.asyncstream;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;

/**
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年10月16日 下午4:16:54
 * 
 */
public class MyPublisher implements Publisher<News> {

	private ConcurrentLinkedDeque<ConsumerData> consumers;
	private ThreadPoolExecutor executor;

	public MyPublisher() {
		consumers = new ConcurrentLinkedDeque<ConsumerData>();
		executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	}

	@Override
	public void subscribe(Subscriber<? super News> subscriber) {
		ConsumerData consumerData = new ConsumerData();
		consumerData.setConsumer((Consumer) subscriber);
		var subscription = new MySubscription();
		consumerData.setSubscription(subscription);
		subscriber.onSubscribe(subscription);
		consumers.add(consumerData);
	}
	
	/**
	 *   发布元素（为每个订阅者创建一个线程并提交给执行器执行。
	 * @param news
	 */
	public void publish(News news) {
		consumers.forEach(consumerData -> {
			try {
				executor.execute(new PublisherTask(consumerData, news));
			} catch (Exception e) {
				consumerData.getConsumer().onError(e);
			}
		});
	}

}
