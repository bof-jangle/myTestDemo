package xyz.jangle.thread.test.n3_2.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 	模拟3台打印机进行对外服务
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月8日 下午4:52:28
 * 
 */
public class PrintQueue {

	// 信号量控制
	private final Semaphore semaphore;
	// 打印机组
	private final boolean freePrintes[];
	// 对获取打印机时，进行同步控制。
	private final Lock lockPrinters;

	public PrintQueue() {
		super();
		this.semaphore = new Semaphore(3);
		this.freePrintes = new boolean[3];
		for (int i = 0; i < freePrintes.length; i++) {
			freePrintes[i] = true;
		}
		this.lockPrinters = new ReentrantLock();
	}

	/**
	 * 主要功能，使用信号量对现有打印机资源进行控制
	 * 
	 * @author jangle
	 * @time 2020年8月8日 下午4:57:30
	 * @param document
	 */
	public void printJob(Object document) {
		try {
			// 获取访问许可（信号量）
			semaphore.acquire();
			int assignedPrinter = getPrinter();
			System.out.println("模拟打印... 打印机编号" + assignedPrinter + "。当前线程" + Thread.currentThread().getName());
			TimeUnit.SECONDS.sleep((long) (Math.random() * 10));
			freePrintes[assignedPrinter] = true;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// 释放许可（信号量）
			semaphore.release();
		}
	}

	/**
	 * 	获取打印机
	 * 
	 * @author jangle
	 * @time 2020年8月8日 下午5:18:20
	 * @return
	 */
	private int getPrinter() {
		// 用于保存打印机编号
		int ret = -1;
		try {
			lockPrinters.lock();
			for (int i = 0; i < freePrintes.length; i++) {
				if (freePrintes[i]) {
					ret = i;
					freePrintes[i] = false;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lockPrinters.unlock();
		}
		return ret;
	}
}
