package xyz.jangle.thread.test.n6_7.flatmap;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月6日 上午9:41:18
 * 
 */
public class FileGenerator {

	/**
	 * 	模拟文件
	 */
	public static List<String> generateFile(int size) {
		ArrayList<String> file = new ArrayList<String>();
		for (int i = 0; i < size; i++) {
			file.add("Constructs an empty list with an initial capacity of ten.");
		}
		return file;
	}

}
