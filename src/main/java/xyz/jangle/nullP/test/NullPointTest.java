package xyz.jangle.nullP.test;
/** 
* @author jangle E-mail: jangle@jangle.xyz
* @version 2019年8月1日 下午5:08:09 
* 类说明 
*/
public class NullPointTest {

	public static void main(String[] args) {
		try {
			String s = null;
			s.indexOf(1);
		} catch (Exception e) {
			System.out.println("异常情况");
		}
		

	}

}
