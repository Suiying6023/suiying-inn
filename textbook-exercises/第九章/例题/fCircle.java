package 第九章.例题;

//9.6
public class fCircle {
	/** The radius of the circle */
	double radius;

	/** The number of objects created */
	static int numberOfObjects = 0;

	/** Construct a circle with radius 1 */
	fCircle() {
		radius = 1;
		numberOfObjects++;
	}

	/** Construct a circle with a specified radius */
	fCircle(double newRadius) {
		radius = newRadius;
		numberOfObjects++;
	}

	/** Return numberOfObeject */
	static int getNumberOfObjects() {
		return numberOfObjects;
	}

	/** Return the area of this circle */
	double getArea() {
		return radius * radius * Math.PI;
	}
}
