package xyz.jangle.string.test;

public class ByteTest {

	public static void main(String[] args) {
		
		String str = "123321";
		byte[] bytes = str.getBytes();

		for(byte b:bytes) {
			System.out.println(b);
		}
		
		for(int i=0;i<255;i++) {
			bytes[1] += 1;
			System.out.println(bytes[1]);
		}
		

	}

}
