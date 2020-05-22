package xyz.jangle.genericity.test;

import java.util.function.Supplier;

/**
 * 
 * 
 * @author jangle E-mail: jangle@jangle.xyz
 * @version 2020年5月22日 上午11:40:52 类说明
 */
public class GenericityTest {

	public static void main(String[] args) throws Exception {
		
		
//		testArray();
//		testArray2();
//		testSupplier();
//		testNewInstance();
	}

	/**
	 * 8.6.5使用泛型进行实例化
	 * 
	 * 新方式和传统方式的比较
	 * 
	 * Supplier<T>是函数式接口，表示结果的提供者，接收一个表达式，返回T类 <T>此Supplier提供的结果类型
	 * 
	 * @throws Exception
	 */
	public static void testNewInstance() throws Exception {
		Supplier<GenericityTest> a = GenericityTest::new;
		a.get().backk("123321");
		// 上述操作等于如下操作 以上需要jdk8 以下是传统方式
		GenericityTest.class.getConstructor().newInstance().backk("123321");
	}

	/**
	 * 8.6.5使用泛型进行实例化
	 * 
	 * 对Supplier进行使用
	 * Supplier<T>是函数式接口，表示结果的提供者，接收一个表达式，返回T类 <T>此Supplier提供的结果类型
	 */
	public static void testSupplier() {
		GenericityTest create2 = create(GenericityTest::new);
		String backk = create2.backk(create(String::new));
		System.out.println(backk);
		String create = create(String::new);
		create += "1";
		System.out.println(create);
	}

	/**
	 * 8.6.4使用泛型构造泛型数组
	 */
	public static void testArray2() {
		Integer[] array = array(1, 2, 3);
		GenericityTest test = new GenericityTest();
		for (Integer i : array) {
			System.out.print(i);
			System.out.println(test.backk(i));
		}

		String[] array2 = array("1", "2", "3", "4");
		for (String a : array2) {
			System.out.println(back(a));
		}
		String[] array3 = array(array2);

		for (String b : array3) {
			System.out.println(b);
		}
	}

	/**
	 * 8.6.3使用泛型构造泛型数组
	 * jdk 10支持
	 */
	@SuppressWarnings("unchecked")
	public static void testArray() {
		var s = (Pair<String>[]) new Pair<?>[10];
		s[0] = new Pair<String>();
		s[0].aaa();
		/*
		 * String[] arr = new String[10]; arr[0] = "0";
		 */
	}

	@SafeVarargs
	static <E> E[] array(E... arr) {
		return arr;
	}

	static <E> E back(E e) {
		return e;
	}

	private <E> E backk(E e) {
		System.out.println("backk");
		return e;
	}

	static <E> E create(Supplier<E> constr) {
		return constr.get();
	}

}
