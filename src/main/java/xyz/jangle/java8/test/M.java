package xyz.jangle.java8.test;

import javax.swing.JButton;

/**
 * @author jangle E-mail: jangle@jangle.xyz
 * @version 2020年1月8日 上午11:35:59 类说明
 */
public class M {

	public static void main(String[] args) {
		Predicate<Integer> lambda = x -> x > 5;
		Predicate<Integer> lambda2 = (Integer x) -> x > 5;
		Predicate<Integer> lambda22 = (Integer x) -> {
			return x + 1 > 5;
		};
		BinaryOperator<Long> lambda3 = (x, y) -> x + y;
		BinaryOperator<Integer> lambda4 = (x, y) -> x + y;
		System.out.println(lambda.test(1));
		System.out.println(lambda2.test(2));
		System.out.println(lambda22.test(2));
		System.out.println(lambda3.add(1L, 2L));
		System.out.println(lambda4.add(1, 2));
		Runnable t = () -> {
			for (int i = 0; i < 10; i++) {
				System.out.println("123");
			}
		};
		t.run();
		new JButton().addActionListener(event -> System.out.println(event.getActionCommand()));
		
	}
}
