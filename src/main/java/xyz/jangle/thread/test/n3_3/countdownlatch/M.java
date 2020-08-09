package xyz.jangle.thread.test.n3_3.countdownlatch;

/**
 * 3.3、等待N个并发事件（条件）
 *  CountDownLatch DEMO 简单的视频会议系统
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月9日 下午4:57:33
 * 
 */
public class M {

	public static void main(String[] args) {
		// 构造一个由10个人参加的会议
		VideoConference conference = new VideoConference(10);
		// 开始会议
		new Thread(conference).start();
		for (int i = 0; i < 10; i++) {
			new Thread(new Participant(conference, "参会者" + i)).start();
		}

	}

}
