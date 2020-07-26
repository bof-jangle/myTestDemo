package xyz.jangle.thread.test.n2_6.condition;

import java.util.concurrent.TimeUnit;

/**
 * 
 * 	2.6 在一个锁中使用2种条件
 *  生产者，即写入程序
 *  消费者，即读取程序
 *  缓冲，生产者和消费者的数据转中。
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年7月26日 上午7:39:31
 * 
 */
public class M {
	
	// 写进程的数量
	private static int wn = 1;
	// 读进程的数量
	private static int rn = 3;
	

	public static void main(String[] args) throws Exception {

		FileMock file = new FileMock(20, 5);
		Buffer buf = new Buffer(3);
		var sTime = System.currentTimeMillis();
		System.out.println(sTime);
		// 写入线程
		Thread[] wThreads = new Thread[wn];
		for (int i = 0; i < wn; i++) {
			Thread thread = new Thread(() -> {
				buf.setPendingLines(true);
				while (file.hasMoreLine()) {
					String line = file.getLine();
					buf.insert(line);
				}
				buf.setPendingLines(false);
			});
			thread.start();
			wThreads[i] = thread;
		}
		Thread[] rThreads = new Thread[rn];
		for (int i = 0; i < rn; i++) {
			Thread thread = new Thread(() -> {
				while (buf.hasPendingLines()) {
					buf.get();
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			thread.start();
			rThreads[i] = thread;
		}
		for(Thread w:wThreads) {
			w.join();
		}
		for(Thread r:rThreads) {
			r.join();
		}
		System.out.println(System.currentTimeMillis() - sTime);

	}

}
