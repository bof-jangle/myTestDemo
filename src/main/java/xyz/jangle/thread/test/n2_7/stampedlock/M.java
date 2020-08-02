package xyz.jangle.thread.test.n2_7.stampedlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;


/**
 * 
 * StampedLock	DEMO 
 * 	
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年7月26日 下午6:17:24
 * 
 */
public class M {

	public static void main(String[] args) {
		StampedLock lock = new StampedLock();
		Position p = new Position(1, 2);
		cWriter(p, lock).start();
		cReader(p, lock).start();
		cOptimisticReader(p, lock).start();

	}

	/**
	 *  创建写线程，该线程使用了StampedLock的写锁
	 *  使用 writeLock() 方法获取写锁
	 *  使用 unlockWrite(stamp) 方法释放写锁
	 * 
	 * @author jangle
	 * @time 2020年8月2日 下午2:59:41
	 * @param p
	 * @param lock
	 * @return
	 */
	public static Thread cWriter(Position p, StampedLock lock) {
		return new Thread(() -> {
			for (int i = 0; i < 2; i++) {
				System.out.println("开始获取写锁...");
				long stamp = lock.writeLock();
				try {

					System.out.println("开始进行写操作x:" + p.getX() + ",y:" + p.getY());
					p.setX(p.getX() + 1);
					p.setY(p.getY() + 1);
					System.out.println("完成写操作x:" + p.getX() + ",y:" + p.getY() + "开始停顿5秒");
					TimeUnit.SECONDS.sleep(5L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlockWrite(stamp);
					System.out.println("释放写锁w stamp:" + stamp);
				}
				try {
					System.out.println("写线程无锁sleep 5秒");
					TimeUnit.SECONDS.sleep(5L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 	创建读线程，该线程使用了StampedLock读锁
	 * 	使用 readLock() 方法获取读锁
	 * 	使用 unlockRead() 方法释放读锁
	 * @author jangle
	 * @time 2020年8月2日 下午3:00:21
	 * @param p
	 * @param lock
	 * @return
	 */
	public static Thread cReader(Position p, StampedLock lock) {
		return new Thread(() -> {
			for (int i = 0; i < 50; i++) {
				System.out.println("开始获取读锁");
				long stamp = lock.readLock();
				try {
					System.out.println("成功获得读锁：x:" + p.getX() + ",y:" + p.getY());
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlockRead(stamp);
					System.out.println("完成读锁释放r stamp:" + stamp);
				}
			}
		});
	}

	/**
	 * 	创建乐观读线程，该线程使用了StampedLock的乐观读锁
	 * 	主要使用 tryOptimisticRead() 方法获取票据值stamp。
	 * 	通过票据值调用 validate(stamp) 方法求的当前值是否可用（是否被写锁占用）
	 * 
	 * @author jangle
	 * @time 2020年8月2日 下午3:03:30
	 * @param p
	 * @param lock
	 * @return
	 */
	public static Thread cOptimisticReader(Position p, StampedLock lock) {
		return new Thread(() -> {
			long stamp;
			for (int i = 0; i < 100; i++) {
				try {
					//
					stamp = lock.tryOptimisticRead();
					Integer x = p.getX();
					Integer y = p.getY();
					if (lock.validate(stamp)) {
						System.out.println("乐观读：x:" + x + ",y:" + y);
					} else {
						System.out.println("OptimisticReader:" + stamp + " Not Free x:" + x + ",y:" + y);
					}
					TimeUnit.MILLISECONDS.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * 	其他方法说明
	 * 
	 * @author jangle
	 * @time 2020年8月2日 下午3:25:28
	 * @param lock
	 */
	public static void other(StampedLock lock) {
		// 尝试获取读锁，未获取返回0
		long stampR = lock.tryReadLock();
//		lock.tryReadLock(time, unit);
		
		// 尝试获取写锁，未获取返回0
		long stampW = lock.tryWriteLock();
//		lock.tryWriteLock(time, unit);
		
		// 尝试将读锁转换为写锁，失败返回0
		long newStampW = lock.tryConvertToWriteLock(stampR);
		// 尝试将写锁转换为读锁，失败返回0
		long newStampR = lock.tryConvertToReadLock(stampW);
		// 尝试转换为乐观读锁，失败返回0，成功则可使用这个stamp进行验证
		long optStampR = lock.tryConvertToOptimisticRead(stampR);
		// 验证获取乐观读stamp之后，到目前位置，操作的共享数据是否可用。
		boolean validate = lock.validate(optStampR);
		if(validate) {
			// 可用，进行相关操作
		}else {
			// 不可用， 进行其他操作
		}
		
		// 判断当前是读锁还是写锁
		lock.isReadLocked();
		lock.isWriteLocked();
		
		// 释放对应的锁
		lock.unlock(stampR);
		lock.unlock(stampW);
		lock.unlock(newStampR);
		lock.unlock(newStampW);
	}
}
