package 第七章;

import java.util.Scanner;

public class S_5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int[] number = new int[10];
		int count = 0;

		System.out.println("Enter the 10 numbers: ");
		for (int i = 0; i < 10; i++) {
			boolean bool = false;
			int numbers = input.nextInt();
			for (int n = 0; n < i; n++) {
				if (number[n] == numbers) {
					bool = true;
				}
			}
			if (!bool) {
				count++;
				number[count - 1] = numbers;
			}
		}
		System.out.println("The number of distinct numbers is " + count);
		System.out.print("The distinct numbers are: ");
		for (int x = 0; x < count; x++) {
			System.out.print(number[x] + " ");
		}
	}

}
