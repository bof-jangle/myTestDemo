package xyz.jangle.thread.test.n4_9.futuretask;

import java.util.concurrent.FutureTask;

/**
 * 	用于包装可执行的任务，并保存执行结果的类
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月24日 下午7:33:10
 * 
 */
public class ResultTask extends FutureTask<String> {
	
	private final String name;

	public ResultTask(ExecutableTask callable) {
		super(callable);
		this.name = callable.getName();
	}

	@Override
	protected void done() {
		if(isCancelled()) {
			System.out.println(this.name+ "被取消");
		}else {
			System.out.println(this.name+ "正常完成（非取消）");
		}
	}

}
