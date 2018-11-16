package xyz.jangle.reflect.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author jangle E-mail: jangle@jangle.xyz
 * @version 2018年11月16日 下午2:58:45 类说明
 */
public class ReflectTest {

	private String ra;

	public static void main(String[] args) {
		ReflectTest r = new ReflectTest();
		ClassA classa = r.new ClassA();
		classa.setA("AAA");
		System.out.println(classa.getA());

		try {
			Class<?> reflectClass = Class.forName("xyz.jangle.reflect.test.ReflectTest");
			Object instance = reflectClass.newInstance();
			Method[] declaredMethods = reflectClass.getDeclaredMethods();
			for (Method m : declaredMethods) {
				System.out.println(m.getName());
			}
			Method setRaMethod = reflectClass.getDeclaredMethod("setRa", String.class); // 获取方法 1、方法名称 2、3、4、 方法入参类型的类
			System.out.println(setRaMethod.getName());
			setRaMethod.invoke(instance, "invokeAAA");
			Method getRaMethod = reflectClass.getDeclaredMethod("getRa");
			Object out = getRaMethod.invoke(instance); // 反射执行getRa方法。获取返回值即方法执行的返回值。
			System.out.println(out);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getRa() {
		return ra;
	}

	public void setRa(String ra) {
		this.ra = ra;
	}

	class ClassA {
		private String a;
		private String b;

		public ClassA() {
			super();
		}

		public String getA() {
			return a;
		}

		public void setA(String a) {
			this.a = a;
		}

		public String getB() {
			return b;
		}

		public void setB(String b) {
			this.b = b;
		}

	}

}
