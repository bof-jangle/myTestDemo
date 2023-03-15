package xyz.jangle.thread.test.n7_X.volatiledemo;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *  7.10 volatile 关键字
 *   volatile关键字允许指定一个变量的读取和存储都必须在主存中而不能在缓存中进行。
 *   volatile关键字要求必须刷新写操作，并要求读操作是从主存中获取最新的值（而不是缓存）
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月18日 下午4:39:45
 * 
 */
public class M {

	public static void main(String[] args) throws Exception {

		VolatileFlag volatileFlag = new VolatileFlag();
		Flag flag = new Flag();

		var vt = new VolatileTask(volatileFlag);
		var t = new Task(flag);

		new Thread(vt).start();
		new Thread(t).start();

		TimeUnit.SECONDS.sleep(1);
		System.out.println("M:开始停止VolatileTask:" + new Date());
		volatileFlag.flag = false;
		System.out.println("M:完成停止VolatileTask:" + new Date());

		System.out.println("M:开始停止Task:" + new Date());
		flag.flag = false;
		System.out.println("M:完成停止Task:" + new Date());

	}

}
