package xyz.jangle.thread.test.n4_4.invokeany;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 	4.4、运行N个任务并处理第一个（最先的且正常的）返回结果
 * 	案例：模拟用户验证，一个通过接口方式验证，另一个使用数据库方式验证。
 * 	只要其中一种方式验证通过即返回结果。
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月19日 下午5:38:03
 * 
 */
public class M {

	public static void main(String[] args) {
		var user = "user";
		var password = "password";
		var iValidator = new UserValidator("接口验证");
		var dbValidator = new UserValidator("数据库验证");
		var iTask = new ValidatorTask(iValidator, user, password);
		var dbTask = new ValidatorTask(dbValidator, user, password);
		ArrayList<ValidatorTask> taskList = new ArrayList<ValidatorTask>();
		taskList.add(iTask);
		taskList.add(dbTask);
		ExecutorService threadPool = Executors.newCachedThreadPool();
		String result;
		try {
			result = threadPool.invokeAny(taskList);
			System.out.println("Main:result:使用了"+result);
		} catch (InterruptedException e) {
			System.out.println("Main:中断异常");
			e.printStackTrace();
		}catch (ExecutionException e) {
			// 用户验证失败，两种方式都未正确返回。
			System.out.println("Main:用户验证失败");
			e.printStackTrace();
		}
		threadPool.shutdown();
		System.out.println("Main:执行结束");
	}

}
