package xyz.jangle.thread.test.n7_XI.varhandle;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

/**
 * 对变量进行加法操作
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月19日 下午6:09:33
 * 
 */
public class Incrementer implements Runnable {

	private Account account;

	public Incrementer(Account account) {
		super();
		this.account = account;
	}

	@Override
	public void run() {
		VarHandle handler;
		try {
			for (int i = 0; i < 10000; i++) {
				handler = MethodHandles.lookup().in(Account.class).findVarHandle(Account.class, "amount", double.class);
				handler.getAndAdd(account, 100);
				account.unsafeAmount += 100;
			}
		} catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}

	}

}
