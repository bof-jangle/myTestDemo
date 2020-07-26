package xyz.jangle.thread.test.n2_6.condition;

/**
 * 
 * 模拟文本文件
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年7月25日 下午10:04:30
 * 
 */
public class FileMock {

	// 正文（(行）
	private String[] content;
	// 行
	private int index;

	public FileMock(int size, int length) {
		content = new String[size];
		for (int i = 0; i < size; i++) {
			StringBuilder row = new StringBuilder(length);
			for (int j = 0; j < length; j++) {
				int rChar = (int) (Math.random() * 255);
				row.append(rChar);
			}
			content[i] = row.toString();
		}
		index = 0;
	}

	/**
	 * 是否还有行（即末尾了没）
	 * 
	 * @author jangle
	 * @time 2020年7月25日 下午10:09:52
	 * @return
	 */
	public boolean hasMoreLine() {
		return index < content.length;
	}

	/**
	 * 获取行
	 * 
	 * @author jangle
	 * @time 2020年7月25日 下午10:12:02
	 * @return
	 */
	public String getLine() {
		if (this.hasMoreLine()) {
			System.out.println("Mock:" + (content.length - index));
			return content[index++];
		}
		return null;
	}

}
