package main;

import model.Constants;

/**
 * {@code MainSystem} 类是一个用于实现主系统功能的类。
 * <p>
 * 该类提供了以下方法，用于运行初始化程序，显示欢迎界面，显示起始菜单，登录，注册和退出程序：
 * <ul>
 * <li>{@link #main(String[])}：主方法，运行初始化程序，显示欢迎界面，并进入起始菜单。</li>
 * <li>{@link #startMenu() }：显示起始菜单，并根据用户输入的选择执行相应的操作，包括登录，注册和退出。退出显示欢送界面。</li>
 * <li>{@link #exitProgram()}：退出程序，关闭Scanner，并结束程序。</li>
 * </ul>
 *
 * @author Suiying
 * @see model.Constants
 */

public class MainSystem {
	public static void main(String[] args) throws Exception {
		Tools.start(); // 运行初始化程序
		System.out.println();
		String str = "********************************************************************************\n"
				+ "                                                                              \n"
				+ "    ██╗    ██╗███████╗██╗     ██╗      ██████╗ ██████╗ ███╗   ███╗███████╗    \n"
				+ "    ██║    ██║██╔════╝██║     ██║     ██╔════╝██╔═══██╗████╗ ████║██╔════╝    \n"
				+ "    ██║ █╗ ██║█████╗  ██║     ██║     ██║     ██║   ██║██╔████╔██║█████╗      \n"
				+ "    ██║███╗██║██╔══╝  ██║     ██║     ██║     ██║   ██║██║╚██╔╝██║██╔══╝      \n"
				+ "    ╚███╔███╔╝███████╗███████╗███████╗╚██████╗╚██████╔╝██║ ╚═╝ ██║███████╗    \n"
				+ "     ╚══╝╚══╝ ╚══════╝╚══════╝╚══════╝ ╚═════╝ ╚═════╝ ╚═╝     ╚═╝╚══════╝    \n"
				+ "                                                                              \n"
				+ "                                 岁影客栈欢迎您的到来                                 \n"
				+ "                                                       系统版本：V1.0             \n "
				+ "******************************************************************************\n";
		System.out.println(str); // 欢迎界面
		startMenu(); // 进入起始菜单
	}

	// 起始菜单
	static void startMenu() throws Exception {

		while (true) {
			System.out.println();
			System.out.println("请选择你要进行的操作：");
			System.out.println();
			System.out.println("1.登录");
			System.out.println("2.注册");
			System.out.println("3.退出");
			System.out.println();
			System.out.print("***请输入你的选择（1-3）：");
			int choice = 0;
			try {
				String input = Constants.sc.nextLine();
				choice = Integer.parseInt(input); // 转换成整数
			} catch (Exception e) {
				System.out.println("输入错误，请重新选择");
				continue;
			}
			switch (choice) {
			case 1:
				// 调用登录方法
				if (Tools.login()) {
					break;
				} else {
					// 登录失败，返回起始菜单
					continue;
				}
			case 2:
				// 调用注册方法
				if (Tools.register()) {
					System.out.println();
					// 注册成功，进入登录界面
					Tools.login();
				} else {
					// 注册失败，返回起始菜单
					continue;
				}
			case 3:
				// 展示欢送界面，结束程序
				String str = "******************************************************************************\n"
						+ "                                                                              \n"
						+ "          ██████╗ ██╗   ██╗███████╗     ██████╗ ██╗   ██╗███████╗\n"
						+ "          ██╔══██╗╚██╗ ██╔╝██╔════╝     ██╔══██╗╚██╗ ██╔╝██╔════╝\n"
						+ "          ██████╔╝ ╚████╔╝ █████╗       ██████╔╝ ╚████╔╝ █████╗  \n"
						+ "          ██╔══██╗  ╚██╔╝  ██╔══╝       ██╔══██╗  ╚██╔╝  ██╔══╝  \n"
						+ "          ██████╔╝   ██║   ███████╗     ██████╔╝   ██║   ███████╗\n"
						+ "          ╚═════╝    ╚═╝   ╚══════╝     ╚═════╝    ╚═╝   ╚══════╝\n"
						+ "                                                                              \n"
						+ "                            岁影客栈欢迎您的再次光临！                                 \n"
						+ "                                                                              \n"
						+ "******************************************************************************\n";
				System.out.println(str);
				MainSystem.exitProgram();
				return;
			default:
				// 如果用户输入其他数字，则提示错误信息
				System.out.println("输入错误，请重新选择");
				System.out.println("--------------------");
				System.out.println();
				continue;
			}
		}
	}

	// 退出程序
	public static void exitProgram() {
		// 关闭Scanner
		Constants.sc.close();
		// 结束程序
		System.exit(0);
	}
}
