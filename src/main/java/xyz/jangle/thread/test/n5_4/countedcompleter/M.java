package xyz.jangle.thread.test.n5_4.countedcompleter;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * 5.4、异步的运行任务CountedCompleter
 * 	分别搜索并统计3个目录下的指定文件的数量
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月28日 下午7:11:53
 * 
 */
public class M {

	public static void main(String[] args) {

		ForkJoinPool pool = new ForkJoinPool();
		FolderProcessor folderProcessor = new FolderProcessor("C:\\workspace", "java");
		FolderProcessor folderProcessor2 = new FolderProcessor("C:\\Windows", "log");
		FolderProcessor folderProcessor3 = new FolderProcessor("C:\\Program Files", "log");
		pool.execute(folderProcessor);
		pool.execute(folderProcessor2);
		pool.execute(folderProcessor3);
		do {
			System.out.println("***********************");
			System.out.println("Main:Active Thread活跃的线程数:" + pool.getActiveThreadCount());
			System.out.println("Main:Task Count任务数:" + pool.getQueuedTaskCount());
			System.out.println("Main:Steal Count线程窃取数量:" + pool.getStealCount());
			System.out.println("***********************");
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (!(folderProcessor.isDone() && folderProcessor2.isDone() && folderProcessor3.isDone()));
		pool.shutdown();
		List<String> results;
		results = folderProcessor.join();
		System.out.println("workspace总共有" + results.size() + "个java文件");
		results = folderProcessor2.join();
		System.out.println("Windows总共有"+results.size()+"个log文件");
		results = folderProcessor3.join();
		System.out.println("Program Files"+results.size()+"个log文件");

	}

}
