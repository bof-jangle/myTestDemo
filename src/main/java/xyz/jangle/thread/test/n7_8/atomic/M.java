package xyz.jangle.thread.test.n7_8.atomic;

/**
 * 7.8、原子性变量
 * AtomicLong: 可进行原子操作的变量
 * LongAdder: 效率更高的原子操作变量，使用N个累加数实现。
 * LongAccumulator:效率更高的原子操作变量，使用累加“操作”实现。
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月16日 下午5:56:18
 * 
 */
public class M {

	public static void main(String[] args) {

		var account = new Account();
		account.setBalance(999);
		var company = new Company(account);
		var bank = new Bank(account);
		var companyThread = new Thread(company);
		var bankThread = new Thread(bank);
		System.out.println("初始金额" + account.getBalance());
		companyThread.start();
		bankThread.start();

		try {
			companyThread.join();
			bankThread.join();
			System.out.println("balance:" + account.getBalance());
			System.out.println("operation:" + account.getOperations());
			System.out.println("commission:" + account.getCommission());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
