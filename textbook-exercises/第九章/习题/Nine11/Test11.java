package 第九章.习题.Nine11;

import java.util.*;

public class Test11 {

	public static void main(String[] args) {
		// 用户输入
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a b c d e f");
		int a = input.nextInt();
		int b = input.nextInt();
		int c = input.nextInt();
		int d = input.nextInt();
		int e = input.nextInt();
		int f = input.nextInt();

		// 创建对象+调用方法
		LinearEquation qe = new LinearEquation(a, b, c, d, e, f);

		// 获取判别式结果
		boolean judge = qe.isSolvable();

		if (judge)
			System.out.printf("x = %.3f  y = %.3f", qe.getX(), qe.getY());
		else
			System.out.print("The equation has no solution");

	}

}
