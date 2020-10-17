package xyz.jangle.thread.test.n8_xiii.asyncstream;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 8.13、自定义异步流（发布与订阅）
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年10月16日 下午3:19:33
 * 
 */
public class M {

	public static void main(String[] args) {
		var publisher = new MyPublisher();
		var consumer1 = new Consumer("Consumer 1");
		var consumer2 = new Consumer("Consumer 2");
		publisher.subscribe(consumer1);
		publisher.subscribe(consumer2);
		System.out.println("M:start");
		var news = new News();
		news.setTitle("My first news");
		news.setContent("This is the content");
		news.setDate(new Date());
		publisher.publish(news);
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		news = new News();
		news.setTitle("My second news");
		news.setContent("This is the content for second news");
		news.setDate(new Date());
		publisher.publish(news);
		System.out.println("M:end");
	}

}
