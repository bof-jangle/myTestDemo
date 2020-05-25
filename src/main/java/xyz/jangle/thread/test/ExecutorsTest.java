package xyz.jangle.thread.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *	 缓存线程池测试
 * 
 * @author jangle E-mail: jangle@jangle.xyz
 * @version 2020年5月25日 上午8:59:42 类说明
 */
public class ExecutorsTest {

	protected static int i = 1;

	public static void main(String[] args) {
		ExecutorsTest exe = new ExecutorsTest();
		AClass aClass = exe.new AClass();
		aClass.setA("123");
		aClass.setB(2);

		ExecutorService pool = Executors.newCachedThreadPool();

		for(int i=0;i<100;i++) {
			Runnable task = createRunnable(aClass);
			pool.submit(task);
		}
		aClass.setA("789");
		System.out.println("main" + aClass.getA() + "" + aClass.getB());

		pool.shutdown();

	}

	public static Runnable createRunnable(AClass aClass) {
		return new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(3000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(aClass.getA() + "" + aClass.getB());
			}
		};

	}

	class AClass {
		private String a;
		private int b;

		public String getA() {
			return a;
		}

		public void setA(String a) {
			this.a = a;
		}

		public int getB() {
			return b;
		}

		public void setB(int b) {
			this.b = b;
		}

	}

}
