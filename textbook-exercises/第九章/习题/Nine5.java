package 第九章.习题;

import java.util.*;

public class Nine5 {
	public static void main(String[] args) {
		// 创建GregorianCalendar对象
		GregorianCalendar calendar = new GregorianCalendar();

		// 获取年、月、日属性
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1; // 注意，月份从0开始，需要加1
		int day = calendar.get(Calendar.DAY_OF_MONTH);

		// 输出日期
		System.out.println(year + "年" + month + "月" + day + "日");

		calendar.setTimeInMillis(1234567898765L);
		// 获取年、月、日属性
		int year2 = calendar.get(Calendar.YEAR);
		int month2 = calendar.get(Calendar.MONTH) + 1; // 注意，月份从0开始，需要加1
		int day2 = calendar.get(Calendar.DAY_OF_MONTH);

		// 输出日期
		System.out.println(year2 + "年" + month2 + "月" + day2 + "日");
	}
}
