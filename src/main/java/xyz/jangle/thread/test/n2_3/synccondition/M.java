package xyz.jangle.thread.test.n2_3.synccondition;
/**
 * 
 * 	使用两个线程模拟生产者和消费者执行任务。
 * 	1、生产者将队列填充满之后会通知消费者进行消费。
 *  2、消费者将队列消费为空之后，会通知生产者生产。
 *  
 *  此处共同使用了EventStorage的对象锁，也就是synchronized方法锁，使用notify()方法进行通知。
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年7月16日 下午10:19:19
 * 
 */
public class M {

	public static void main(String[] args) {

		EventStorage e = new EventStorage();
		Producer p = new Producer(e);	// 生产者线程
		Consumer c = new Consumer(e);	// 消费者线程
		new Thread(p).start();
		new Thread(c).start();
		
	}

}
