package xyz.jangle.thread.test.n4_2.rejectedexecutionhandler;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月17日 下午4:03:18
 * 
 */
public class RejectedTaskHandler implements RejectedExecutionHandler {

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		System.out.println("Rejected:runnable:" + r.toString() + "被拒絕");
		System.out.println("Rejected:executor:" + executor.toString());
		System.out.println("Rejected:Terminating:" + executor.isTerminating());
		System.out.println("Rejected:Terminated:" + executor.isTerminated());

	}

}
