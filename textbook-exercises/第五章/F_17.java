package 第五章;

import java.util.Scanner;

public class F_17 {
	public static void main(String[] args) {
		// 输入行数
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the number of lines: ");
		int lines = input.nextInt();

		// 输出
		for (int n = 1; n <= lines; n++) { // 共n行
			// 输出空格部分
			for (int a = 1; a <= (lines - n); a++)
				System.out.print("    ");
			for (int b = n; b > 0; b--)
				System.out.printf("%4d", b);
			if (n > 1) {
				for (int c = 2; c <= n; c++)
					System.out.printf("%4d", c);
			}
			System.out.println();
		}

	}

}
