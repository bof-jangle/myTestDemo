package xyz.jangle.thread.test.n8_x.prioritytransfer;

/**
 *  元素
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年10月10日 下午5:20:14
 * 
 */
public class Event implements Comparable<Event> {

	private final String thread;

	private final int priority;

	public Event(String thread, int priority) {
		super();
		this.thread = thread;
		this.priority = priority;
	}

	public String getThread() {
		return thread;
	}

	public int getPriority() {
		return priority;
	}

	@Override
	public int compareTo(Event o) {
		return Integer.compare(o.getPriority(), this.priority);
	}

}
