package xyz.jangle.thread.test.n8_xii.stream;

import java.util.Spliterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.StreamSupport;

/**
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年10月14日 下午4:47:46
 * 
 */
public class MM {
	public static void main(String[] args) {
		
		
		var t = new ConcurrentLinkedQueue<Integer>();
		t.add(1);
		t.add(2);
		t.stream().parallel().forEach(i -> System.out.println(i));
		
		Spliterator<Integer> spliterator = t.spliterator();
		StreamSupport.stream(spliterator, true).forEach(i -> System.out.println(i));
	}

}
