package xyz.jangle.thread.test.n4_3.callable0future;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 	计算number的阶乘
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月18日 下午2:51:06
 * 
 */
public class FactorialCalculator implements Callable<Integer> {

	private final Integer number;

	public FactorialCalculator(Integer number) {
		super();
		this.number = number;
	}

	@Override
	public Integer call() throws Exception {
		int result = 1;
		if (number == 0 || number == 1) {
			result = 1;
		} else {
			for (int i = 2; i <= number; i++) {
				result *= i;
				TimeUnit.MILLISECONDS.sleep(20);
			}
		}
		System.out.println(Thread.currentThread().getName() +"数字"+ number+"的阶乘是:" + result);
		return result;
	}

}
