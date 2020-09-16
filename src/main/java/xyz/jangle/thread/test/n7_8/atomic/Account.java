package xyz.jangle.thread.test.n7_8.atomic;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.DoubleAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 *  模拟银行账户
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月16日 下午5:58:02
 * 
 */
public class Account {

	private final AtomicLong balance;

	private final LongAdder operations;

	private final DoubleAccumulator commission;

	public Account() {
		super();
		this.balance = new AtomicLong();
		this.operations = new LongAdder();
		this.commission = new DoubleAccumulator((x, y) -> x + y * 0.2, 0);
	}

	public AtomicLong getBalance() {
		return balance;
	}

	public LongAdder getOperations() {
		return operations;
	}

	public DoubleAccumulator getCommission() {
		return commission;
	}
	
	public void setBalance(long balance) {
		this.balance.set(balance);
		operations.reset();
		commission.reset();
	}
	
	/**
	 * 增加账户余额
	 * 
	 * @param amount
	 */
	public void addAmount(long amount) {
		this.balance.getAndAdd(amount);
		this.operations.increment();
		this.commission.accumulate(amount);
		System.out.println(this.balance.get());
	}
	
	/**
	 *  减少账户余额
	 * 
	 * @param amount
	 */
	public void subtractAmount(long amount) {
		this.balance.getAndAdd(-amount);
		this.operations.increment();
		this.commission.accumulate(amount);
		System.out.println(this.balance.get());
	}

}
