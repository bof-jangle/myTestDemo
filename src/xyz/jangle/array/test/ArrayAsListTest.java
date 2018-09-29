package xyz.jangle.array.test;

import java.util.Arrays;
import java.util.List;

/**
 * @author huhj
 * @email  jangle@jangle.xyz
 * @time   2018年9月29日 上午10:05:38
 */
public class ArrayAsListTest {

	public static void main(String[] args) {
		List<String> list = Arrays.asList("1","2","3");
		for(String s : list){
			System.out.println(s);
		}
//		List<String> list2 = ["1","2","3","4"];
//		List<Integer> list3 = [1,2,3,4];		//没有这种语法糖

	}

}
