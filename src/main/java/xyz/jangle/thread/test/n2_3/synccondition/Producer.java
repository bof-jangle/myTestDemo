package xyz.jangle.thread.test.n2_3.synccondition;
/**
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年7月16日 下午10:16:21
 * 
 */
public class Producer implements Runnable {
	
	private EventStorage storage;
	
	

	public Producer(EventStorage storage) {
		super();
		this.storage = storage;
	}



	@Override
	public void run() {
		
		for(int i=0;i<100;i++) {
			storage.set();
		}

	}

}
