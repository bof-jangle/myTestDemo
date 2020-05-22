package xyz.jangle.string.test;
/** 
 * 
 * 
 * 测试 字符串 和 字符 关系。
 * 
 * char 占2字节。
 * 
 * 
* @author jangle E-mail: jangle@jangle.xyz
* @version 2020年5月22日 下午4:36:21 
* 类说明 
*/
public class StringTest2 {

	public static void main(String[] args) {
		
		String s = "中国123";
		for(int i=0;i<s.length();i++) {
			System.out.println(s.charAt(i));
		}
		
	}

}
