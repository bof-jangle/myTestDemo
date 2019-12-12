package xyz.jangle.mouse.test;

import java.awt.AWTException;
//import java.awt.MouseInfo;
//import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;

/** 
* @author jangle E-mail: jangle@jangle.xyz
* @version 2019年10月23日 上午10:09:14 
* 类说明 
*/
public class MouseLuanDou {

	public static void main(String[] args) throws AWTException, InterruptedException {
		
		while(true) {
			mouseLuanDou();
		}
		
	}
	
	public static void mouseLuanDou() throws AWTException, InterruptedException {
		Robot r = new Robot();
		int[] mouseMove = new int[] {C.ldx,C.ldy,C.yxX,C.yxY,C.startX,C.startY};
		for(int i=0;i<mouseMove.length;) {
			
			r.mouseMove(mouseMove[i++],mouseMove[i++]);
			// 鼠标左键点击
			r.mousePress(InputEvent.BUTTON1_MASK);
			r.mouseRelease(InputEvent.BUTTON1_MASK);
			Thread.sleep(1000L);
			r.mousePress(InputEvent.BUTTON1_MASK);
			r.mouseRelease(InputEvent.BUTTON1_MASK);
			
		}
		// 移动鼠标到指定位置
		Thread.sleep(15000L);
	}

}
