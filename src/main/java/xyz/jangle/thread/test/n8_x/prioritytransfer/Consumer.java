package xyz.jangle.thread.test.n8_x.prioritytransfer;

/**
 *  消费者
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年10月10日 下午5:31:05
 * 
 */
public class Consumer implements Runnable {

	private final MyPriorityTransferQueue<Event> buffer;

	public Consumer(MyPriorityTransferQueue<Event> buffer) {
		super();
		this.buffer = buffer;
	}

	@Override
	public void run() {

		for (int i = 0; i < 1002; i++) {
			try {
				var value = buffer.take();
				System.out.println("Consumer: " + value.getThread() + ":" + value.getPriority());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
