package xyz.jangle.mouse.test;

import java.io.IOException;

/** 
* @author jangle E-mail: jangle@jangle.xyz
* @version 2019年10月23日 上午10:52:08 
* 类说明 
*/
public class KeyReceiveTest {

	public static void main(String[] args) throws IOException {
		 System.out.print("Enter a Char:"); 
		 AutoMouse m = new AutoMouse();
		 m.start();
		 for(;;) {
			 
			 char i = (char) System.in.read(); 
//			 System.out.println("your char is :"); 
//			 System.out.println(""+i); 
			 m.setIn(i);
		 }

	}

}
