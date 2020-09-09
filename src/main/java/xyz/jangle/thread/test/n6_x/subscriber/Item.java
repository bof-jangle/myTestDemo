package xyz.jangle.thread.test.n6_x.subscriber;

/**
 * 元素类（含标题和内容）
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月9日 下午4:20:05
 * 
 */
public class Item {

	private String title, content;

	public Item() {
		super();
	}

	public Item(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
