package xyz.jangle.boxup.test;

/**
 * @author huhj
 * @email jangle@jangle.xyz
 * @time 2018年9月29日 上午10:15:50
 */
public class BoxupTest {

	public static void main(String[] args) {
		Integer a = 1;
		Integer b = 2;
		Integer c = 3;
		Integer d = 3;
//		Integer e = 128;
//		Integer f = 128;
		Integer aa = 1111;
		Integer bb = 2222;
		Integer cc = 3333;
		Integer dd = 3333;

		Long g = 3L;

		System.out.println("c == d ? :" + (c == d));
		System.out.println("cc == dd ? :" + (cc == dd)); // false预存的自动拆箱只到达127。 2^7 -1 见Integer.IntegerCache
		System.out.println("c == a+b ? :" + (c == (a + b)));
		System.out.println("cc == aa+bb ? :" + (cc == (aa + bb))); // true 当遇到运算符的时，会进行自动拆装。
		System.out.println("c.equals(a+b) ? :" + (c.equals(a + b)));
		System.out.println("g == (a+b) ? :" + (g == (a + b)));
		System.out.println("g.equals(a+b) ? :" + (g.equals(a + b))); // false Long的 equeals方法做了类型的判断。

	}

}
