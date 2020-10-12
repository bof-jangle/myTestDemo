package xyz.jangle.thread.test.n8_xi.atomic;

/**
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年10月11日 下午4:48:56
 * 
 */
public class Sensor1 implements Runnable {

	private final ParkingCounter counter;

	public Sensor1(ParkingCounter counter) {
		this.counter = counter;
	}

	@Override
	public void run() {

		counter.carIn();
		counter.carIn();
		counter.carIn();
		counter.carIn();
		counter.carOut();
		counter.carOut();
		counter.carOut();
		counter.carIn();
		counter.carIn();
		counter.carIn();

	}

}
