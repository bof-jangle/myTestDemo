package xyz.jangle.thread.test.n6_3.mapreduce;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *  坐标点 工厂
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月2日 下午8:45:11
 * 
 */
public class PointGenerator {

	public static List<Point> generatorPointList(int size) {

		List<Point> ret = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < size; i++) {
			Point point = new Point();
			point.setX(random.nextDouble());
			point.setY(random.nextDouble());
			ret.add(point);
		}
		return ret;

	}

}
