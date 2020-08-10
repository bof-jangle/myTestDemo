package xyz.jangle.thread.test.n3_4.cyclicbarrier;

/**
 * 用于汇总的线程（在N个搜索线程搜索完毕之后执行）
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月10日 下午12:53:57
 * 
 */
public class Grouper implements Runnable {

	private final Results results;

	public Grouper(Results results) {
		super();
		this.results = results;
	}

	@Override
	public void run() {
		int finalResult = 0;
		System.out.println("开始汇总结果");
		int[] data = results.getData();
		for (int i : data) {
			finalResult += i;
		}
		System.out.println("汇总结果，总共搜索出"+finalResult+"个");
	}

}
