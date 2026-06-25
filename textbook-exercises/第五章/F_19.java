package 第五章;

import java.util.Scanner;

public class F_19 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the numbers of lines: ");
		int lines = input.nextInt();
		// 输入行数

		for (int a = 1; a <= lines; a++) {
			for (int b = 1; b <= (lines - a); b++)
				System.out.print("    ");

			// 输出空格
			for (int c = 1; c <= a; c++) {
				int e = (int) Math.pow(2, c - 1);
				System.out.printf("%4d", e);
			}

			if (a > 1) {
				for (int d = a - 1; d > 0; d--) {
					int f = (int) Math.pow(2, d - 1);
					System.out.printf("%4d", f);
				}
			}
			System.out.println();
		}

	}

}
