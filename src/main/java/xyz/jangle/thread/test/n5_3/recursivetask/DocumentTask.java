package xyz.jangle.thread.test.n5_3.recursivetask;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

/**
 * 统计文档中指定单词出现次数的任务类
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月27日 下午6:24:38
 * 
 */
public class DocumentTask extends RecursiveTask<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[][] document;
	private int start, end;
	private String word;

	public DocumentTask(String[][] document, int start, int end, String word) {
		super();
		this.document = document;
		this.start = start;
		this.end = end;
		this.word = word;
	}

	@Override
	protected Integer compute() {
		Integer result = null;
		if (end - start < 10) {
			// 统计并返回单词数
			result = processLines(document, start, end, word);
		} else {
			// 将其拆分为2个子任务
			int mid = (start + end) / 2;
			DocumentTask task = new DocumentTask(document, start, mid, word);
			DocumentTask task2 = new DocumentTask(document, mid, end, word);
			invokeAll(task, task2);
			try {
				// 汇总子任务的结果
				result = task.get() + task2.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 统计当前段落中单词匹配的数量（为段落的每一行创建一个任务，查询单词数量并汇总）
	 * 
	 * @param document2
	 * @param start2
	 * @param end2
	 * @param word2
	 * @return
	 */
	private Integer processLines(String[][] document2, int start2, int end2, String word2) {
		ArrayList<LineTask> tasks = new ArrayList<LineTask>();
		for (int i = start2; i < end2; i++) {
			LineTask task = new LineTask(document2[i], 0, document2[i].length, word2);
			tasks.add(task);
		}
		// 执行所有任务
		invokeAll(tasks);
		// 统计任务结果
		int result = 0;
		for (LineTask lineTask : tasks) {
			try {
				result += lineTask.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
