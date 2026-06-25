package 第五章;

import java.util.Scanner;

public class F_21 {

	public static void main(String[] args) {
		//
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the Loan Amount and the Number of Year ");
		System.out.print("Loan Amount: ");
		double Loan = input.nextDouble();
		System.out.print("Number of Year: ");
		double Year = input.nextDouble();

		String format = "%-15s%-15s%-15s\n";
		System.out.printf(format, "Interest Rate", "Monthly Payment", "Total Payment");
		for (int i = 1; i <= 25; i++) {
			double Rate = (4.875 + (0.125 * i)) / 1200;
			double Monthly = Loan * Rate / (1 - 1 / Math.pow(1 + Rate, Year * 12));
			double Total = Monthly * Year * 12;
			System.out.printf("%.3f%%     %.2f     %.2f", Rate * 1200, Monthly, Total);
			System.out.println();
		}
	}

}
