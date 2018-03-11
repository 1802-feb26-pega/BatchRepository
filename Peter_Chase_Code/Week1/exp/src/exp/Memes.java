package exp;

public class Memes {
	public static void main(String[] args) {
		String pword1 = "password123";
		String pword2 = new String("password123");
		String pword3 = "pword";
		
		System.out.println(pword1.hashCode() == pword2.hashCode());
		System.out.println(pword1.hashCode() == pword3.hashCode());
	}
}

