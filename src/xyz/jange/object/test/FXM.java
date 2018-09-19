package xyz.jange.object.test;
/**
 * FXM 泛型main方法的缩写
 * @author huhj
 * @email  jangle@jangle.xyz
 * @time   2018年9月19日 上午10:05:19
 */
public class FXM {

	public static void main(String[] args) {
//		FX<Object> tx = new FX<Object>();
//		System.out.println(tx);
//		FX<A> txa = (FX<A>) tx;
//		System.out.println(txa);
		//对于泛型，我们不能直接将<Object>类型的泛型对象转换为其他的泛型对象。

		//但，可以通过Object这个中间类型进行转换。 如下：
		FX<Object> tx = new FX<Object>(2);
		System.out.println(tx);
		Object o = tx;
		System.out.println(o);
		FX<A> txa = (FX<A>) o;
		System.out.println(txa);
		int id = txa.getId();
		System.out.println(id);
		//其实这个功能在AOP的@Around切面中应用会很巧妙。可参考我的webapp项目的统一返回类型的设计。
	}
}
