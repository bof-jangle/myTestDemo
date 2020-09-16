package xyz.jangle.thread.test.n7_8.atomic;

/**
 * 	模拟公司付款
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月16日 下午7:05:52
 * 
 */
public class Company implements Runnable {

	private final Account account;

	public Company(Account account) {
		super();
		this.account = account;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			account.addAmount(1000);
		}
	}

}
