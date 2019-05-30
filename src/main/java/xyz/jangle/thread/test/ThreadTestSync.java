package xyz.jangle.thread.test;

/**
 * 同步的自增计数，在多线程时，不会丢失i100，不存在重复i值
 * @author jangle E-mail: jangle@jangle.xyz
 * @version 2019年5月30日 类说明
 */
public class ThreadTestSync extends Thread {

	public static Integer i = 0;		// 用于自增计数

	private int a;

	public ThreadTestSync(int a) {
		super();
		this.a = a;
	}

	@Override
	public void run() {
		synchronized (i) {
			System.out.println("a=" + a + "这是一个单独线程的输出i" + i++);
		}
		super.run();
	}

	public static void main(String[] args) {
		for (int a = 0; a <= 100; a++) {
			new ThreadTestSync(a).start();
		}
	}

}
