package xyz.jangle.thread.test.n4_8.cancel;

import java.util.concurrent.Callable;

/**
 * 普通的无线循环任务
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月23日 下午5:25:34
 * 
 */
public class Task implements Callable<String> {

	@Override
	public String call() throws Exception {
		while(true) {
			System.out.println("执行中...");
			// 用于取消时，可被中断。(sleep遇到中断标志会抛出InterruptedException异常。从而结束方法）
			Thread.sleep(100);
		}
		/*
		//第二种写法
		while (!Thread.currentThread().isInterrupted()) {
			System.out.println("执行中...");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
				//InterruptedException会清除中断状态，在此处进行再次中断处理即可。
				Thread.currentThread().interrupt();
			}
		}
		System.out.println(Thread.currentThread().getName()+":线程被中断啦");
		return null;
		*/
	}

}
