package xyz.jangle.thread.test;

/**
 * 非同步的自增计数，在多线程时，会导致计数错误。丢失i100，存在重复i值
 * 
 * @author jangle E-mail: jangle@jangle.xyz
 * @version 2019年5月30日 类说明
 */
public class ThreadTest extends Thread {

	public static Integer i = 0; // 用于自增计数

	private int a;

	public ThreadTest(int a) {
		super();
		this.a = a;
	}

	@Override
	public void run() {
		System.out.println("a=" + a + "这是一个单独线程的输出i" + i++);
		super.run();
	}

	public static void main(String[] args) {
		for (int a = 0; a <= 100; a++) {
			new ThreadTest(a).start();
		}
	}

}
