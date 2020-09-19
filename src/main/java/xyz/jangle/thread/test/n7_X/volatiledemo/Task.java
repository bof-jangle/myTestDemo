package xyz.jangle.thread.test.n7_X.volatiledemo;

/**
 * 	使用非线程安全状态的任务
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月18日 下午4:45:01
 * 
 */
public class Task implements Runnable {

	private Flag flag;

	public Task(Flag flag) {
		super();
		this.flag = flag;
	}

	@Override
	public void run() {
		int i=0;
		while(flag.flag) {
			i++;
		}
		System.out.println("Task: 停止执行 i= "+i);
	}

}
