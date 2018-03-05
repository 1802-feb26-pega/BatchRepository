package exp;

public class Memes {
	
	static Object m1() {
		return new Object();
	}
	
	static void m2() {
		String s = (String) m1();
	}
	
	public static void main(String[] args) {
		m1();
		m2();
	}
}

