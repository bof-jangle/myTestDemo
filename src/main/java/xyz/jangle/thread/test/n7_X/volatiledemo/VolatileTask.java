package xyz.jangle.thread.test.n7_X.volatiledemo;

/**
 * 使用线程安全状态标志的任务类
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月18日 下午4:47:58
 * 
 */
public class VolatileTask implements Runnable {

	private VolatileFlag flag;

	public VolatileTask(VolatileFlag flag) {
		super();
		this.flag = flag;
	}

	@Override
	public void run() {

		int i = 0;
		while (flag.flag) {
			i++;
		}
		System.out.println("VolatileTask: 停止， i = " + i);
	}

}
