package xyz.jangle.thread.test.n2_6.condition;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 数据缓冲区，建立在生产者和消费者之间。
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年7月25日 下午10:12:59
 * 
 */
public class Buffer {

	// 存放共享数据
	private final LinkedList<String> buffer;
	// 缓冲区长度
	private final int maxSize;

	private final ReentrantLock lock;

	private final Condition insertCondition;

	private final Condition getCondition;

	private boolean pendingLines;

	public Buffer(int maxSize) {
		super();
		this.maxSize = maxSize;
		this.lock = new ReentrantLock();
		insertCondition = lock.newCondition();
		getCondition = lock.newCondition();
		pendingLines = true;
		buffer = new LinkedList<String>();
	}

	// 插入缓冲数据
	public void insert(String line) {
		lock.lock();
		try {
			while (buffer.size() == maxSize) {
				System.out.println(Thread.currentThread().getName()+"进入等待");
				insertCondition.await();
			}
			buffer.offer(line);
			System.out.println(Thread.currentThread().getName()+"插入了一行记录"+line);
			getCondition.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}

	// 获取缓冲数据
	public String get() {
		String line = null;
		lock.lock();
		try {
			// 当缓冲区没有内容，但文本文件中还有未写入的内容时，进行等待。
			while((buffer.size() == 0) && (hasPendingLines())) {
				System.out.println(Thread.currentThread().getName()+"进入等待");
				getCondition.await();
			}
			if(hasPendingLines()) {
				line = buffer.poll();
				System.out.println(Thread.currentThread().getName()+"读取了一行数据"+line+"缓冲区还剩:"+buffer.size());
				insertCondition.signalAll();
			}
		} catch (Exception e) {
		}finally {
			lock.unlock();
		}
		return line;
	}
	
	/**
	 * 	用于设置是否还有文本内容（在文本文件内容全部写入缓冲后设置为false）
	 * 
	 * @author jangle
	 * @time 2020年7月26日 上午7:59:13
	 * @param pendingLines
	 */
	public synchronized void setPendingLines(boolean pendingLines) {
		this.pendingLines = pendingLines;
	}
	
	/**
	 *  是否还有未处理的文本内容（文本文件内容 || 缓冲文件内容）
	 * 
	 * @author jangle
	 * @time 2020年7月26日 上午7:58:32
	 * @return
	 */
	public synchronized boolean hasPendingLines() {
		return pendingLines || buffer.size() > 0;
	}
	

}
