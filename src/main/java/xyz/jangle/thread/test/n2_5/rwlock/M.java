package xyz.jangle.thread.test.n2_5.rwlock;

import java.util.concurrent.TimeUnit;

/**
 * 
 * 	读写锁。ReentrantReadWriteLock
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年7月22日 下午9:37:18
 * 
 */
public class M {

	public static void main(String[] args) {

		RWInfo rwInfo = new RWInfo();

		// 读取价格1的线程
		new Thread(() -> {
			for (int i = 0; i < 20; i++) {
				rwInfo.getPrice1();
				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		// 读取价格2的线程
		new Thread(() -> {
			for (int i = 0; i < 20; i++) {
				rwInfo.getPrice2();
				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		// 修改价格1和价格2的线程
		new Thread(() -> {
			for (int i = 0; i < 3; i++) {
				rwInfo.setPrice1(rwInfo.getPrice1() + 1, rwInfo.getPrice2()+1);
			}
		}).start();

	}

}
