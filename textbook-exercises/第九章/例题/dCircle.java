package 第九章.例题;

//9.8
public class dCircle {
	/** The radius of the circle */
	private double radius = 1;

	/** The number of objects created */
	private static int numberOfObjects = 0;

	/** Construct a circle with radius 1 */
	public dCircle() {
		numberOfObjects++;
	}

	/** Construct a circle with a specified radius */
	public dCircle(double newRadius) {
		radius = newRadius;
		numberOfObjects++;
	}

	/** Return radius */
	public double getRadius() {
		return radius;
	}

	/** Set a new radius */
	public void setRadius(double newRadius) {
		radius = (newRadius >= 0) ? newRadius : 0;
	}

	/** Return the area of this circle */
	public double getArea() {
		return radius * radius * Math.PI;
	}

}
