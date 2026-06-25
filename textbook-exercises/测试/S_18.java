package 测试;

import java.util.Scanner;

public class S_18 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		double[] number = new double[10];
		double temp = 0;
		boolean flag = true;
		System.out.print("Please enter the ten numbers: ");
		for (int i = 0; i < 10; i++) {
			number[i] = input.nextDouble();
		}

		for (int j = 0; j < 9; j++) {
			for (int k = 0; k < 9 - j; k++) {
				if (number[k + 1] > number[k]) {
					temp = number[k + 1];
					number[k + 1] = number[k];
					number[k] = temp;
					flag = true;
				}
			}
			if (flag == false) {
				break;
			} else {
				flag = false;
			}
		}

		for (int a = 0; a <= 9; a++) {
			System.out.print(number[a] + "\n");
		}

	}

}