package 第五章;

public class F_7 {

	public static void main(String[] args) {
		//

		double a = 10000, rate = 0.05;
		double ten = a * Math.pow(1 + rate, 10);
		System.out.println("十年后的学费为" + ten);
		double four = ten;
		for (int i = 1; i < 4; i++)
			four += ten * Math.pow(1 + rate, i);
		System.out.println("十年后4年内的总学费为" + four);
	}

}
