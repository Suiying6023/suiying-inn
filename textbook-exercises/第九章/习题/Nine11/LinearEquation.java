package 第九章.习题.Nine11;

public class LinearEquation {
	private double a, b, c, d, e, f;

	// 构造方法
	public LinearEquation(int a, int b, int c, int d, int e, int f) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.e = e;
		this.f = f;
	}

	// getter方法
	public double getA() {
		return a;
	}

	public double getB() {
		return b;
	}

	public double getC() {
		return c;
	}

	public double getD() {
		return d;
	}

	public double getE() {
		return e;
	}

	public double getF() {
		return f;
	}

	public boolean isSolvable() {
		return !(a * d - b * c == 0);
	}

	public double getX() {
		return ((e * d - b * f) / (a * d - b * c));
	}

	public double getY() {
		return ((a * f - e * c) / (a * d - b * c));
	}
}
