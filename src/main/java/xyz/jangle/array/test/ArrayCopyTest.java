package xyz.jangle.array.test;

import java.util.Arrays;


public class ArrayCopyTest {

	public static void main(String[] args) {

		int a[] = new int[10];
		int b[] = new int[10];
		for(int i = 0; i< 10 ; i ++){
			a[i]=i;
		}
		for(int i = 9; i> 0 ; i --){
			b[9-i]=i;
		}
		System.out.println("a:"+Arrays.toString(a));
		System.out.println("b:"+Arrays.toString(b));
		System.arraycopy(a, 0, b, 0, 10);
		System.out.println("b:"+Arrays.toString(b));
		System.out.println("a:"+Arrays.toString(a));
	}

}
