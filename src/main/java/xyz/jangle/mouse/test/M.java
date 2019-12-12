package xyz.jangle.mouse.test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

/**
 * @author jangle E-mail: jangle@jangle.xyz
 * @version 2019年10月25日 上午9:23:30 类说明
 */
public class M {

	public Robot r;
	public ImageType it;

	public M() {
		super();
		try {
			r = new Robot();
			it = new ImageType();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws AWTException {

		M m = new M();
		while (true) {

			// 1、开始按钮的判断和点击 如果是开始按钮等待15秒。
			m.doStart();
			// 2、判断抽牌后的蓝色确认按钮。
			m.doConfirm();
			// 3、判断黄色结束按钮
			m.doYEnd();
			// 4、判断结束
			m.doEnd();
		}
	}

	/**
	 * 判断是否是开始并执行相应的操作
	 */
	public void doStart() {
		if (dodo(C.startX, C.startY, 'n') > 60) {
			System.out.println("开始匹配对手");
			r.mousePress(InputEvent.BUTTON1_MASK);
			r.mouseRelease(InputEvent.BUTTON1_MASK);
			sleep(15);
		}
	}
	
	/**
	 * 判断是否是确认按钮，是则点击
	 */
	public void doConfirm() {
		if(dodo(C.confirmX, C.confirmY, 'b') > 60) {
			System.out.println("蓝色的确认开始按钮");
			r.mousePress(InputEvent.BUTTON1_MASK);
			r.mouseRelease(InputEvent.BUTTON1_MASK);
			sleep(5);
		}
	}
	
	/**
	 * 判断是否是结束
	 */
	public void doEnd() {
		if(dodo(C.endX, C.endY, 'e') > 60) {
			System.out.println("对局已经结束");
			r.mousePress(InputEvent.BUTTON1_MASK);
			r.mouseRelease(InputEvent.BUTTON1_MASK);
			sleep();
			r.mousePress(InputEvent.BUTTON1_MASK);
			r.mouseRelease(InputEvent.BUTTON1_MASK);
		}
	}
	
	/**
	 * 黄色结束按钮
	 */
	public void doYEnd() {
		if(dodo(C.endX, C.endY, 'y') > 60) {
			System.out.println("是黄色结束按钮");
			r.mousePress(InputEvent.BUTTON1_MASK);
			r.mouseRelease(InputEvent.BUTTON1_MASK);
			sleep(1);
		}
	}
	
	/**
	 * 停 second 秒
	 * @param second
	 */
	public static void sleep(int second) {
		try {
			Thread.sleep(second * 1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void sleep() {
		try {
			Thread.sleep(1200L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public int dodo(int x,int y, char result) {
		int percent = 0;
		for(int i=0;i<100;i++) {
			r.mouseMove(x, y);
			if(it.getLocationType() == result) {
				percent++;
			}
		}
		return percent;
	}

}
