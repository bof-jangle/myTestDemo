package xyz.jangle.mouse.test;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;

/** 
 * 	读取配置文件方式的主程序
* @author jangle E-mail: jangle@jangle.xyz
* @version 2019年10月25日 下午2:52:09 
* 类说明 
*/
public class PM {
	public Robot r;
	public ImageType it;
	
	public PM() {
		super();
		try {
			r = new Robot();
			it = new ImageType();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		PM pm = new PM();
		
		

	}
	
	
	public void doStart() {
		r.mouseMove((int)P.propertyMap.get(PC.startX), (int)P.propertyMap.get(PC.startY));
		Color color = it.getLocationColor();
		
	}

}
