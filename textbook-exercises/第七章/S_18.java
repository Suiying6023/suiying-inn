package 第七章;

import java.util.Scanner;

public class S_18 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		double[] number = new double[10];
		double temp = 0;
		boolean flag = true;
		System.out.print("Please enter the ten numbers: ");
		for (int x = 0; x < 10; x++) {
			number[x] = input.nextDouble();
		}

		for (int y = 0; y < 9; y++) {
			for (int z = 0; z < 9 - y; z++) {
				if (number[z + 1] > number[z]) {
					temp = number[z + 1];
					number[z + 1] = number[z];
					number[z] = temp;
					flag = true;
				}

			}
			if (flag = false)
				break;
		}
		for (int a = 0; a <= 9; a++) {
			System.out.print(number[a] + "\n");

		}

	}
}
