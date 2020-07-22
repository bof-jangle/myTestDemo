package xyz.jangle.thread.test.n2_5.rwlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 
 * 读写锁，实体类
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年7月22日 下午9:26:57
 * 
 */
public class RWInfo {

	private double price1, price2;
	private ReadWriteLock RWLock;

	public RWInfo() {
		super();
		this.price1 = 1.0;
		this.price2 = 2.0;
		RWLock = new ReentrantReadWriteLock();
	}

	public double getPrice1() {
		RWLock.readLock().lock();
		double v = price1;				// 赋值给中间变量，然后释放锁。
		System.out.println("读价格1信息："+v);
		RWLock.readLock().unlock();
		return v;
	}
	
	// 读锁之间互不干扰
	public double getPrice2() {
		RWLock.readLock().lock();
		double v = price2;
		System.out.println("读价格2信息："+v);
		RWLock.readLock().unlock();
		return v;
	}

	/**
	 * 	写锁可以锁定住读锁和其他的写操作。 
	 * 
	 * @author jangle
	 * @time 2020年7月22日 下午9:35:11
	 * @param price1
	 * @param price2
	 */
	public void setPrice1(double price1, double price2) {
		RWLock.writeLock().lock();
		System.out.println("开始修改价格");
		this.price1 = price1;
		this.price2 = price2;
		try {
			// 睡眠3秒，让读锁锁定其他的读写操作
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		RWLock.writeLock().unlock();
	}


}
