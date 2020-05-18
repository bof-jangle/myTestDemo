package xyz.jangle.java8.test;
/** 
* @author jangle E-mail: jangle@jangle.xyz
* @version 2020年1月8日 下午2:59:41 
* 类说明 
*/
public interface Check {
	
	boolean check(Predicate<Integer> predicate);
	
	boolean check(IntPred predicate);

}
