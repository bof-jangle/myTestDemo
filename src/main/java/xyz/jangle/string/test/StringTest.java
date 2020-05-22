package xyz.jangle.string.test;
/**
 * @author huhj
 * @email  jangle@jangle.xyz
 * @time   2018年7月5日 下午3:24:33
 */
public class StringTest {

	public static void main(String[] args) {

		String s = new String("123");
		String ss = "123";
		String sss = "123";
		String ssss = new String("123");
		System.out.println("s==ss:"+(s==ss));		//false
		System.out.println("ss==sss:"+(ss==sss));	//true	指向了相同的内存空间。
		System.out.println("sss==ssss:"+(sss==ssss));	//false	
		System.out.println("ssss==s:"+(ssss==s));	//false 内容相同，但是是两个对象。
		
		
		sss = sss+"4";
		s=ssss;
		System.out.println(ssss==s);			//true
		System.out.println(ss==sss);			//false
		System.out.println(ss.equals(ssss));	//true
		String s1= "";
		String s2= new String();
		System.out.println(s1==s2);				//false
		
		/*
		 * ss 与sss相等，    当给String对象赋值时， 会先去查找内存中是否存在内容为"123"的对象，若存在则直接获取其引用，不存在则创建。
			所以ss与sss指向一个相同的引用。 new方法则是单独的创建一个对象,不管是否存在相同内容的对象。
		 */
		
		/*  String 的equals方法判断的逻辑是： 如果两个String是相同的对象返回true(对象相同内容必定相同)
		 * 	如果不是，则判断其内容，1、判断内容长度是否相同， 2、循环比较每个字符内容是否相同  （String 底层是用字符数组实现的）  1字符=2字节 16位
		 * 
		 */
	}

}
