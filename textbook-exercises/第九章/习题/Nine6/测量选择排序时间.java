package 第九章.习题.Nine6;

import java.util.Random;

public class 测量选择排序时间 {

	public static void selectionSort(int[] arr) {
		int n = arr.length;

		// One by one move boundary of unsorted subarray
		for (int i = 0; i < n - 1; i++) {
			// Find the minimum element in unsorted array
			int min_idx = i;
			for (int j = i + 1; j < n; j++) {
				if (arr[j] < arr[min_idx]) {
					min_idx = j;
				}
			}

			// Swap the found minimum element with the first element
			int temp = arr[min_idx];
			arr[min_idx] = arr[i];
			arr[i] = temp;
		}
	}

	public static void main(String[] args) {
		// Generate an array of 100000 random numbers
		int[] arr = new int[100000];
		Random rand = new Random();

		Stopwatch Stopwatch = new Stopwatch();

		for (int i = 0; i < arr.length; i++) {
			arr[i] = rand.nextInt(100000);
		}

		// Sort the array using selection sort
		Stopwatch.start();
		selectionSort(arr);
		Stopwatch.stop();

		// Print the sorted array
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		long elapsedTime = Stopwatch.getElapsedTime();
		System.out.println();
		System.out.println("Elapsed Time: " + elapsedTime + " milliseconds.");
	}
}
