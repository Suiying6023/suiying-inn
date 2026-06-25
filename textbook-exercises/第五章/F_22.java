package 第五章;

import java.util.Scanner;

public class F_22 {

	public static void main(String[] args) {
		//
		Scanner input = new Scanner(System.in);
		System.out.print("Loan Amount: ");
		double Loan = input.nextInt();
		System.out.print("Number of Years: ");
		int Years = input.nextInt();
		System.out.print("Annual Interest Rate: ");
		double Rate = input.nextDouble();

		String format = "%-15s%-15s%-15s%-15s\n";
		System.out.printf(format, "Payment#", "Interest", "Principal", "Balance");

	}

}
