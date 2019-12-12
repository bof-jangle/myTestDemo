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
public class MouseTest {

	public static void main(String[] args) throws AWTException, InterruptedException {
		
		Robot r = new Robot();
		int y = 640;
		int x = 1000;
		

		for(;;) {
			
			// 移动鼠标到指定位置
			r.mouseMove(x,y);
			
//			// 获取鼠标位置
//			Point p  = MouseInfo.getPointerInfo().getLocation();
//			System.out.println(p.getX() + "---" +p.getY());
//			
			// 鼠标左键点击
			r.mousePress(InputEvent.BUTTON1_MASK);
			r.mouseRelease(InputEvent.BUTTON1_MASK);
			Thread.sleep(1000L);
			r.mousePress(InputEvent.BUTTON1_MASK);
			r.mouseRelease(InputEvent.BUTTON1_MASK);
			
			Thread.sleep(8000L);
		}
		
	}

}
