abstract class Person {
	String name;
	
	abstract void speak();
}

class Student extends Person {
	float gpa;
	
	@Override
	void speak() {
		System.out.printf("Name: %s, GPA: %.1f\n", name, gpa);
	}
}


public class PairProgram1 {	
	public static void main(String[] args) {
		String str = "The cat-jumped_over,the.log;to:eat";		
		String[] tokens = str.split("([ .,;:_-])");
		
		System.out.println(java.util.Arrays.toString(tokens));
		
		Student pete = new Student();
		pete.name = "Pete";
		pete.gpa = 2.7f;
		pete.speak();
	}
}