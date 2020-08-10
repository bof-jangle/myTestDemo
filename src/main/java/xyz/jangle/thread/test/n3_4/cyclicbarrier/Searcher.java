package xyz.jangle.thread.test.n3_4.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 	搜索线程（用于从mock矩阵中搜索指定的行区间，将匹配的搜索结果写入results中，然后调用barrier的await方法。）
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月10日 下午1:01:41
 * 
 */
public class Searcher implements Runnable {

	private final int firstRow;
	private final int lastRow;
	private final MatrixMock mock;
	private final Results results;
	private final int number;
	private final CyclicBarrier barrier;

	public Searcher(int firstRow, int lastRow, MatrixMock mock, Results results, int number, CyclicBarrier barrier) {
		super();
		this.firstRow = firstRow;
		this.lastRow = lastRow;
		this.mock = mock;
		this.results = results;
		this.number = number;
		this.barrier = barrier;
	}

	@Override
	public void run() {
		int counter;
		System.out.println(Thread.currentThread().getName() + "开始搜索第" + firstRow + "行到第" + lastRow + "行");
		for (int i = firstRow; i < lastRow; i++) {
			int[] row = mock.getRow(i);
			counter = 0;
			for (int j = 0; j < row.length; j++) {
				if (row[j] == number) {
					counter++;
				}
			}
			results.setData(i, counter);
		}
		System.out.println(Thread.currentThread().getName()+"搜索完毕");
		
		try {
			barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}

	}

}
