package xyz.jangle.thread.test.nxi_8.lazyinit;

/**
 * 使用这种方式，会在高并发时，可能产生不止1个对象，会浪费内存资源
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年10月29日 下午4:46:46
 * 
 */
public class DBConnectionERROR {

	private DBConnectionERROR() {
		System.out.println(Thread.currentThread().getName() + ": Connection created.");
	}

	private static DBConnectionERROR dbc;

	/**
	 *  使用这种方式，会在高并发时，可能产生不止1个对象，会浪费内存资源
	 * 
	 * @author jangle
	 * @time 2020年10月29日 下午5:00:26
	 * @return
	 */
	public static DBConnectionERROR getConnection() {
		if (dbc == null) {
			dbc = new DBConnectionERROR();
		}
		return dbc;
	}

}
