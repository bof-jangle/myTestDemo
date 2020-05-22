package xyz.jangle.genericity.test;

/**
 * @author jangle E-mail: jangle@jangle.xyz
 * @version 2020年5月22日 下午3:27:19 类说明
 */
public class Pair<T> {

	private T first;

	private T second;

	public Pair() {

	}

	public Pair(T f, T s) {
		this.first = f;
		this.second = s;
	}

	public void aaa() {
		System.out.println(""+first+second);
	}

	public T getFirst() {
		return first;
	}

	public void setFirst(T first) {
		this.first = first;
	}

	public T getSecond() {
		return second;
	}

	public void setSecond(T second) {
		this.second = second;
	}

}
