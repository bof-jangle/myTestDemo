package xyz.jangle.mouse.test;

import java.awt.AWTException;
//import java.awt.MouseInfo;
//import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;

/** 
* @author jangle E-mail: jangle@jangle.xyz
* @version 2019年10月23日 上午11:02:13 
* 类说明 
*/
public class AutoMouse extends Thread {
	
	private volatile char in = 'a';

	@Override
	public void run() {
		
		Robot r = null;
		try {
			r = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		int y = 888;
		int x = 1407;
		

		for(;;) {
//			System.out.println(in);
			if(in == 'z') {
				break;
			}
			// 移动鼠标到指定位置
			r.mouseMove(x,y);
			
			// 获取鼠标位置
//			Point p  = MouseInfo.getPointerInfo().getLocation();
//			System.out.println(p.getX() + "---" +p.getY());
			
			// 鼠标左键点击
			r.mousePress(InputEvent.BUTTON1_MASK);
			r.mouseRelease(InputEvent.BUTTON1_MASK);
			
			try {
				Thread.sleep(5000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public char getIn() {
		return in;
	}

	public void setIn(char i) {
		this.in = i;
	}
	
	
	

}
