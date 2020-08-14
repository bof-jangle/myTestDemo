package xyz.jangle.thread.test.n3_8.completablefuture;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * 	实现一个供应商类，供应一个几百万条记录的列表
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月14日 下午7:50:30
 * 
 */
public class NumberListGenerator implements Supplier<List<Long>> {

	private final int size;

	public NumberListGenerator(int size) {
		super();
		this.size = size;
	}

	@Override
	public List<Long> get() {
		var ret = new ArrayList<Long>();
		System.out.println(Thread.currentThread().getName() + "NumberList 开始生产...");
		for (int i = 0; i < size * 1000000; i++) {
			long number = Math.round(Math.random() * Long.MAX_VALUE);
			ret.add(number);
		}
		System.out.println(Thread.currentThread().getName() + "NumberList 完成生产...");
		return ret;
	}

}
