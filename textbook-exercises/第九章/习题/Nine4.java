package 第九章.习题;

import java.util.Random;

public class Nine4 {
	public static void main(String[] args) {
		// 指定种子创建Random对象
		long seed = 1000;
		Random random = new Random(seed);

		// 生成10个随机整数
		for (int i = 1; i <= 50; i++) {
			int randomNum = random.nextInt(100);
			System.out.println("Random number " + i + ": " + randomNum);
		}
	}
}
