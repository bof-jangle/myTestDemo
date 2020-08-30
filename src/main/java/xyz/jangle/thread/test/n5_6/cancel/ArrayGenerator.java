package xyz.jangle.thread.test.n5_6.cancel;

import java.util.Random;

/**
 * 	生成一个数字数组
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月30日 下午5:55:51
 * 
 */
public class ArrayGenerator {

	public int[] generateArray(int size) {
		int[] array = new int[size];
		Random r = new Random();
		for (int i = 0; i < array.length; i++) {
			array[i] = r.nextInt(10);
		}
		return array;
	}

}
