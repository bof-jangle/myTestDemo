package xyz.jangle.thread.test.n4_4.invokeany;

import java.util.concurrent.Callable;

/**
 * 执行验证的任务线程
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月19日 下午5:44:10
 * 
 */
public class ValidatorTask implements Callable<String> {

	private final UserValidator validator;
	private final String user;
	private final String password;

	public ValidatorTask(UserValidator validator, String user, String password) {
		super();
		this.validator = validator;
		this.user = user;
		this.password = password;
	}

	@Override
	public String call() throws Exception {
		if (!validator.validate(user, password)) {
			System.out.println(validator.getName() + ":用户验证不通过");
			throw new Exception("用户验证失败");
		}
		System.out.println(validator.getName() + ":用户验证通过");
		// 返回用户验证的方式
		return validator.getName();
	}

}
