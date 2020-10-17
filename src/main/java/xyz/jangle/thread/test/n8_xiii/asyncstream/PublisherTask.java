package xyz.jangle.thread.test.n8_xiii.asyncstream;

/**
 *  发布任务类
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年10月16日 下午4:07:54
 * 
 */
public class PublisherTask implements Runnable {

	private ConsumerData consumerData;
	private News news;

	public PublisherTask(ConsumerData consumerData, News news) {
		super();
		this.consumerData = consumerData;
		this.news = news;
	}

	@Override
	public void run() {
		MySubscription subscription = consumerData.getSubscription();
		if (!subscription.isCanceled() && subscription.getRequested() > 0) {
			consumerData.getConsumer().onNext(news);
			subscription.decreaseRequested();
		}
	}

}
