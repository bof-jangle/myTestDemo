package xyz.jangle.genericity.test;

/**
 * 泛型方法与继承。
 * 
 * @author jangle
 *
 */
public class GenericityMethodTest {

	public static void main(String[] args) {

		GenericityMethodTest test = new GenericityMethodTest();
		Da da = test.new Da();
		da.setS("123321");
		System.out.println(da.getS());
	}

	class Pair<T> {

		private T f;
		private T s;

		public T getF() {
			return f;
		}

		public T getS() {
			System.out.println("Pair.getS");
			return s;
		}

		public void setF(T f) {
			this.f = f;
		}

		public void setS(T s) {
			System.out.println("Pair.setS");
			this.s = s;
		}
	}

	class Da extends GenericityMethodTest.Pair<String> {
		public void setS(String str) {
			System.out.println("Da.setS");
			super.setS(str);
		}

		public String getS() {
			System.out.println("Da.getS");
			return super.getS();
		}
	}

}
