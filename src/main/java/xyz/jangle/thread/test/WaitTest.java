package xyz.jangle.thread.test;

/**
 * 测试单例对象的 单个条件对象在多线程情况下的使用状况
 * 主要测试在Spring中，单例的service对象能否很好的使用内部条件对象
 * 主要用于理解条件对象的使用。
 * 2020年5月17日 18:45:38
 * @author jangle
 *
 */
public class WaitTest {

	private static int i = 0;

	public static void main(String[] args) {
		WaitTest waitTest = new WaitTest();
		A a = waitTest.new A();		//创建唯一的一个对象
		Thread thread1 = createThread(a);
		Thread thread2 = createThread(a);
		Thread thread3 = createThread(a);
		Thread thread4 = createThread(a);	//a对象被多个线程共享

		thread2.setName("thread2");
		thread2.start();
		try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread3.setName("thread3");
		thread3.start();
		try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		i++;
		thread4.setName("thread4");
		thread4.start();
		thread1.setName("thread1");
		thread1.start();

	}

	/**
	 * 创建一个线程，委托a对象执行方法。 
	 * @param a
	 * @return
	 */
	public static Thread createThread(A a) {
		Runnable r = () -> {
			try {
				a.aMethod();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};
		return new Thread(r);
	}

	class A {
		synchronized void aMethod() throws InterruptedException {
			String name = Thread.currentThread().getName();
			String num = name.substring(name.length() - 1);
			while (i < Integer.valueOf(num)) {
				System.out.println(Thread.currentThread().getName() + "进入条件wait");
				wait();
			}
			i++;
			System.out.println(Thread.currentThread().getName() + "解除wait的线程，notifyAll");
			Thread.sleep(1000L);
			notifyAll();
		}
	}

}
