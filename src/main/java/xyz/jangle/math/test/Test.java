package xyz.jangle.math.test;
/** 
* @author jangle E-mail: jangle@jangle.xyz
* @version 2019年12月25日 上午11:15:01 
* 类说明 
*/
public class Test {

	public static void main(String[] args) {
		double z =0;
		long i,j=0;
		boolean breakv = false;
		for(i=1;i<100000;i++) {
			for(j=1;j<100000;j++) {
				z = Math.sqrt(i*i+j*j);
				System.out.println(z);
				int indexOf = String.valueOf(z).indexOf(".");
				if(z%Integer.parseInt(String.valueOf(z).substring(0, indexOf)) == 0) {
					breakv = true;
					break;
				}
			}
			if(breakv) {
				break;
			}
		}
		if(breakv) {
			System.out.println("z："+z);
			System.out.println("i："+i);
			System.out.println("j："+j);
		}else {
			System.out.println("为找到这个数字");
		}

	}

}
