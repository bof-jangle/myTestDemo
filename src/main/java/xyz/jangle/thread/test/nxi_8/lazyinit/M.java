package xyz.jangle.thread.test.nxi_8.lazyinit;

/**
 * 11.8、使用延迟初始化预防问题
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年10月29日 下午4:46:15
 * 
 */
public class M {

	public static void main(String[] args) {

		for (int i = 0; i < 29; i++) {
			var task = new Task();
			var thread = new Thread(task);
			thread.start();
		}

	}

}
