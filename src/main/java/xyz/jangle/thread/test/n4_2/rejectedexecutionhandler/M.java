package xyz.jangle.thread.test.n4_2.rejectedexecutionhandler;

import java.util.concurrent.TimeUnit;

/**
 * 4.2 創建一個綫程執行器並實現其拒絕策略
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月17日 下午3:54:48
 * 
 */
public class M {

	public static void main(String[] args) {
		Server server = new Server();
		System.out.println("Main:開始...");
		for (int i = 0; i < 20; i++) {
			Task task = new Task("Task" + i);
			server.executeTask(task);
		}
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Main:停止執行器Executor.");
		server.endServer();
		System.out.println("Main:添加另外的任務");
		for (int i = 0; i < 10; i++) {
			Task task = new Task("將被拒絕的任務"+i);
			server.executeTask(task);
		}
		System.out.println("Main:結束");
	}

}
