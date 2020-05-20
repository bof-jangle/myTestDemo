package xyz.jangle.lock.test;

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
			Thread thread = getThread();
			thread.setName("线程"+i);
			thread.start();
		}
		System.out.println("10个线程创建完毕");

	}

	/**
	 * 同步块的同步方法
	 */
	public static void syncBlockRun() {
		synchronized (lock) {
//		synchronized (i) {		//ERROR 这个锁并无效果，因为每次对象都在变化
			System.out.println(Thread.currentThread().getName()+"开始睡眠2秒。  i="+i++);
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
		return new Thread(r);
	}

}
