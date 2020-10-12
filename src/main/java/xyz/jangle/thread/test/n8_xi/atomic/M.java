package xyz.jangle.thread.test.n8_xi.atomic;
/**
 * 8.11、自定义原子性对象
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年10月11日 下午4:41:24
 * 
 */
public class M {

	public static void main(String[] args) throws Exception {
		ParkingCounter counter = new ParkingCounter(5);
		var sensor1 = new Sensor1(counter);
		var sensor2 = new Sensor2(counter);
		var thread1 = new Thread(sensor1);
		var thread2 = new Thread(sensor2);
		thread1.start();
		thread2.start();
		thread1.join();
		thread2.join();
		System.out.println("M:车子数量"+counter.get());
		System.out.println("M:程序结束");

	}

}
