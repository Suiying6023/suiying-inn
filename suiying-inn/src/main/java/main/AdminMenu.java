package main;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

import model.Constants;
import model.Order;
import model.Room;

/**
 * {@code AdminMenu} 类是一个用于实现管理员菜单功能的类。
 * <p>
 * 该类提供了以下方法，用于显示、增加、删除、修改订单和房间的信息：
 * <ul>
 * <li>{@link #adminMenu()}：显示管理员菜单，并根据用户输入的选择执行相应的操作。</li>
 * <li>{@link #showOrders()}：显示所有订单信息，遍历订单列表，并打印每个订单对象的信息。</li>
 * <li>{@link #showRooms()}：显示所有房间信息，遍历房间列表，并打印每个房间对象的信息。</li>
 * <li>{@link #addRoom()}：增加一个房间，提示用户输入房间的属性，并创建一个新的房间对象，并将其添加到房间列表中。</li>
 * <li>{@link #deleteRoom()}：删除一个房间，提示用户输入要删除的房号，并从房间列表中删除该房间。</li>
 * <li>{@link #updateRoom()}：修改一个房间，提示用户输入要修改的房号，并根据用户输入的选择修改相应的属性。</li>
 * </ul>
 *
 * @author Suiying
 * @see java.time.format.DateTimeFormatter
 * @see java.util.Objects
 * @see model.Constants
 * @see model.Order
 * @see model.Room
 */

public class AdminMenu {
	// 管理员菜单
	static void adminMenu() throws Exception {
		while (true) {
			System.out.println("");
			System.out.println("请选择你要进行的操作：");
			System.out.println("1.查看订单");
			System.out.println("2.查看所有房间信息");
			System.out.println("3.增加房间");
			System.out.println("4.删除房间");
			System.out.println("5.修改房间");
			System.out.println("6.更改密码");
			System.out.println("7.退出帐号");
			System.out.println();
			System.out.print("***请输入你的选择（1-7）：");
			// 获取用户输入的整数
			int choice = 0;
			try {
				String input = Constants.sc.nextLine(); // 读取用户输入的字符串
				choice = Integer.parseInt(input); // 转换成整数
			} catch (Exception e) {
				System.out.println("输入错误，请重新选择");
				continue;
			}
			// 根据用户输入的整数执行相应的操作
			switch (choice) {
			case 1:
				// 如果用户选择1，则调用showOrders方法显示所有订单信息，并提示重新选择
				showOrders();
				System.out.println();
				continue;
			case 2:
				// 如果用户选择2，则调用showRooms方法显示所有房间信息，并提示重新选择
				showRooms();
				System.out.println();
				continue;
			case 3:
				// 如果用户选择3，则调用addRoom方法增加一个房间，并提示重新选择
				addRoom();
				System.out.println();
				continue;
			case 4:
				// 如果用户选择4，则调用deleteRoom方法删除一个房间，并提示重新选
				deleteRoom();
				System.out.println();
				continue;
			case 5:
				// 如果用户选择5，则调用updateRoom方法修改一个房间，并提示重新选择
				updateRoom();
				System.out.println();
				continue;
			case 6:
				// 如果用户选择6，则调用changePassword方法更改当前用户的密码，并提示重新选择
				Tools.changePassword();
				System.out.println();
				continue;
			case 7:
				// 如果用户选择7，则调用logout方法退出当前用户，并返回到起始界面
				Tools.logout();
				System.out.println();
				break;
			default:
				// 如果用户输入其他数字，则提示错误信息，并重新输入
				System.out.println("输入错误，请重新选择");
				System.out.println();
				System.out.println("--------------------");
				continue;
			}
		}
	}

