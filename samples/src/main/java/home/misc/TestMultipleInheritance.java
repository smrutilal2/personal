package home.misc;

/**
 * @author smruti
 * 
 */
public class TestMultipleInheritance {

}

class Impl implements Interface1, Interface2 {

	@Override
	public void hello() {

	}

}

interface Interface1 {
	void hello();
}

interface Interface2 {
	void hello();
}
