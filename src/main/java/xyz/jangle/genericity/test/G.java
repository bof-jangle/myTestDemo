package xyz.jangle.genericity.test;

import java.util.function.Supplier;

/**
 * 用于使用supplier创建对象
 * @author jangle
 *
 */
public class G {
	
	/**
	 * 创建对象
	 * @param <T>
	 * @param supp
	 * @return
	 */
	public static <T> T create(Supplier<T> supp) {
		return supp.get();
	}

}
