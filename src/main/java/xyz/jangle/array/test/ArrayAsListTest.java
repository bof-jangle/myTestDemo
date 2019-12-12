package xyz.jangle.array.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author huhj
 * @email  jangle@jangle.xyz
 * @time   2018年9月29日 上午10:05:38
 */
public class ArrayAsListTest {

	public static void main(String[] args) {
		int k = 0;
		List<String> list = Arrays.asList("1","2","3");
		for(String s : list){
			System.out.println(s);
		}
		System.out.println("**********");
//		List<String> list2 = ["1","2","3","4"];
//		List<Integer> list3 = [1,2,3,4];		//没有这种语法糖
		
		List<String> list2 = new ArrayList<String>();
		list2.add("1");
		list2.add("2");
		list2.add("3");
		list2.remove(0-k);
		k++;
		for(String s : list2){
			System.out.println(s);
		}
		System.out.println("**********");
		list2.remove(2-k);
		for(String s : list2){
			System.out.println(s);
		}

	}

}
