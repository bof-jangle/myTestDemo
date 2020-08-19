package xyz.jangle.thread.test.n4_4.invokeany;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 	用户校验类
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月19日 下午5:39:08
 * 
 */
public class UserValidator {

	private final String name;

	public UserValidator(String name) {
		super();
		this.name = name;
	}

	public boolean validate(String name, String password) {
		var r = new Random();
		long duration = (long) (Math.random() * 10);
		System.out.println(this.name + "验证使用时间：" + duration);
		try {
			// 模拟验证时长
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
		// 采用随机方式模拟用户验证，即1/2的概率验证通过。
		return r.nextBoolean();
	}

	public String getName() {
		return name;
	}
	

}
