package xyz.jangle.thread.test.n3_5.phaser;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * 	这个线程实现了几个阶段性的工作，并在每个阶段进行等待。
 * 	1、遍历目录搜索所有符合扩展名的文件，并检查是否有记录。
 *  2、对结果进行过滤，获取进近小时修改过的文件，并检查是否有记录。
 *  3、输出这些记录。
 *  4、完毕注销。
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月11日 下午5:02:51
 * 
 */
public class FileSearch implements Runnable {

	// 需要搜索的文件夹路径
	private final String initPath;
	// 要搜索的文件扩展名
	private final String fileExtension;
	// 存储满足条件的文件全路径
	private List<String> results;
	// 对任务的不同阶段进行同步控制。 important
	private Phaser phaser;

	public FileSearch(String initPath, String fileExtension, Phaser phaser) {
		super();
		this.initPath = initPath;
		this.fileExtension = fileExtension;
		this.phaser = phaser;
		this.results = new ArrayList<String>();
	}

	@Override
	public void run() {
		File file = new File(initPath);
		if (file.isDirectory()) {
			// 递归调用，遍历所有目录与其子目录
			directoryProcess(file);
		}
		if (!checkResults()) {
			// 虽然checkResults中从phaser中注销了，但程序还会继续执行，所以这里需要手动return，结束程序。
			return;
		}
		// 筛选近24小时修改过的文件
		filterResults();
		if (!checkResults()) {
			return;
		}
		// 用于最终输出所有结果到控制台
		showInfo();
		// 注销当前任务
		phaser.arriveAndDeregister();
		System.out.println(Thread.currentThread().getName()+"程序执行完毕。");
	}

	/**
	 * 递归调用，遍历所有目录与其子目录
	 * 
	 * @param file
	 */
	private void directoryProcess(File file) {
		File[] files = file.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					directoryProcess(files[i]);
				} else {
					fileProcess(files[i]);
				}
			}
		}
	}

	/**
	 * 记录符合扩展名的文件路径
	 * 
	 * @param file
	 */
	private void fileProcess(File file) {
		if (file.getName().endsWith(fileExtension)) {
			results.add(file.getAbsolutePath());
		}
	}

	/**
	 * 筛选近24小时修改过的文件
	 * 
	 * @author jangle
	 * @time 2020年8月11日 下午5:24:05
	 */
	private void filterResults() {
		ArrayList<String> newResult = new ArrayList<>();
		long currentDate = new Date().getTime();
		for (int i = 0; i < results.size(); i++) {
			File file = new File(results.get(i));
			long fileDate = file.lastModified();
			if (currentDate - fileDate < TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)) {
				newResult.add(results.get(i));
			}
		}
		results = newResult;
	}

	/**
	 * 用于检查每个阶段结束之后，结果是否为空。
	 * 
	 * @author jangle
	 * @time 2020年8月11日 下午5:25:11
	 */
	private boolean checkResults() {
		if (results.isEmpty()) {
			System.out.println(Thread.currentThread().getName() + "结果为空，,阶段phaser:" + phaser.getPhase() + "。该阶段结束，当前存在参与线程数"+phaser.getRegisteredParties());
			// 无结果，则完成当前阶段工作，并且不参与后续阶段的工作。
			phaser.arriveAndDeregister();
			return false;
		} else {
			System.out.println(
					Thread.currentThread().getName() + "存在結果" + results.size() + "，阶段phaser:" + phaser.getPhase()+"，当前存在参与线程数"+phaser.getRegisteredParties());
			// 有结果，则完成当前阶段工作，并且等待其他线程完成工作，而后进入后续阶段的工作。
			phaser.arriveAndAwaitAdvance();
			return true;
		}
	}

	/**
	 * 用于最终输出所有结果到控制台
	 * 
	 * @author jangle
	 * @time 2020年8月11日 下午5:41:02
	 */
	private void showInfo() {
		for (int i = 0; i < results.size(); i++) {
			File file = new File(results.get(i));
			System.out.println(Thread.currentThread().getName() + ":" + file.getAbsolutePath());
		}
		// 等待其他线程完成输出，再进入后续工作。
		phaser.arriveAndAwaitAdvance();
	}

}
