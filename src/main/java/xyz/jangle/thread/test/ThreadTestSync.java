package xyz.jangle.thread.test;

/**
 * 同步的自增计数，在多线程时，不会丢失i100，不存在重复i值
 * @author jangle E-mail: jangle@jangle.xyz
 * @version 2019年5月30日 类说明
 */
public class ThreadTestSync extends Thread {

	public static Integer i = 0;		// 用于自增计数

	public static Object lock = new Object();
	
	private int a;

	public ThreadTestSync(int a) {
		super();
		this.a = a;
	}

	@Override
	public void run() {
//		synchronized (lock) {	//这个锁可以进行方法的同步，每秒进行一次输出，并且线程不是顺序的。
		synchronized (i) {		//ERROR 这个锁并无效果
			System.out.println("a=" + a + "这是一个单独线程的输出i" + i++);
			try {
				Thread.sleep(1000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		super.run();
	}

	public static void main(String[] args) {
		for (int a = 0; a <= 100; a++) {
			new ThreadTestSync(a).start();
		}
	}

}
