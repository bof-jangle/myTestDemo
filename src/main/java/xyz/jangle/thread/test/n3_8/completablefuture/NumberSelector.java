package xyz.jangle.thread.test.n3_8.completablefuture;

import java.util.List;
import java.util.function.Function;

/**
 * 根据List，计算出最大值和最小值的平均值
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月14日 下午8:21:12
 * 
 */
public class NumberSelector implements Function<List<Long>, Long> {

	@Override
	public Long apply(List<Long> t) {
		System.out.println(Thread.currentThread().getName() + "任务3、开始计算最大值和最小值的平均值");
		Long max = t.stream().max(Long::compare).get();
		Long min = t.stream().min(Long::compare).get();
		Long avg = (max + min) / 2;
		System.out.println(Thread.currentThread().getName() + "任务3、完成计算最大值和最小值的平均值");
		return avg;
	}

}
