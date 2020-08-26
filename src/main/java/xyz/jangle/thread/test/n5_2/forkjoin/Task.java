package xyz.jangle.thread.test.n5_2.forkjoin;

import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 *  核心任务类
 *  compute方法是核心执行逻辑（也是分治逻辑编写的地方）
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月26日 下午5:08:15
 * 
 */
public class Task extends RecursiveAction {

	private static final long serialVersionUID = 1L;

	private List<Product> products;

	private int first, last;
	// 涨幅
	private double increment;

	public Task(List<Product> products, int first, int last, double increment) {
		super();
		this.products = products;
		this.first = first;
		this.last = last;
		this.increment = increment;
	}

	@Override
	protected void compute() {
		if (last - first < 10) {
			updatePrices();
		} else {
			int middle = (last + first) / 2;
			System.out.println("Task:待执行的线程数量：" + getQueuedTaskCount());
			Task task1 = new Task(products, first, middle + 1, increment);
			Task task2 = new Task(products, middle + 1, last, increment);
			// 该方法创建子任务，并进行同步等待（在等待期间，该工作线程会选取待执行的任务进行执行
			// 该方法是ForkJoinTask的invokeAll，与ExecutorService的invokeAll稍有区别
			invokeAll(task1, task2);
		}
	}

	/**
	 * 更新产品价格（first - last之间的）
	 * 
	 * @author jangle
	 * @time 2020年8月26日 下午5:10:14
	 */
	private void updatePrices() {
		for (int i = first; i < last; i++) {
			Product product = products.get(i);
			product.setPrice(product.getPrice() * (1 + increment));
		}
	}

}
