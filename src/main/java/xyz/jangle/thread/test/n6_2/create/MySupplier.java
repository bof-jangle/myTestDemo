package xyz.jangle.thread.test.n6_2.create;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

/**
 * 	Supplier类
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月1日 下午6:23:07
 * 
 */
public class MySupplier implements Supplier<String> {

	private final AtomicInteger counter;

	public MySupplier() {
		counter = new AtomicInteger();
	}

	@Override
	public String get() {
		int v = counter.getAndAdd(1);
		return "String " + v;
	}
	
	public static void main(String[] args) {
		MySupplier mySupplier = new MySupplier();
		String string = mySupplier.get();
		System.out.println(string);
	}

}
