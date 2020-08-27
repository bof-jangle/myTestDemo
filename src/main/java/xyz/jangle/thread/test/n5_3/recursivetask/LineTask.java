package xyz.jangle.thread.test.n5_3.recursivetask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * 统计每一行匹配单词数的任务类
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月27日 下午6:32:42
 * 
 */
public class LineTask extends RecursiveTask<Integer> {

	private static final long serialVersionUID = 1L;

	private String[] line;
	private int start, end;
	private String word;

	public LineTask(String[] line, int start, int end, String word) {
		this.line = line;
		this.start = start;
		this.end = end;
		this.word = word;
	}

	@Override
	protected Integer compute() {
		Integer result = null;
		if (end - start < 100) {
			// 统计该行匹配单词的数量
			result = count(line, start, end, word);
		} else {
			// 拆分为2个子任务
			int middle = (start + end) / 2;
			LineTask task1 = new LineTask(line, start, middle, word);
			LineTask task2 = new LineTask(line, middle, end, word);
			invokeAll(task1, task2);
			try {
				// 将子任务的结果汇总
				result = task1.get() + task2.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 统计指定部分单词匹配的数量
	 * 
	 * @param line2
	 * @param start2
	 * @param end2
	 * @param word2
	 * @return
	 */
	private Integer count(String[] line2, int start2, int end2, String word2) {
		Integer count = 0;
		for (int i = start2; i < end2; i++) {
			if (line2[i].equals(word2)) {
				count++;
			}
		}
		try {
			TimeUnit.MILLISECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return count;
	}

}
