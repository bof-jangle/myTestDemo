package xyz.jangle.thread.test.n8_xiii.asyncstream;

import java.util.Date;

/**
 * 元素类
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年10月16日 下午3:23:03
 * 
 */
public class News {

	private String title, content;

	private Date date;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "News [title=" + title + ", content=" + content + ", date=" + date + "]";
	}

}
