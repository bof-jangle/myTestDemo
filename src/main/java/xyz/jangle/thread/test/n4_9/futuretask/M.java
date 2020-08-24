package xyz.jangle.thread.test.n4_9.futuretask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 4.9、FutureTask 在执行器内控制任务的完成
 * 	参数既作为执行任务，也作为返回的Future对象进行控制。
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月24日 下午7:26:08
 * 
 */
public class M {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		// 创建包装的任务数组（该包装类既可以当作任务，也可以作为Future对任务进行干预控制。
		ResultTask[] resultTaskArr = new ResultTask[5];
		for (int i = 0; i < resultTaskArr.length; i++) {
			ExecutableTask executableTask = new ExecutableTask("Task" + i);
			resultTaskArr[i] = new ResultTask(executableTask);
			executorService.submit(resultTaskArr[i]);
		}
		// 休息5秒
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 取消所有任务
		for (int i = 0; i < resultTaskArr.length; i++) {
			resultTaskArr[i].cancel(true);
		}
		// 打印未被取消的任务的执行结果（在取消之前已经完成了）
		for (int i = 0; i < resultTaskArr.length; i++) {
			try {
				if (!resultTaskArr[i].isCancelled()) {
					System.out.println(resultTaskArr[i].get());
				}
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		executorService.shutdown();
	}

}
