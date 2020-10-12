package xyz.jangle.thread.test.n8_xi.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * 自定义原子性对象
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年10月11日 下午4:43:37
 * 
 */
@SuppressWarnings("serial")
public class ParkingCounter extends AtomicInteger {
	
	private final int maxNumber;

	public ParkingCounter(int maxNumber) {
		set(0);
		this.maxNumber = maxNumber;
	}
	/**
	 * 进车
	 * 
	 * @return
	 */
	public boolean carIn() {
		for(;;) {
			int value = get();
			if (value == maxNumber) {
				System.out.println("停车位已满");
				return false;
			} else {
				int newValue = value +1;
				boolean changed = compareAndSet(value, newValue);
				if(changed) {
					System.out.println("成功停车:"+newValue);
					return true;
				}
			}
		}
	}
	/**
	 * 出车
	 * 
	 * @return
	 */
	public boolean carOut() {
		for(;;) {
			int value = get();
			if (value == 0) {
				System.out.println("没有车辆可以开出");
				return false;
			} else {
				int newValue = value -1;
				boolean changed = compareAndSet(value, newValue);
				if(changed) {
					System.out.println("成功出车:"+newValue);
					return true;
				}
			}
		}
	}
	
	

}
