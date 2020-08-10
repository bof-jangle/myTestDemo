package xyz.jangle.thread.test.n3_4.cyclicbarrier;

import java.util.Random;

/**
 * 矩阵类，它将生成0~9的随机数字矩阵，用于被搜索线程检索
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月10日 下午12:29:48
 * 
 */
public class MatrixMock {

	private final int data[][];

	public MatrixMock(int size, int length, int number) {
		int counter = 0;
		data = new int[size][length];
		Random random = new Random();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < length; j++) {
				data[i][j] = random.nextInt(10);
				if (data[i][j] == number) {
					counter++;
				}
			}
		}
		System.out.println("数字" + number + " ，共计" + counter + "个");

	}

	/**
	 * 获取指定行
	 * 
	 * @param row
	 * @return
	 */
	public int[] getRow(int row) {
		if (row < 0 || row > data.length) {
			return null;
		}
		return data[row];
	}

}
