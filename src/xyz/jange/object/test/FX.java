package xyz.jange.object.test;
/**
 * FX泛型的缩写
 * @author huhj
 * @email  jangle@jangle.xyz
 * @time   2018年9月19日 上午10:04:36
 */
public class FX<T> {
	
	private int id;
	private String name;
	
	
	public FX() {
		super();
	}
	public FX(int id) {
		super();
		this.id = id;
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
	
	

}
