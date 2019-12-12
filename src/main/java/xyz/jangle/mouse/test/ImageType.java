package xyz.jangle.mouse.test;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

/**
 * @author jangle E-mail: jangle@jangle.xyz
 * @version 2019年10月23日 下午2:20:43 类说明
 */
public class ImageType {
	public Robot rb;
	public Toolkit tk;
	public Dimension di; // 屏幕尺寸规格
	public Rectangle rec;
	public BufferedImage bi;

	public ImageType() throws AWTException {
		super();
		rb = new Robot();
		tk = Toolkit.getDefaultToolkit();
		di = tk.getScreenSize(); // 屏幕尺寸规格
		rec = new Rectangle(0, 0, di.width, di.height);
		bi = rb.createScreenCapture(rec);
	}

	public static void main(String[] args) throws AWTException, InterruptedException {
		while (true) {
			Thread.sleep(5000L);
			Robot rb = null; // java.awt.image包中的类，可以用来抓取屏幕，即截屏。
			rb = new Robot();
			Toolkit tk = Toolkit.getDefaultToolkit(); // 获取缺省工具包
			Dimension di = tk.getScreenSize(); // 屏幕尺寸规格
//			System.out.println(di.width);  
//			System.out.println(di.height);  
			Rectangle rec = new Rectangle(0, 0, di.width, di.height);
			BufferedImage bi = rb.createScreenCapture(rec);
			Point p = MouseInfo.getPointerInfo().getLocation();
			int pixelColor = bi.getRGB((int) p.getX(), (int) p.getY());
			Color color = new Color(16777216 + pixelColor);
//        return color; // pixelColor的值
			System.out.println("" + p.getX() + "," + p.getY());
			System.out.println(color.getRed() + "," + color.getGreen() + "," + color.getBlue());
		}
	}

	/**
	 * 获取当前色彩类型
	 * 
	 * @param color
	 * @return
	 */
	public static char getType(Color color) {
		int r, g, b;
		r = color.getRed();
		g = color.getGreen();
		b = color.getBlue();
		System.out.println("" + r + "," + g + "," + b);
		if (r > 150 && r < 230 && g > 130 && g < 170 && b < 50) {
			// 黄色确认
			return 'y';
		}
		if (r > 110 && r < 150 && g > 180 && g < 220 && b > 220) {
			// 抽排后的开始,蓝色
			return 'b';
		}
		if (r > 70 && r < 100 && g > 70 && g < 100 && b < 100 && b > 70) {
			// 结束
			return 'e';
		}
		if (r > 30 && r < 70 && g > 130 && g < 170 && b < 40) {
			// 绿色确认
			return 'g';
		}
		if (r > 110 && r < 130 && g > 80 && g < 110 && b > 60 && b < 90) {
			// 绿色确认
			return 'd';
		}
		
		return 'n';
	}

	public static char doWhat() {
		// 判断是否有结束按钮

		return '0';
	}

	/**
	 * 获取当前鼠标位置的色彩
	 * @return
	 */
	public Color getLocationColor() {
		Point p = MouseInfo.getPointerInfo().getLocation();
		int pixelColor = bi.getRGB((int) p.getX(), (int) p.getY());
		Color color = new Color(16777216 + pixelColor);
		return color;
	}
	
	public char getLocationType() {
		Point p = MouseInfo.getPointerInfo().getLocation();
		int pixelColor = bi.getRGB((int) p.getX(), (int) p.getY());
		Color color = new Color(16777216 + pixelColor);
		return getType(color);
		
	}

}