	// 显示所有订单信息
	private static void showOrders() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy M d");
		// 检查orders对象是否为null，如果是则抛出错误信息
		Objects.requireNonNull(Constants.orders, "无订单信息");
		// 判断订单列表是否为空，如果为空则提示没有订单信息
		if (Constants.orders.isEmpty()) {
			System.out.println("无订单信息");
		} else {
			// 如果不为空，则遍历订单列表，并打印每个订单对象的信息，包括用户名，房型，具体信息，入住日期，退房日期和价格
			System.out.println("以下是所有的订单信息：");
			for (Order order : Constants.orders) {
				// 创建一个StringBuilder对象，用来拼接字符串
				StringBuilder st = new StringBuilder();
				st.append(String.format("预约用户：%s%n", order.getUsername()));
				st.append(String.format("房号：%s%n", order.getRoom().getId()));
				st.append(String.format("房型：%s%n", order.getRoom().getType()));
				st.append(String.format("具体信息：%s%n", order.getRoom().getInfo()));
				st.append(String.format("入住日期：%s%n", dtf.format(order.getCheckInDate())));
				st.append(String.format("退房日期：%s%n", dtf.format(order.getCheckOutDate())));
				st.append(String.format("是否订购早餐：%s%n", (order.getBreakfast() ? "是" : "否")));
				st.append(String.format("订单总额：%s%n", (int) order.gettotalPrice()));
				st.append("--------------------");
				// 打印StringBuilder对象的内容
				System.out.println(st.toString());
			}
		}
	}

	// 显示所有房间功能
	private static void showRooms() {
		// 判断房间列表是否为空，如果为空则提示没有房间信息
		if (Constants.rooms.isEmpty()) {
			System.out.println("没有房间信息");
		} else {
			// 如果不为空，则打印表头，并使用格式化字符串来打印每个房间对象的信息，包括房号，房型，具体信息，数量，价格（无早餐）
			System.out.println("以下是所有的房间信息：");
			System.out.println();
			System.out.println("房号\t房型\t具体信息\t数量\t价格（不含早餐100元）\t已被预约数量（未来七天）");
			for (Room room : Constants.rooms.values()) {
				System.out.printf("%s\t%s\t%s\t%d\t%.0f\t\t\t", room.getId(), room.getType(), room.getInfo(),
						room.getNum(), room.getPrice());
				// 获取房间对象的被预约数量属性，并打印出每一天的次数
				int[] bookedDates = room.getBookedDates();
				for (int i = 0; i < 7; i++) {
					System.out.print(bookedDates[i] + "\t");
				}
				System.out.println();
			}
		}
	}

	// 增加房间功能
	private static void addRoom() {
		try {
			// 提示用户输入房号，并获取用户输入的字符串
			System.out.println();
			System.out.print("请输入房号：");
			String id = Constants.sc.nextLine();
			// 判断用户输入的房号是否已经存在于rooms中，如果存在则提示错误信息并返回
			if (Constants.rooms.containsKey(id)) {
				System.out.println("该房号已存在，请重新输入或选择修改房间");
				return;
			}
			// 如果房号不存在，则提示用户输入其他属性，并获取用户输入的字符串或整数
			System.out.print("请输入房型：");
			String type = Constants.sc.nextLine();
			System.out.print("请输入具体信息：");
			String info = Constants.sc.nextLine();
			System.out.print("请输入数量：");
			int num = Constants.sc.nextInt();
			// 判断用户输入的数量是否大于0，如果不是则提示错误信息并返回
			if (num <= 0) {
				System.out.println("数量必须大于0，请重新输入");
				Constants.sc.nextLine();
				return;
			}
			System.out.print("请输入价格（无早餐）：");
			int price = Constants.sc.nextInt();
			// 判断用户输入的价格是否大于0，如果不是则提示错误信息并返回
			if (price <= 0) {
				System.out.println("价格必须大于0，请重新输入");
				return;
			}

			// 创建一个新的房间对象，并将其添加到rooms中
			Room room = new Room(id, type, info, num, price);
			Constants.rooms.put(id, room);
			// 提示增加成功
			System.out.println("增加成功");
			Constants.sc.nextLine();
		} catch (Exception e) {
			// 捕获可能的输入类型不匹配的异常，并提示错误信息
			System.out.println("数量与价格需为数字格式，请重新添加");
			Constants.sc.nextLine();
			addRoom();
		}
	}

	// 删除房间功能
	private static void deleteRoom() {
		// 提示用户输入要删除的房间的id，并获取用户输入的字符串
		System.out.println();
		System.out.print("请输入要删除的房号：");
		String id = Constants.sc.nextLine();
		if (Constants.rooms.containsKey(id)) {
			// 如果map中包含该id，则提示删除数量
			System.out.print("请输入要删除的数量：");
			int num = 0;
			try {
				String input = Constants.sc.nextLine();
				num = Integer.parseInt(input); // 转换成整数
			} catch (Exception e) {
				System.out.println("数量错误，请返回重试");
				return;
			}
			// 获取该id对应的房间对象，并获取房间的数量
			Room room = Constants.rooms.get(id);
			int roomNum = room.getNum();
			// 如果要删除的数量大于房间的数量，则提示错误信息并返回
			if (num > roomNum) {
				System.out.println("删除数量不能大于房间数量");
				return;
			}
			if (num <= 0) {
				System.out.println("删除数量不能小于0");
				return;
			}
			// 如果要删除的数量等于房间的数量，则从map中删除该房间
			if (num == roomNum) {
				Constants.rooms.remove(id);
				System.out.println("删除成功");
				return;
			}
			// 如果要删除的数量小于房间的数量，则修改房间的数量，并提示删除成功
			room.setNum(roomNum - num);
			System.out.println("删除成功");
		} else {
			System.out.println("没有该房间");
		}
	}

	// 修改房间功能
	private static void updateRoom() {
		// 提示用户输入要修改的房间的id，并获取用户输入的字符串
		System.out.println();
		System.out.print("请输入要修改的房号：");
		String id = Constants.sc.nextLine();
		if (Constants.rooms.containsKey(id)) {
			Room room = Constants.rooms.get(id);
			int choice = 0;
			while (true) {
				// 提示用户输入要修改的属性，并获取用户输入的字符串
				System.out.println("请选择要修改的属性：");
				System.out.println("1.数量");
				System.out.println("2.价格（无早餐）");
				try {
					String input = Constants.sc.nextLine();
					choice = Integer.parseInt(input); // 转换成整数
					// 根据用户输入的选择，修改相应的属性，并提示修改成功
					switch (choice) {
					case 1:
						System.out.print("请输入新的数量：");
						int num = Constants.sc.nextInt();
						room.setNum(num);
						System.out.println("修改成功");
						Constants.sc.nextLine();
						return;
					case 2:
						System.out.print("请输入新的价格（无早餐）：");
						int price = Constants.sc.nextInt();
						if (price <= 0) {
							System.out.println("房间价格不能小于0");
							continue;
						}
						room.setPrice(price);
						System.out.println("修改成功");
						Constants.sc.nextLine();
						return;
					default:
						System.out.println("输入错误，请重新输入");
						break;
					}
				} catch (Exception e) { // 捕获所有异常
					System.out.println("输入错误，请重新选择");
					continue;
				}
			}
		} else {
			System.out.println("没有该房间");
			Constants.sc.nextLine();
			return;
		}
	}
}
