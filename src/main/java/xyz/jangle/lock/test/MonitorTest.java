package xyz.jangle.lock.test;

/**
 * 监视器的概念
 * 
 * 1、监视器只包含私有字段的类
 * 2、监视器类每个对象有一个关联的锁
 * 3、所有方法由这个锁锁定（类似所有方法都含有关键字 synchronized）
 * 4、锁可以有任意多个相关联的条件
 * 
 * 
 * 此处的DEMO，主要是证明3，如果不是所有的方法都被锁定，那么会产生什么样意外的结果。
 * DEMO解释：
 * i初始为0
 * 线程1 执行方法后，希望等待i==1之后，再执行后续的内容
 * 线程2 去执行i+1 ，并通知所有等待的线程继续执行。 
 * 如此是可以正常执行完程序的。
 * 
 * 线程3 是 public void ipp()  执行i+1 。   那么会在线程2执行通知之前就对i+1 ,从而导致i==2 
 * 线程3 是 public synchronized void ipp()  执行i+1 。   那么会在线程3会等待线程2 和线程1执行完毕再执行。因为是线程2先获得的锁
 * 
 * 
 * @author jangle E-mail: jangle@jangle.xyz
 * @version 2020年5月20日 下午6:08:54 类说明
 */
public class MonitorTest {

	private static volatile int i = 0;		// 如果不添加volatile 关键字， 那么多个Thread对i的修改可能对其他线程不可见，从而无法

	public static void main(String[] args) {

		MonitorTest monitor = new MonitorTest();
		
		// 线程1
		new Thread(() -> {
			monitor.testWait();
		}).start();	// 启用线程1条用wait方法。
		try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 线程2
		new Thread(()->{
			monitor.testNotifyAll();
		}).start();;	// 启用线程2调用nofityAll 方法
		try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 线程3
		new Thread(()->{
			// 如果ipp 上了锁，会等待testNotifyAll等执行完毕，再获取锁之后执行。
			// 如果ipp 没有加锁，会提前对i进行 +1操作，虽然调用的稍微晚一点，但是，由于testNotifyAll的执行逻辑长，从而先被执行。
			monitor.ipp();		
		}).start();

	}

	/**
	 * 创建等待条件（加锁的方法）
	 */
	public synchronized void testWait() {
		do{
			System.out.println(Thread.currentThread().getName() + "：进入等待 i="+i);
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("i="+i+".此处如果i==1 则跳出循环执行后续逻辑， 否则，则会一直等待 ");
			if(i == 1) {
				System.out.println("i==1 跳出循环");
				break;
			}
		}while(true);
		while (i < 2) {
			System.out.println(Thread.currentThread().getName() + "：获得运行权限 i="+i);
			i++;
		}
		System.out.println(Thread.currentThread().getName() + "：运行结束");
	}

	/**
	 * 进行通知(加锁的方法)
	 */
	public synchronized void testNotifyAll() {
		try {
			Thread.sleep(5000L);		// 这里做了一些其他的操作
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		i++;		// 此处进行i++之后， i应该等于1 ，会释放wait条件的等待
		System.out.println(Thread.currentThread().getName() + "：开始通知");
		notifyAll(); // 这个方法的调用需要依赖 synchronized (o) 需要先获取锁，再执行锁信号的通知。
	}
	
	/**
	 * 公开的方法
	 */
	public void ipp() {	//TODO  1.此方法如果不加锁，会导致 i最终变成2
//	public synchronized void ipp() {	//TODO 
		i++;
	}

}
