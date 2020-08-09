package xyz.jangle.thread.test.n3_3.countdownlatch;

import java.util.concurrent.TimeUnit;

/**
 * 	参会者类，它作为单独的线程，模拟参会者在随机时间后抵达会场进行签到。
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月9日 下午5:19:05
 * 
 */
public class Participant implements Runnable {

	// 会议对象
	private VideoConference conference;
	// 参会者名称
	private String name;

	public Participant(VideoConference conference, String name) {
		super();
		this.conference = conference;
		this.name = name;
	}

	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep((long) (Math.random() * 10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 在等待随机时间后进行签到
		conference.arrive(name);

	}

}
