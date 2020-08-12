package xyz.jangle.thread.test.n3_6.phaser;

import java.util.concurrent.Phaser;

/**
 * 	继承Phaser，重写onAdvance方法，进行自定义操作。
 * 	（自定义每个阶段转变时所要进行的干预动作。例：阶段1完成后 -> 阶段2开始前）
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月12日 下午3:27:58
 * 
 */
public class MyPhaser extends Phaser {

	@Override
	protected boolean onAdvance(int phase, int registeredParties) {
		// 此处如果返回true会让phaser进入终止状态。
		switch (phase) {
		case 0:
			return studentsArrived();
		case 1:
			return finishFirstExercise();
		case 2:
			return finishSecondExercise();
		case 3:
			return finishExam();
		default:
			return true;
		}
	}

	private boolean finishExam() {
		System.out.println("第三轮答题结束，考试结束。");
		return true;
	}

	private boolean finishSecondExercise() {
		System.out.println("所有学生完成第二轮答题，准备进入第三轮答题。");
		return false;
	}

	private boolean finishFirstExercise() {
		System.out.println("所有学生完成第一轮答题。准备进入第二轮答题。");
		return false;
	}

	private boolean studentsArrived() {
		System.out.println("学生全部抵达考场，学生人数"+getRegisteredParties()+"人");
		return false;
	}
	
	

}
