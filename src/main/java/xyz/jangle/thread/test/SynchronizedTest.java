package xyz.jangle.thread.test;

/**
 * 
 * 测试同步块的锁效果
 * 
 * @author jangle E-mail: jangle@jangle.xyz
 * @version 2020年5月18日 下午2:46:57 类说明
 */
public class SynchronizedTest {

	static Object lock = new Object();
	static Integer i = 1;

	public static void main(String[] args) {

		for (int i = 0; i < 10; i++) {
			getThread().start();
			System.out.println("10个线程创建完毕");
		}

	}

	/**
	 * 同步块的同步方法
	 */
	public static void syncBlockRun() {
		synchronized (lock) {
//		synchronized (i) {	
			System.out.println(i++);
			try {
				Thread.sleep(2000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized static void syncMethod() {
		System.out.println(i++);
		try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取一个线程，该线程执行一个同步的方法。
	 * 
	 * @return
	 */
	public static Thread getThread() {

		Runnable r = () -> {
			SynchronizedTest.syncBlockRun();
		};
		System.out.println("创建了一个线程");
		return new Thread(r);
	}

}
