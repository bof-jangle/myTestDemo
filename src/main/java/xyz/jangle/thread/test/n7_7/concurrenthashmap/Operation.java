package xyz.jangle.thread.test.n7_7.concurrenthashmap;

import java.util.Date;

/**
 * 元素类
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月15日 下午5:33:37
 * 
 */
public class Operation {

	private String user, operation;
	private Date time;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
