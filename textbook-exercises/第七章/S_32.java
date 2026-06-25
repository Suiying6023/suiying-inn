package 第七章;

import java.util.Scanner;

public class S_32 {

	public static void main(String[] args) {
//			输入数组的长度
		System.out.print("please enter list size：");
		Scanner input = new Scanner(System.in);
		int number = input.nextInt();
		int[] array = new int[number];
		System.out.print("Enter list content:");

		for (int i = 0; i < array.length; i++)
			array[i] = input.nextInt();
		System.out.println();

		int pivot = partition(array);
		System.out.print("After the partition,the list is ");
		for (int e : array) {
			System.out.print(e + " ");
		}

	}

	public static int partition(int[] array) {
		int num = array[0];
//			定位中心点的下标值
		int flag = 0;
//			判断中心点是否放置中心
		int f = 0;
//			对元素进行比较
		for (int i = 1; i <= array.length; i++) {
//				第一个元素最小或者已找到pivot
			if (f == 1)
				break;
//				第一个元素最大
			if (i == array.length) {
				int s = array[i - 1];
				array[i - 1] = num;
				num = s;
				array[flag] = s;
				flag = i - 1;
				break;
			}

			int low = array[i];
			if (low > num) {
				for (int j = array.length - 1; j >= 0; j--) {
					if (j == 0) {
						f = 1;
						break;
					}
					int high = array[j];
					if (j < i) {
						int s = array[j];
						array[j] = num;
						num = s;
						array[flag] = s;
						flag = j;
						f = 1;
						break;
					}
					if (high <= num) {
						int s = array[j];
						array[j] = array[i];
						array[i] = s;
						break;
					}
				}
			}
		}
		return num;
	}

}
