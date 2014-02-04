package home.string;

/**
 * @author smruti
 * 
 */
public class TestString {

	public static void main(String[] args) {

		String sl1 = "hi";
		String sl2 = "hi";
		System.out.println(sl1 == sl2);

		String s1 = new String("hi");
		String s2 = new String("hi");
		System.out.println(s1 == s2);

		System.out.println(sl1.equals(sl2));
		System.out.println(sl1.equals(s2));
		System.out.println(s1.equals(s2));

	}

}
