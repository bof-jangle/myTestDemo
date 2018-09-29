package xyz.jangle.object.test;

/**
 * M 主函数main
 * @author huhj
 * @email jangle@jangle.xyz
 * @time 2018年9月19日 上午9:33:04
 */
public class M {

	public static void main(String[] args) {
		Object o = new Object();
//		A a = (A) o;	//object对象转换为其他对象时编译通过，执行不通过
		
		A aa = new A();
		System.out.println(aa);
		Object oo = aa;
		System.out.println(oo);
		A aaCopy = (A)oo;
		System.out.println(aaCopy);
//		As asT = (As) oo;		
//		//此处的转换，编译可以通过，但执行不通过， oo其实为A类型对象，As为A类型的子类
//		System.out.println(asT);
		
		As asss = new As();
		System.out.println(asss);
		A aaa  = asss;
		System.out.println(aaa);
		//此处为对象上转型的体现，其子类对象可以赋值给其父类引用，犹如所有对象都可以被赋值为Object类型的引用一样

	}

}
