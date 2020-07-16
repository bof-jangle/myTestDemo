package xyz.jangle.thread.test.n2_3.synccondition;

/**
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年7月16日 下午10:18:00
 * 
 */
public class Consumer implements Runnable {

	private EventStorage storage;

	public Consumer(EventStorage storage) {
		super();
		this.storage = storage;
	}

	@Override
	public void run() {

		for (int i = 0; i < 100; i++)
			storage.get();

	}

}
