package xyz.jangle.thread.test.n5_2.forkjoin;

import java.util.ArrayList;
import java.util.List;

/**
 * 	产品工厂
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月26日 下午5:04:28
 * 
 */
public class ProductListGenerator {

	/**
	 * 创建一个产品列表
	 * 
	 * @param size
	 * @return
	 */
	public List<Product> generate(int size) {
		ArrayList<Product> list = new ArrayList<Product>();
		for (int i = 0; i < size; i++) {
			Product product = new Product();
			product.setName("Product " + i);
			product.setPrice(10);
			list.add(product);
		}
		return list;
	}

}
