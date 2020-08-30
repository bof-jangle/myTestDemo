package xyz.jangle.thread.test.n5_6.cancel;

/**
 *   取消任务的管理类
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月30日 下午5:59:04
 * 
 */

import java.util.concurrent.ConcurrentLinkedDeque;

public class TaskManager {

	private final ConcurrentLinkedDeque<SearchNumberTask> tasks;

	public TaskManager() {
		tasks = new ConcurrentLinkedDeque<SearchNumberTask>();
	}

	public void addTask(SearchNumberTask task) {
		tasks.add(task);
	}

	public void cancelTasks(SearchNumberTask searchNumberTask) {
		for (SearchNumberTask forkJoinTask : tasks) {
			if (forkJoinTask != searchNumberTask) {
				forkJoinTask.cancel(true);
				forkJoinTask.logCancelMessage();
			}
		}

	}

}
