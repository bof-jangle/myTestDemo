package xyz.jangle.annotation.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import xyz.jangle.genericity.test.G;

public class AnnotationM {
	
	private final static String name = "testMethodMyTestAnnotationName";

	public static void main(String[] args) {
		
		AnnotationM create = G.create(AnnotationM::new);
		try {
//			Class<?> mClass = Class.forName("xyz.jangle.annotation.test.AnnotationM");
//			Class<?> mClass = AnnotationM.class;
			Class<?> mClass = create.getClass();
			Method method = mClass.getMethod("testMethod");
			MyTestAnnotation[] a = method.getAnnotationsByType(MyTestAnnotation.class);
			Annotation[] b = method.getAnnotations();
			System.out.println(a[0].name()+":"+a[0].ppp());
			if(a[0].ppp().equals("1")) {
				System.out.println("存在MyTestAnnotation注解，且ppp值为1的方法");
			}
			for(Annotation bb:b) {
				if(bb instanceof MyTestAnnotation) {
					MyTestAnnotation c =(MyTestAnnotation)bb;
					if(c.name().equals(name)) {
						System.out.println("存在MyTestAnnotation注解，且name值为"+name+"的方法");
					}
				}
			}
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}

	}
	
	@MyTestAnnotation(name = name)
	public void testMethod() {
		
		System.out.println("123321");
		
	}

}
