package xyz.jangle.thread.test.n8_4.threadfactory;

/**
 * 8.4、实现线程工厂（ThreadFactory）
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月22日 下午5:19:57
 * 
 */
public class M {

	public static void main(String[] args) throws Exception {
		// 1、创建线程工厂
		var factory = new MyThreadFactory("jangle");
		// 2、创建普通任务
		var task = new MyTask();
		// 3、使用工厂创建线程
		Thread thread = factory.newThread(task);
		thread.start();
		thread.join();
		System.out.println("M: 结束");

	}

}
