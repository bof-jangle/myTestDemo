package xyz.jangle.thread.test.n8_xiii.asyncstream;

/**
 *  订阅者信息实体
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年10月16日 下午4:03:35
 * 
 */
public class ConsumerData {

	private Consumer consumer;
	private MySubscription subscription;

	public Consumer getConsumer() {
		return consumer;
	}

	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}

	public MySubscription getSubscription() {
		return subscription;
	}

	public void setSubscription(MySubscription subscription) {
		this.subscription = subscription;
	}

}
