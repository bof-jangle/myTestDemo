package xyz.jangle.thread.test.n3_4.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * 3.4、CyclicBarrier DEMO 在指定状态点同步任务
 * 
 * 本DEMO实现了，由5个线程去搜索10000*1000的矩阵中，值等于6的个数，5个线程分别把结果存入result中。
 * 最后由grouper线程进行汇总。
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月10日 下午12:27:16
 * 
 */
public class M {

	public static void main(String[] args) {
		final int rows = 10000;
		final int length = 1000;
		// 待查询的号码
		final int searchNumber = 6;
		// 线程数量
		final int participants = 5;
		final int linesParticipant = 2000;

		// 矩阵
		MatrixMock mock = new MatrixMock(rows, length, searchNumber);
		// 结果
		Results results = new Results(rows);
		// 总结线程，在所有线程执行完毕后进行启动
		Grouper grouper = new Grouper(results);
		CyclicBarrier barrier = new CyclicBarrier(participants, grouper);
		Searcher[] searcher = new Searcher[participants];
		// 创建N个搜索线程，分别进行搜索
		for (int i = 0; i < searcher.length; i++) {
			searcher[i]= new Searcher(i*linesParticipant, (i+1)*linesParticipant, mock, results, searchNumber, barrier);
			Thread thread = new Thread(searcher[i]);
			thread.start();
		}
		System.out.println("主程序执行完毕");

	}

}
