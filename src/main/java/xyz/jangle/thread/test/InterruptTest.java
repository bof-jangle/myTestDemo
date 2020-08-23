package xyz.jangle.thread.test;

public class InterruptTest {

	public static void main(String[] args) {
		testInterrupt();
	}

	/**
	 * 让线程执行2秒后进行中断，使用interrupt()方法中断线程
	 */
	public static void testInterrupt() {
		Thread thread = ippThread();
//		Thread thread = ippThreadInterruptByException();  //使用异常中断线程
//		Thread thread = ippThreadInterruptThrowsException();  //效果同上
//		Thread thread = ippThreadError(); //这个线程无法被中断
		
		thread.start();	// 开始执行
		
		//等待2秒
		try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 执行中断
		thread.interrupt();
		/*
		 * int j = 0; while(true) { System.out.println("j:" + j++); }
		 */
	}

	/**
	 * 创建一个线程，若该线程未被中断，则持续打印i++
	 */
	public static Thread ippThread() {
		Runnable r = () -> {
			int i = 0;
			while (!Thread.currentThread().isInterrupted()) {
				System.out.println(i++);
				// 1、ERROR
				/* 如果在这里添加sleep，那么这个线程无法被中断。 因为InterruptedException会清除中断状态
				 * 中断状态也就是isInterrupted返回的布尔值。
				 * @impotent 错误的行为
				try {
					Thread.sleep(20L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				*/
				// 2、SUCCESS
				/* 这样就可以成功中断
				try {
					Thread.sleep(20L);
				} catch (InterruptedException e) {
					e.printStackTrace();
					Thread.currentThread().interrupt();	//如果在此处添加中断当前线程的方法，那么调用者就可以成功中断
				}
				*/
			}
		};
		Thread thread = new Thread(r);
		return thread;
	}
	
	/**
	 * 这是一个错误的行为，这个线程无法被中断。
	 * 1、这样添加sleep，那么这个线程无法被中断。 因为InterruptedException会清除中断状态
	 * 也就是isInterrupted返回的布尔值。那么while中的判断将一直为true。
	 * 2、
	 * @return
	 */
	public static Thread ippThreadError() {
		Runnable r = () -> {
			int i = 0;
			while (!Thread.currentThread().isInterrupted()) {
				System.out.println(i++);
				try {
					Thread.sleep(20L);
				} catch (InterruptedException e) {
					e.printStackTrace();
//					Thread.currentThread().interrupt();	//如果这此处添加中断当前线程的方法，那么调用者就可以成功中断
				}
			}
		};
		Thread thread = new Thread(r);
		return thread;
	}
	
	/**
	 * 当sleep方法遇到中断状态时，会抛出InterruptedException，
	 * 而异常的抛出可以终止try块中的执行内容，从而结束while循环
	 * @return
	 */
	public static Thread ippThreadInterruptByException() {
		Runnable r = () -> {
			int i = 0;
			try {
				while (!Thread.currentThread().isInterrupted()) {
					System.out.println(i++);
					Thread.sleep(20L);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};
		Thread thread = new Thread(r);
		return thread;
	}
	
	/**
	 *  这个效果与ippThreadInterruptByException 异曲同工
	 * @return
	 */
	public static Thread ippThreadInterruptThrowsException(){
		Runnable r = () -> {
			try {
				println();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};
		Thread thread = new Thread(r);
		return thread;
	}
	
	public static void println() throws InterruptedException {
		int i = 0;
		while (!Thread.currentThread().isInterrupted()) {
			System.out.println(i++);
			Thread.sleep(20L);
		}
	}

}
