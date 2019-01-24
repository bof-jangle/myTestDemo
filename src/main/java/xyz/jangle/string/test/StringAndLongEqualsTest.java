package xyz.jangle.string.test;

public class StringAndLongEqualsTest {

	public static void main(String[] args) {
		long lon = 123;
		String str = "123";
//		System.out.println(str.equals(lon));  //false
		System.out.println(str.equals(""+lon));  //true
	}

}
