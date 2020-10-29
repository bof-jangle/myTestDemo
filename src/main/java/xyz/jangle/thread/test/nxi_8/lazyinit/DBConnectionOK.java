package xyz.jangle.thread.test.nxi_8.lazyinit;

/**
 * 使用内部静态类的静态final属性，解决高并发时，错误地创建N+个对象。
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年10月29日 下午4:46:46
 * 
 */
public class DBConnectionOK {

//	private static DBConnectionOK dbc;

	private DBConnectionOK() {
		System.out.println(Thread.currentThread().getName() + ": Connection created.");
	}

	private static class LazyDBConnection {
		private static final DBConnectionOK INSTANCE = new DBConnectionOK();
	}

	public static DBConnectionOK getConnection() {
		return LazyDBConnection.INSTANCE;
//		if (dbc == null) {
//			dbc = new DBConnectionOK();
//		}
//		return dbc;
	}

}
