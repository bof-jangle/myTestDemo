package xyz.jangle.thread.test.n7_7.concurrenthashmap;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月15日 下午5:34:57
 * 
 */
public class HashFiller implements Runnable {

	private ConcurrentHashMap<String, ConcurrentLinkedDeque<Operation>> userHash;

	public HashFiller(ConcurrentHashMap<String, ConcurrentLinkedDeque<Operation>> userHash) {
		this.userHash = userHash;
	}

	@Override
	public void run() {
		var randomGenerator = new Random();
		for (int i = 0; i < 100; i++) {
			var operation = new Operation();
			String user = "USER" + randomGenerator.nextInt(100);
			operation.setUser(user);
			String action = "OP" + randomGenerator.nextInt(10);
			operation.setOperation(action);
			operation.setTime(new Date());
			// 存入map。
			addOperationToHash(userHash, operation);
		}

	}
	/**
	 * 	将operation进行归类，通过user值进行分类到指定的Queue中。由map管理这些Queue。
	 * 
	 * @author jangle
	 * @time 2020年9月15日 下午5:45:17
	 * @param userHash
	 * @param operation
	 */
	private void addOperationToHash(ConcurrentHashMap<String, ConcurrentLinkedDeque<Operation>> userHash,
			Operation operation) {
		// 这个computeIfAbsent是个并发操作，具有原子性，防止并发时的丢失与冲突
		ConcurrentLinkedDeque<Operation> opList = userHash.computeIfAbsent(operation.getUser(),
				user -> new ConcurrentLinkedDeque<>());
		opList.add(operation);
	}

}
