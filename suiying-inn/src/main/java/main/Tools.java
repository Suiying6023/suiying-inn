package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Objects;

import model.Constants;
import model.Room;
import model.User;

/**
 * {@code Tools} 类是一个用于实现一些工具方法的类。
 * <p>
 * 该类提供了以下方法，用于初始化程序，实现登录，注册，退出和更改密码的功能：
 * <ul>
 * <li>{@link #start() }：初始化程序，读取房间列表和用户文件，并创建默认用户。</li>
 * <li>{@link #login() }：实现登录功能，根据用户输入的用户名和密码，判断是否登录成功，并进入相应的菜单界面。</li>
 * <li>{@link #register() }：实现注册功能，根据用户输入的用户名和密码，判断是否注册成功，并将新用户添加到用户文件中。</li>
 * <li>{@link #logout() }：实现退出功能，将当前用户设置为null，并返回到起始菜单。</li>
 * <li>{@link #changePassword() }：实现更改密码功能，根据用户输入的旧密码和新密码，判断是否更改成功，并更新用户文件中的密码信息。</li>
 * </ul>
 * 
 * @author Suiying
 * @see model.Constants
 * @see model.Room
 * @see model.User
 */

public class Tools {
	// 程序初始化
	static void start() throws Exception {
		// 初始化房间列表
		Constants.rooms = Room.getRooms();
		// 读取帐号文件
		File file = new File("users.txt"); // 定义一个File对象，用于报错输出路径
		try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
			// 创建一个字符串变量line，用来存储每一行的数据，并初始化为null
			String line = null;
			// 使用while循环，当line不为空时，继续读取下一行
			while ((line = br.readLine()) != null) {
				// 使用split方法，以"
				// "为分隔符，将line分割成三个部分，并存储到一个字符串数组中
				String[] parts = line.split(" ");
				// 分别从字符串数组中获取用户名，密码和是否是管理员的信息，并转换成相应的类型
				String username = parts[0];
				String password = parts[1];
				boolean isAdmin = parts[2].equals("1");
				// 创建一个User对象，使用上面获取的信息作为参数
				User user = new User(username, password, isAdmin);
				// 将User对象添加到users中，以用户名为键，用户对象为值
				Constants.users.put(username, user);
			}
		} catch (Exception e) {
			// 处理文件不存在的异常
			System.out.println("帐号文件出错，启用内置默认账户");
			System.out.println("文件路径：" + file.getAbsolutePath());
		} finally {
			User newUser = new User("admin", "admin", true);
			Constants.users.put("admin", newUser); // 默认管理员账户
			User guestUser = new User("guest", "guest", false);
			Constants.users.put("guest", guestUser); // 默认来宾账户
			try (// 创建一个BufferedWriter对象，用于向users.txt文件中写入数据，并遍历用户映射的值集合，将每个用户对象的信息写入到文件中，每行一个
					BufferedWriter bw = new BufferedWriter(new FileWriter("users.txt"))) {
				for (User user : Constants.users.values()) {
					bw.write(user.getUsername() + " " + user.getPassword() + " " + (user.isAdmin() ? "1" : "0") + "\n");
				}
			}
		}
		Constants.currentUser = null; // 初始化登录用户为null
	}

	// login方法，实现登录功能，返回一个布尔值表示是否登录成功
	public static boolean login() throws Exception {
		// 提示用户输入用户名和密码，并获取用户输入的字符串
		System.out.println();
		System.out.print("请输入用户名：");
		String username = Constants.sc.nextLine();
		System.out.print("请输入密码：");
		String password = Constants.sc.nextLine();
		// 判断用户输入的用户名是否存在于用户映射中，如果不存在则提示错误信息并返回false
		if (!Constants.users.containsKey(username)) {
			System.out.println(String.format("用户%s不存在，请重新登录或注册", username));
			System.out.println();
			return false;
		}
		// 如果用户名存在，则获取对应的用户对象，并判断用户输入的密码是否与用户对象的密码相同，如果不同则提示错误信息并返回false
		User user = Constants.users.get(username);
		if (!Objects.equals(password, user.getPassword())) {
			System.out.println(String.format("密码错误，请重新登录"));
			return false;
		}
		Constants.currentUser = user; // 更改当前用户为登录用户
		System.out.println(String.format("用户%s,登录成功", Constants.currentUser.getUsername()));
		System.out.println();
		if (Constants.currentUser.isAdmin()) {
			// 如果当前用户是管理员，则调用adminMenu方法进入管理员界面
			System.out.println("****************尊敬的管理员用户，欢迎进入岁影客栈管理系统****************");
			AdminMenu.adminMenu();
		} else {
			// 如果当前用户不是管理员，则调用userMenu方法进入普通用户界面
			System.out.printf("******************欢迎进入岁影客栈预约系统******************");
			System.out.println();
			System.out.println("**************本店最新活动，连住3天及3天以上打8折**************");
			UserMenu.userMenu();
		}
		return true;
	}

	// 更改密码功能
	public static void changePassword() throws Exception {
		// 提示用户输入旧密码，并获取用户输入的字符串
		System.out.println();
		System.out.print("请输入旧密码：");
		String oldPassword = Constants.sc.nextLine();
		// 判断用户输入的旧密码是否与当前用户对象的密码属性相同，如果不同则提示错误信息并返回
		if (!oldPassword.equals(Constants.currentUser.getPassword())) {
			System.out.println("旧密码错误，请检查");
			return;
		}
		// 如果相同，则提示用户输入新密码，并获取用户输入的字符串
		System.out.print("请输入新密码：");
		String newPassword = Constants.sc.nextLine();
		// 修改当前用户对象的密码属性为新密码，并更新用户映射中的值
		Constants.currentUser.setPassword(newPassword);
		Constants.users.put(Constants.currentUser.getUsername(), Constants.currentUser);
		// 创建一个BufferedWriter对象，用于向users.txt文件中写入数据，并遍历用户映射的值集合，将每个用户对象的信息写入到文件中，每行一个
		BufferedWriter bw = new BufferedWriter(new FileWriter("users.txt"));
		for (User user : Constants.users.values()) {
			bw.write(user.getUsername() + " " + user.getPassword() + " " + (user.isAdmin() ? "1" : "0") + "\n");
		}
		// 关闭BufferedWriter对象
		bw.close();
		// 提示修改成功
		System.out.println("修改成功");
	}

	// logout方法，实现退出当前用户功能，将当前用户赋值为null，并提示退出成功
	static void logout() throws Exception {
		Constants.currentUser = null;
		System.out.println("退出帐号成功");
		System.out.println();
		// 返回起始界面
		MainSystem.startMenu();
	}

	// register方法，实现注册功能，返回一个布尔值表示是否注册成功
	public static boolean register() throws Exception {
		try {
			System.out.print("请输入用户名：");
			String username = Constants.sc.nextLine();
			// 判断用户输入的用户名是否已经存在于用户映射中，如果存在则提示错误信息并返回false
			if (Constants.users.containsKey(username)) {
				System.out.println("用户名已存在，请重新输入或登录");
				System.out.println();
				return false;
			}
			// 如果用户名不存在，则创建一个新的用户对象，isAdmin属性为false，表示普通用户，并将该对象添加到用户映射中
			System.out.print("请输入密码：");
			String password = Constants.sc.nextLine();
			// 并将该对象的信息写入到users.txt文件中，格式为：用户名 密码
			// 是否是管理员（0表示否，1表示是）
			User user = new User(username, password, false);
			Constants.users.put(username, user);
			BufferedWriter bw = new BufferedWriter(new FileWriter("users.txt", true));
			bw.write(username + " " + password + " 0\n");
			bw.flush();
			bw.close();
		} catch (Exception e) {
			System.out.println("请输入有效的用户名");
			register();
		}
		// 提示注册成功并返回true
		System.out.println("注册成功，请返回登录");
		return true;
	}
}
