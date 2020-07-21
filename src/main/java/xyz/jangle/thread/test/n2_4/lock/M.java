package xyz.jangle.thread.test.n2_4.lock;

/**
 * 
 * 公平锁（最长等待优秀策略）
 * 重入锁的公平参数的使用
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年7月21日 下午10:10:45
 * 
 */
public class M {

	public static void main(String[] args) {

//		testFair();

		testNoFair();

	}

	/**
	 * 	采用随机，没有优先的策略
	 * 
	 * @author jangle
	 * @time 2020年7月21日 下午10:19:18
	 */
	public static void testNoFair() {
		PrintQueue queue = new PrintQueue(false);
		for (int j = 0; j < 10; j++) {
			new Thread(new Job(queue), "Thread" + j).start();
			try {
				Thread.sleep(500L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 	使用公平锁（最长等待优先的策略）
	 * 
	 * @author jangle
	 * @time 2020年7月21日 下午10:18:55
	 */
	public static void testFair() {
		PrintQueue queue = new PrintQueue(true);
		for (int j = 0; j < 10; j++) {
			new Thread(new Job(queue), "Thread" + j).start();
			try {
				Thread.sleep(500L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
