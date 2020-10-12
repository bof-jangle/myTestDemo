package xyz.jangle.thread.test.n8_xi.atomic;

/**
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年10月11日 下午4:50:13
 * 
 */
public class Sensor2 implements Runnable {

	private ParkingCounter counter;

	public Sensor2(ParkingCounter counter) {
		this.counter = counter;
	}

	@Override
	public void run() {
		counter.carIn();
		counter.carOut();
		counter.carOut();
		counter.carIn();
		counter.carIn();
		counter.carIn();
		counter.carIn();
		counter.carIn();
		counter.carIn();

	}

}
