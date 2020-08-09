package xyz.jangle.thread.test.n3_3.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * 	这是一个会议进程，它等待会议指定的人数到齐后开始会议。
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月9日 下午5:02:52
 * 
 */
public class VideoConference implements Runnable{
	
	//	倒计数控制器（锁）
	private final CountDownLatch controller;
	
	public VideoConference(int num) {
		super();
		this.controller = new CountDownLatch(num);
	}
	
	/**
	 * 	参会人员签到方法
	 * @param name	参会人员名称
	 */
	public void arrive(String name) {
		System.out.printf("%s抵达，",name);
		controller.countDown();
		System.out.printf("还需要等待%d 个人\n",controller.getCount());
	}

	@Override
	public void run() {
		System.out.println("会议开始等待"+controller.getCount()+"个人员参加");
		try {
			controller.await();
			System.out.println("开始会议...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	

}
