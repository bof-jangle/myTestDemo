package xyz.jangle.thread.test.n8_x.prioritytransfer;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 	基于优先级的传递队列
 * @author jangle
 * @param <E>
 * @email jangle@jangle.xyz
 * @time 2020年10月9日 上午10:50:14
 * 
 */
@SuppressWarnings("serial")
public class MyPriorityTransferQueue<E> extends PriorityBlockingQueue<E> implements TransferQueue<E> {

	private final AtomicInteger counter;
	// 传输队列：当没有消费者时，生产者的元素将存在这个数据结构中。
	private final LinkedBlockingQueue<E> transferred;

	private final ReentrantLock lock;

	public MyPriorityTransferQueue() {
		super();
		counter = new AtomicInteger(0);
		lock = new ReentrantLock();
		transferred = new LinkedBlockingQueue<E>();
	}

	@Override
	public boolean tryTransfer(E e) {
		boolean value = false;
		try {
			lock.lock();
			if (counter.get() == 0) {
				value = false;
			} else {
				put(e);
				value = true;
			}
		} finally {
			lock.unlock();
		}
		return value;
	}

	@Override
	public void transfer(E e) throws InterruptedException {
		lock.lock();
		// 如果有消费者在等待，则存入优先队列。
		if (counter.get() != 0) {
			try {
				put(e);
			} finally {
				lock.unlock();
			}
		} 
		// 如果没有消费者在等待，则放入传输队列。（并等待）
		else {
			try {
				transferred.add(e);
			} finally {
				lock.unlock();
			}
			// 当没有消费者时，等待消费者的出现。生产线程进入等待。
			synchronized (e) {
				e.wait();
			}
		}
	}

	@Override
	public boolean tryTransfer(E e, long timeout, TimeUnit unit) throws InterruptedException {
		lock.lock();
		// 如果有消费者在等待，则立即发送元素。
		if (counter.get() != 0) {
			try {
				put(e);
			} finally {
				lock.unlock();
			}
			return true;
		}
		// 如果没有消费者在等待，
		else {
			long newTimeout = 0;
			try {
				transferred.add(e);
				// 把时间单位转换为单位
				newTimeout = TimeUnit.MILLISECONDS.convert(timeout, unit);
			} finally {
				lock.unlock();
			}
			// 当没有消费者时，等待消费者的出现。生产线程等待newTimeout毫秒后，元素未消费，则移除并返回false，
			// 元素被消费则返回true。
			e.wait(newTimeout);
			lock.lock();
			boolean value;
			try {
				if (transferred.contains(e)) {
					transferred.remove(e);
					value = false;
				} else {
					value = true;
				}
			} finally {
				lock.unlock();
			}
			return value;
		}
	}

	@Override
	public boolean hasWaitingConsumer() {
		return counter.get() != 0;
	}

	@Override
	public int getWaitingConsumerCount() {
		return counter.get();
	}

	@Override
	public E take() throws InterruptedException {
		lock.lock();
		E value;
		try {
			counter.incrementAndGet();
			value = transferred.poll();
			if (value == null) {
				// 因为 super.take()中使用了lock，故此处要进行解锁
				lock.unlock();
				value = super.take();
				lock.lock();
			} else {
				// 此处synchronized未知。
				synchronized (value) {
					value.notify();
				}
			}
			counter.decrementAndGet();
		} finally {
			lock.unlock();
		}
		return value;
	}

}
