package xyz.jangle.thread.test.n8_xii.stream;

import java.util.stream.StreamSupport;

/**
 *  8.12、实现自己的流生成器
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年10月12日 下午6:46:22
 * 
 */
public class M {

	public static void main(String[] args) {
		Item[][] items;
		items = new Item[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				var it = items[i][j] = new Item();
				it.setRow(i);
				it.setColumn(j);
				it.setName("Item " + i + " " + j);
			}
		}
		var mySpliterator = new MySpliterator(items, 0, items.length);
		// 重要 1、先执行trySplit方法来分割数据源(parallel参数为true, 若为false则直接执行2)
		// 2、调用forEachRemaining方法来处理
		// 3、（forEachRemaining委托tryAdvance方法执行）
		StreamSupport.stream(mySpliterator, true)
				.forEach(item -> System.out.println(Thread.currentThread().getName() + ":" + item.getName()));
	}

}
