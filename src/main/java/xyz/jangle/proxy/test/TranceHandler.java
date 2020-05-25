package xyz.jangle.proxy.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/** 
* @author jangle E-mail: jangle@jangle.xyz
* @version 2020年5月23日 下午7:07:15 
* 类说明 
*/
public class TranceHandler implements InvocationHandler {
	
	private Object ob;
	
	public TranceHandler() {
	}

	public TranceHandler(Object o) {
		super();
		this.ob = o;
	}



	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("invoke ...");
		System.out.println("methodName"+method.getName());
		System.out.println("args"+args);
		return method.invoke(ob, args);
	}

}
