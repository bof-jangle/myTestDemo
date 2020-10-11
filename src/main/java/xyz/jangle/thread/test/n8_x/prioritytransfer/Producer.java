package xyz.jangle.thread.test.n8_x.prioritytransfer;

/**
 *  生产者
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年10月10日 下午5:22:29
 * 
 */
public class Producer implements Runnable {

	private final MyPriorityTransferQueue<Event> buffer;

	public Producer(MyPriorityTransferQueue<Event> buffer) {
		super();
		this.buffer = buffer;
	}

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			Event event = new Event(Thread.currentThread().getName(), i);
			buffer.put(event);
		}

	}

}
