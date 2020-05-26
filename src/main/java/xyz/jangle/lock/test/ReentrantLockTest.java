package xyz.jangle.lock.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** 
 * 重入锁 和 条件对象   加锁，等待，通知等操作的DEMO
 * 
 * 重入的含义是：不同的对象创建不同的ReentrantLock对象锁，那么他们互不干扰（- - ！ 这不是很正常吗）
* @author jangle E-mail: jangle@jangle.xyz
* @version 2020年5月20日 下午6:08:54 
* 类说明 
*/
public class ReentrantLockTest {
	
	private static Lock lock = new ReentrantLock();
	private static Condition condition = lock.newCondition();
	private static int i = 0;

	public static void main(String[] args) {
		testAwait();
		System.out.println("i="+i);
		try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		i++;
//		System.out.println("i="+i);
		try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		signalAll();

	}
	
	/**
	 * 创建等待条件
	 */
	public static void testAwait() {
		Runnable signal = () -> {
			lock.lock();
			try {
				while(i<1) {
					System.out.println(Thread.currentThread().getName()+"：进入等待");
					try {
						condition.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println(Thread.currentThread().getName()+"：获得运行权限 i="+i);
				while(i<2) {
					i++;
				}
			}finally {
				System.out.println(Thread.currentThread().getName()+"：运行结束");
				lock.unlock();
			}
		};
		Thread thread = new Thread(signal);
		thread.setName("等待线程");
		thread.start();
	}
	
	/**
	 * 进行通知
	 */
	public static void signalAll() {
		lock.lock();	//先要拿到锁
		try {
			i++;
			System.out.println(Thread.currentThread().getName()+"：开始通知");
			condition.signalAll(); // 这个方法的调用需要依赖lock.lock();  需要先获取锁，再执行锁信号的通知。
		} finally {
			lock.unlock();	//通知以后释放锁（否则被通知的家伙拿不到锁）
		}
	}

}
