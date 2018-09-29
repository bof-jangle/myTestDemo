package xyz.jangle.overwrite.test;

import java.util.ArrayList;
import java.util.List;

import xyz.jange.object.test.A;

/**
 * 
 * 此处虽然要报错提示，但可以运行。 会执行先定义的。 即，后定义的无法覆盖先定义的方法， 
 * @author huhj
 * @email  jangle@jangle.xyz
 * @time   2018年9月29日 上午9:37:48
 */
public class M {
/*
	public static void main(String[] args) {
		
		try {
			System.out.println((new M()).test1(new ArrayList<>()));
		} catch (Exception e) {
			System.out.println("return int error");
		}
		System.out.println((new M()).test2());	//相同方法签名，使用先定义的那个。
		
		test3(new ArrayList<String>());
//		test3(new ArrayList<Integer>());		//无法编译通过  sun jdk 1.6 的javac 可以。
		
	}
	public String test1(List<String> l){
		return "3";
	}
	
	public int test1(List<Integer> l){
		return 2;
	}
	
	public String test2(){
		return "String 1";
	}
	
	public int test2(){
		return 1;
	}
	
	
	public static String test3(List<String> l){
		System.out.println(" return String test3");
		return "";
	}
	
	public static int test3(List<Integer> l){
		System.out.println(" return Integer test3");
		return 0;
	}
	
*/
}
