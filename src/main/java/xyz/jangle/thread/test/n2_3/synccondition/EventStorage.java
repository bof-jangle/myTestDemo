package xyz.jangle.thread.test.n2_3.synccondition;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年7月16日 下午10:04:56
 * 
 */
public class EventStorage {

	private int maxSize;

	private Queue<Date> storage;

	public EventStorage() {
		maxSize = 10;
		storage = new LinkedList<Date>();
	}
	
	
	public synchronized void set() {
		while(storage.size() == maxSize) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		storage.offer(new Date());
		System.out.printf("Set: %d",storage.size());
		notify();
	}
	
	public synchronized void get() {
		while(storage.size() == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		String string = storage.poll().toString();
		System.out.printf("Get: %d:%s \n",storage.size(),string);
		notify();
	}
	
	

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public Queue<Date> getStorage() {
		return storage;
	}

	public void setStorage(Queue<Date> storage) {
		this.storage = storage;
	}

}
