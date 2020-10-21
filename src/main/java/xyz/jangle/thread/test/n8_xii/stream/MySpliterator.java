package xyz.jangle.thread.test.n8_xii.stream;

import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * 自定义分离器
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年10月13日 下午4:50:12
 * 
 */
public class MySpliterator implements Spliterator<Item> {

	private Item[][] items;
	private int start, end, current;

	public MySpliterator(Item[][] items, int start, int end) {
		super();
		this.items = items;
		this.start = start;
		this.end = end;
		this.current = start;
	}

	@Override
	public boolean tryAdvance(Consumer<? super Item> action) {
		System.out.println("tryAdvance: start :" + start + "," + end + "," + current);
		if (current < end) {
			for (int i = 0; i < items[current].length; i++) {
				action.accept(items[current][i]);
			}
			current++;
			System.out.println("tryAdvance: end:true");
			return true;
		}
		System.out.println("tryAdvance: end:false");
		return false;
	}

	@Override
	public void forEachRemaining(Consumer<? super Item> action) {
		System.out.println("forEachRemaining:start");
		Spliterator.super.forEachRemaining(action);
		System.out.println("forEachRemaining:end");
	}

	@Override
	public Spliterator<Item> trySplit() {
		System.out.println("trySplit:start");
		if (end - start <= 2) {
			System.out.println("trySplit:end");
			return null;
		}
		int mid = start + ((end - start) / 2);
		int newStart = mid;
		int newEnd = end;
		// 修改当前end的值，因为对其进行了拆分。
		end = mid;
		System.out.println("trySplit:end : start=" + start + ",mid=" + mid + ",end=" + end + ",newStart=" + newStart
				+ ",newEnd=" + newEnd + ",current=" + current);
		return new MySpliterator(items, newStart, newEnd);
	}

	@Override
	public long estimateSize() {
		return end - current;
	}

	@Override
	public int characteristics() {
		return ORDERED | SIZED | SUBSIZED;
	}

}
