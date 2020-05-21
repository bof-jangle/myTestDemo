package xyz.jangle.lock.test;

/**
 * Synchronized 块的 加锁， 条件， 通知 的相关DEMO
 * 
 * @author jangle E-mail: jangle@jangle.xyz
 * @version 2020年5月20日 下午6:08:54 类说明
 */
public class SynchronizedBlockTest {

	private static int i = 0;
	private static Object o = new Object();

	public static void main(String[] args) {
		testWait();
		try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		testNotifyAll();

	}

	/**
	 * 创建等待条件
	 */
	public static void testWait() {
		Runnable signal = () -> {
			synchronized (o) {
				while (i < 1) {
					System.out.println(Thread.currentThread().getName() + "：进入等待");
					try {
						o.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				while (i < 2) {
					i++;
					System.out.println(Thread.currentThread().getName() + "：获得运行权限");
				}
				System.out.println(Thread.currentThread().getName() + "：运行结束");
			}
		};
		Thread thread = new Thread(signal);
		thread.setName("等待线程");
		thread.start();
	}

	/**
	 * 进行通知
	 */
	public static void testNotifyAll() {
		synchronized (o) { // 先要拿到锁. 此处需要获取o对象的锁，否则 o.notifyAll()会出异常
			i++;
			System.out.println(Thread.currentThread().getName() + "：开始通知");
			o.notifyAll(); // 这个方法的调用需要依赖 synchronized (o) 需要先获取锁，再执行锁信号的通知。
		}
	}

}
