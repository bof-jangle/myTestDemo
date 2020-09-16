package xyz.jangle.thread.test.n7_8.atomic;

/**
 * 模拟银行扣款
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月16日 下午7:07:33
 * 
 */
public class Bank implements Runnable {

	private final Account account;

	public Bank(Account account) {
		super();
		this.account = account;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			account.subtractAmount(1000);
		}
	}

}
