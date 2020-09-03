package xyz.jangle.thread.test.n6_4.collect;

/**
 * 	辅助类
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月3日 下午9:28:51
 * 
 */
public class Counter {

	private String value;
	private int counter;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public synchronized void increment() {
		counter++;

	}

}
