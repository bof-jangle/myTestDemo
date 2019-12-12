package xyz.jangle.string.test;
/** 
* @author jangle E-mail: jangle@jangle.xyz
* @version 2019年10月7日 下午4:17:58 
* 类说明 
*/
public class SplitTest {

	public static void main(String[] args) {
		String s = "123   456789";
		String[] split = s.split(" ");
		for(String sp:split) {
			System.out.println(sp);
		}

	}

}
