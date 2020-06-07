package xyz.jangle.annotation.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author huhj
 * @email jangle@jangle.xyz
 * @time 2018年7月16日 下午3:58:16
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface MyTestAnnotation {
	
	public String name();
	
	public String ppp() default "1";

}
