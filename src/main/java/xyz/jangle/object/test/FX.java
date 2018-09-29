package xyz.jangle.object.test;

/**
 * FX泛型的缩写
 * 
 * @author huhj
 * @email jangle@jangle.xyz
 * @time 2018年9月19日 上午10:04:36
 */
public class FX<T> {

	private int id;
	private String name;
	private T t;

	public FX() {
		super();
	}

	public FX(int id) {
		super();
		this.id = id;
	}

	public FX(int id, T t) {
		super();
		this.id = id;
		this.t = t;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

}
