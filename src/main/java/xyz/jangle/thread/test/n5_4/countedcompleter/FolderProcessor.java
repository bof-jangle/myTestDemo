package xyz.jangle.thread.test.n5_4.countedcompleter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountedCompleter;

/**
 * 统计目录下指定文件类型的数目 compute()执行计算过程 getRawResult()返回计算结果
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月28日 下午7:20:10
 * 
 */
public class FolderProcessor extends CountedCompleter<List<String>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 路径
	private String path;
	// 文件类型
	private String extension;
	// 子任务列表
	private List<FolderProcessor> tasks;
	// 结果列表
	private List<String> resultList;

	/**
	 * 	 用于在生成子任务时进行构建
	 * @param completer
	 * @param path
	 * @param extension
	 */
	protected FolderProcessor(CountedCompleter<?> completer, String path, String extension) {
		super(completer);
		this.path = path;
		this.extension = extension;
	}

	/**
	 *  初始任务的构建
	 * @param path
	 * @param extension
	 */
	public FolderProcessor(String path, String extension) {
		this.path = path;
		this.extension = extension;
	}

	@Override
	public void compute() {
		resultList = new ArrayList<>();
		tasks = new ArrayList<>();
		File file = new File(path);
		File[] content = file.listFiles();
		if (content != null) {
			for (int i = 0; i < content.length; i++) {
				if (content[i].isDirectory()) {
					// 如果是目录，则创建子任务进行处理（子任务需将当前对象进行传入）
					FolderProcessor task = new FolderProcessor(this, content[i].getAbsolutePath(), extension);
					// 异步执行
					task.fork();
					// 111
					addToPendingCount(1);
					// 将任务添加到子任务列表中
					tasks.add(task);
				} else {
					// 否则是文件，则验证是否为目标扩展名
					if (checkFile(content[i].getName())) {
						resultList.add(content[i].getAbsolutePath());
					}
				}
			}
			if (tasks.size() > 50) {
				System.out.println(file.getAbsolutePath() + "子任务大于50：" + tasks.size());
			}
		}
		// 对任务进行完成操作，如果不执行，会导致任务的isDone()始终为false。
		tryComplete();
	}

	@Override
	public void onCompletion(CountedCompleter<?> caller) {
		for (FolderProcessor childTask : tasks) {
			// 把子任务的结果全部添加到其父级任务中。
			resultList.addAll(childTask.getResultList());
		}
	}

	/**
	 * 该方法用于返回计算结果，不重写的话，程序调用task.join()方法会得到null
	 */
	@Override
	public List<String> getRawResult() {
		return resultList;
	}

	private boolean checkFile(String name) {
		return name.endsWith(extension);
	}

	public List<String> getResultList() {
		return resultList;
	}

}
