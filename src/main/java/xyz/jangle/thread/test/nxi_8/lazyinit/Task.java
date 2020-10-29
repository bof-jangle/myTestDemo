package xyz.jangle.thread.test.nxi_8.lazyinit;

/**
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年10月29日 下午4:51:42
 * 
 */
public class Task implements Runnable {

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + ": 获取连接...");
		var connection = DBConnectionOK.getConnection();
//		var connection = DBConnectionERROR.getConnection();
		System.out.println(Thread.currentThread().getName() + ": 连接获取结束..." + connection);
	}

}
