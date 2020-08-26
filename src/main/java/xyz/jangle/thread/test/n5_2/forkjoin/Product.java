package xyz.jangle.thread.test.n5_2.forkjoin;

/**
 * 	产品类，存储产品名称和价格（model）
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月26日 下午5:03:06
 * 
 */
public class Product {

	private String name;
	private double price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
