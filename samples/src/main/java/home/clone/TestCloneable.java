package home.clone;

public class TestCloneable {
	public static void main(String[] args) throws CloneNotSupportedException {
		Super sup = new Super(6);
		System.out.println(((Super) sup.clone()).i);
		Sub sub = new Sub(5);
		System.out.println(((Sub) sub.clone()).i);
	}
}

class Super implements Cloneable {
	int i;

	public Super(int i) {
		this.i = i;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
		// return new Super(++i);
	}
}

class Sub extends Super {

	public Sub(int i) {
		super(i);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
