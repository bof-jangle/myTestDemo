package xyz.jangle.thread.test.n3_6.phaser;

import java.util.concurrent.Phaser;

/**
 * 	Phaser DEMO，本案例模拟一场考试。
 * 	它总共进行3轮答题，每一轮都要等所有学生答题完成后，再进入下一轮答题。
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月12日 下午4:06:42
 * 
 */
public class M {

	public static void main(String[] args) {
		Phaser phaser = new MyPhaser();
		Student[] students = new Student[5];
		for (int i = 0; i < students.length; i++) {
			students[i] = new Student(phaser);
			phaser.register();
		}
		Thread[] threads = new Thread[students.length];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(students[i]);
			threads[i].start();
		}
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Main: 考试结束 phaser终止："+phaser.isTerminated());

	}

}
