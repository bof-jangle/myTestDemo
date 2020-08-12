package xyz.jangle.thread.test.n3_6.phaser;

import java.util.Date;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * 	创建学生类，模拟考试答题，通过phaser进行每一轮的同步。
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月12日 下午3:53:17
 * 
 */
public class Student implements Runnable {

	private Phaser phaser;

	public Student(Phaser phaser) {
		super();
		this.phaser = phaser;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + "，抵达考场，" + new Date());
		phaser.arriveAndAwaitAdvance();
		System.out.println(Thread.currentThread().getName() + "，进行第一轮答题" + new Date());
		doExercise1();
		System.out.println(Thread.currentThread().getName() + "，完成第一轮答题，等待大家答完" + new Date());
		phaser.arriveAndAwaitAdvance();
		System.out.println(Thread.currentThread().getName() + "，开始第二轮答题" + new Date());
		doExercise2();
		System.out.println(Thread.currentThread().getName() + "，完成第二轮答题，等待大家答完" + new Date());
		phaser.arriveAndAwaitAdvance();
		System.out.println(Thread.currentThread().getName() + "，开始第三轮答题" + new Date());
		doExericise3();
		System.out.println(Thread.currentThread().getName() + "，完成第三轮答题，等待大家答完" + new Date());
		phaser.arriveAndAwaitAdvance();

	}

	private void doExericise3() {
		doExercise1();
	}

	private void doExercise2() {
		doExercise1();
	}

	private void doExercise1() {
		try {
			TimeUnit.SECONDS.sleep((long) (Math.random() * 10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
