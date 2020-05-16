package xyz.jangle.exception.test;

public class FinallyTest {

	public static void main(String[] args) {
		
		
		System.out.println(parseInt("12d3"));

	}
	
	@SuppressWarnings("finally")
	public static int parseInt(String str) {
		try {
			return Integer.parseInt(str);
		} finally {
			return 0;
		}
	}

}
