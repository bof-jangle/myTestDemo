package xyz.jangle.thread.test.nxi_13.streamvsforkjoin;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

/**
 *  11.13、使用流处理大数据（比较Stream和forkjoin的效率）
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年11月30日 下午7:58:04
 * 
 */
public class M {

	public static void main(String[] args) {
		var ps = Person.generatorPersons(10000000);
		// 使用流
		var start = new Date();
		ConcurrentMap<String, List<Person>> collect = ps.parallelStream()
				.collect(Collectors.groupingByConcurrent(p -> p.getArea()));
		var end = new Date();
		System.out.println(collect.size() + ":Stream::Time:" + (end.getTime() - start.getTime()));
		// 使用fork/join框架
		start = new Date();
		var forkjoinMap = new ConcurrentHashMap<String, ConcurrentLinkedDeque<Person>>();
		var personMapTask = new PersonMapTask(ps, forkjoinMap);
		ForkJoinPool.commonPool().invoke(personMapTask);
		end = new Date();
		System.out.println(forkjoinMap.size() + ":ForkJoinPool::Time:" + (end.getTime() - start.getTime()));

	}

}
