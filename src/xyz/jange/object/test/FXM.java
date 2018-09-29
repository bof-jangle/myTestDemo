package xyz.jange.object.test;
/**
 * FXM 泛型main方法的缩写  Java的泛型其实是由Object实现的，包装了一下，不算真正的泛型。
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
		FX<Object> tx = new FX<Object>(2,"2");
		System.out.println(tx);
		Object o = tx;
		System.out.println(o);
		FX<A> txa = (FX<A>) o;
		System.out.println(txa);
		int id = txa.getId();
		System.out.println(id);
		System.out.println(txa.getT());	//输出字符串2 此时的泛型 T 对象是 "2" 字符串String
//		System.out.println(txa.getT().getId());	// 报错，此时虚拟机会对T进行强制类型转化，String不能转为类型A
//		System.out.println(txa.getT().getClass());	// 报错，此时虚拟机会对T进行强制类型转化，String不能转为类型A
		//由此，说明，当且仅当程序对泛型进行方法调用时，会做强制类型的转化，否则，它将作为Object对象处理。
//		A a = (A)"2";	//编译不通过
		Object oo = "2";
		A aa = (A)oo; //编译通过，执行不通过。
		//其实这个功能在AOP的@Around切面中应用会很巧妙。可参考我的webapp项目的统一返回类型的设计。
	}
}
