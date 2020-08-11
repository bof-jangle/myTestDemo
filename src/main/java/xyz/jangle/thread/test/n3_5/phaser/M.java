package xyz.jangle.thread.test.n3_5.phaser;

import java.util.concurrent.Phaser;

/**
 * 3.5 Phaser 运行阶段性并发任务
 *   当一些并发任务需要分步骤执行时，可以使用该机制。（Phaser类提供了在每一步结束时同步线程的机制，
 * 这使得只有当所有线程都完成第一步后，才会有线程开始执行第二步。）
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月11日 下午4:58:36
 * 
 */
public class M {

	public static void main(String[] args) {
		Phaser phaser = new Phaser(3);
		FileSearch program86 = new FileSearch("C:\\Program Files (x86)", "log", phaser);
		FileSearch program = new FileSearch("C:\\Program Files", "log", phaser);
		FileSearch workspace = new FileSearch("C:\\workspace", "log", phaser);
		Thread thread1 = new Thread(program86, "program86");
		Thread thread2 = new Thread(program, "program");
		Thread thread3 = new Thread(workspace, "workspace");
		thread1.start();
		thread2.start();
		thread3.start();
		try {
			thread1.join();
			thread2.join();
			thread3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("程序结束，phaser is terminated:" + phaser.isTerminated());

	}

}
