package xyz.jangle.thread.test.n9_3.testphaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * 
 *  9.3、监测Phaser类
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年10月23日 下午5:58:27
 * 
 */
public class M {

	public static void main(String[] args) throws Exception {
		
		var phaser = new Phaser(3);
		for (int i = 0; i < 3; i++) {
			var task = new Task(i+1 , phaser);
			var thread = new Thread(task);
			thread.start();
		}
		for (int i = 0; i < 10; i++) {
			System.out.println("*********************");
			System.out.println("M:Phaser Log");
			System.out.println("M:getPhaser():"+phaser.getPhase());
			System.out.println("M:phaser.getRegisteredParties():"+phaser.getRegisteredParties());
			System.out.println("M:phaser.getArrivedParties()："+phaser.getArrivedParties());
			System.out.println("M:phaser.getUnarrivedParties()："+phaser.getUnarrivedParties());
			System.out.println("*********************");
			TimeUnit.SECONDS.sleep(1);
		}
		

	}

}
