package xyz.jangle.thread.test.n3_4.cyclicbarrier;
/**
 * 	用于存放每行找到对应number的数量结果（每个搜索线程检索完毕后，将结果写入该对象）
 * 	（辅助类）
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月10日 下午12:44:37
 * 
 */
public class Results {
	
	private final int data[];

	public Results(int size) {
		super();
		this.data = new int[size];
	}
	
	/**
	 *  设置对应行存在对应number的数量
	 * 
	 * @param position	行号
	 * @param value		数量
	 */
	public void setData(int position,int value) {
		data[position] = value;
	}
	
	/**
	 * 返回结果
	 * 
	 * @return
	 */
	public int[] getData() {
		return data;
	}
	

}
