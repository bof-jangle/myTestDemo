package xyz.jangle.thread.test.n2_7.stampedlock;

/**
 * 	StampedLock 的DEMO所要使用的共享数据类
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月2日 下午1:55:09
 * 
 */
public class Position {

	private Integer x, y;

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Position(Integer x, Integer y) {
		super();
		this.x = x;
		this.y = y;
	}

}
