package 第九章.例题;

//9.7
public class TestCirrcleWithStaticMembers {

	public static void main(String[] args) {
		System.out.println("Before creating objects");
		System.out.println("The number of Circle objects is " + fCircle.numberOfObjects);

		// Create c1
		fCircle c1 = new fCircle(); // Use the circle class in Listing 9.6

		// Display c1 BEFORE c2 is created
		System.out.println("\nAfter creating c1");
		System.out.println("c1: radius (" + c1.radius + ") and number of Circle objects (" + c1.numberOfObjects + ")");

		// Create c2
		fCircle c2 = new fCircle(5);

		// Modify c1
		c1.radius = 9;

		// Display c1 and c2 AFTER c2 was created
		System.out.println("\nAfter creating c2 and modifying c1");
		System.out.println("c1: radius(" + c1.radius + ") and number of Circle objects (" + c1.numberOfObjects + ")");
		System.out.println("c2: radius(" + c2.radius + ") and number of Circle objects (" + c2.numberOfObjects + ")");

	}

}
