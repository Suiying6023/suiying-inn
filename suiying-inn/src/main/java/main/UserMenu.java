package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import model.Constants;
import model.Order;
import model.Room;

/**
 * {@code UserMenu} 类是一个用于实现用户菜单与功能的类。
 * <p>
 * 该类提供了以下方法，用于显示用户菜单，预约房间，展示和取消订单：
 * <ul>
 * <li>{@link #userMenu()}：显示用户菜单。</li>
 * <li>{@link #bookRoom()}：根据用户输入的日期显示当前可预约房间，提示是否需要早餐，预约完成后打印出订单。</li>
 * <li>{@link #showMyOrders()}：展示当前用户订单，并提示用户是否需要取消订单。</li>
 * <li>{@link #cancelOrder()}：由 {@link #showMyOrders()} 调用，实现取消订单功能。</li>
 * </ul>
 * 
 * @author Suiying
 * @see model.Constants
 * @see model.Room
 * @see model.User
 */

public class UserMenu {
	// 普通用户菜单
	public static void userMenu() throws Exception {
		while (true) {
			System.out.println();
			System.out.println("请选择你要进行的操作：");
			System.out.println("1.预约房间");
			System.out.println("2.查看订单");
			System.out.println("3.更改密码");
			System.out.println("4.退出账号");
			System.out.println();
			System.out.print("请输入你的选择（1-4）：");
			int choice = 0;
			try {
				String input = Constants.sc.nextLine(); // 读取字符
				choice = Integer.parseInt(input); // 转换成整数
			} catch (Exception e) {
				System.out.println("输入错误，请重新选择");
				continue;
			}
			switch (choice) {
			case 1:
				// 调用预约方法
				bookRoom();
				continue;
			case 2:
				// 显示当前用户的订单，并提示是否要进行取消订单操作，如果是则进行取消订单操作
				showMyOrders();
				System.out.println();
				continue;
			case 3:
				// 更改当前用户的密码
				Tools.changePassword();
				System.out.println();
				continue;
			case 4:
				// 退出当前账户
				Tools.logout();
				return;
			default:
				// 如果用户输入其他数字，则提示错误信息，并重新输入
				System.out.println("输入错误，请重新输入");
				System.out.println();
				System.out.println("--------------------");
				continue;
			}
		}
	}

	// bookRoom方法，实现预定房间功能
	private static void bookRoom() throws Exception {
		// 导入时间
		LocalDate today = LocalDate.now();
		LocalDate sevenDaysLater = today.plusDays(7);
		LocalDate checkInDate = null;
		// 接受不同的时间格式
		DateTimeFormatter[] formatters = new DateTimeFormatter[] { DateTimeFormatter.ofPattern("yyyy M dd"),
				DateTimeFormatter.ofPattern("yyyy MM dd"), DateTimeFormatter.ofPattern("yyyy M d"),
				DateTimeFormatter.ofPattern("yyyy MM d") };
		// 让用户输入要预约的入住日期和天数
		boolean validCheckInDate = false;
		while (!validCheckInDate) {
			System.out.println();
			System.out.print("请输入要预约的入住日期（例如：2023 6 8）：");
			if (!Constants.sc.hasNextLine()) {
				System.out.println("请输入一行有效的日期");
			} else {
				String checkInDateStr = Constants.sc.nextLine();
				// 尝试解析日期字符串，如果有异常则提示错误信息并继续循环
				for (DateTimeFormatter formatter : formatters) {
					try {
						checkInDate = LocalDate.parse(checkInDateStr, formatter);
						break;
					} catch (Exception e) {
					}
				}
				if (checkInDate == null) {
					System.out.println("日期格式错误，请重新输入");
				} else {
					validCheckInDate = true; // 跳出循环
				}
			}
		}
		int days = 0;
		boolean validDays = false; // 用于控制循环
		while (!validDays) {
			System.out.print("请输入要预约的天数：");
			String input = Constants.sc.nextLine(); // 读取用户输入的整行字符串
			try {
				days = Integer.parseInt(input); // 转换成整数
				if (days <= 0) {
					System.out.println("天数必须大于0哦！");
				} else {
					validDays = true; // 跳出循环
				}
			} catch (Exception e) {
				System.out.println("无法识别，请重新输入");
			}
		}
		// 根据入住日期和天数计算退房日期
		LocalDate checkOutDate = checkInDate.plusDays(days);
		// 判断用户输入的日期是否在当前日期和七天后的日期之间，如果不是则提示错误信息并返回
		if (checkInDate.isBefore(today) || checkInDate.isAfter(sevenDaysLater) || checkOutDate.isBefore(today)
				|| checkOutDate.isAfter(sevenDaysLater)) {
			System.out.println("仅支持预约一周以内的入住哦！");
			return;
		}
		int checkInDays = (int) ChronoUnit.DAYS.between(today, checkInDate);
		int checkOutDays = (int) ChronoUnit.DAYS.between(today, checkOutDate);
		ArrayList<Room> availableRooms = new ArrayList<Room>();
		for (Room room : Constants.rooms.values()) {
			// 获取房间对象的被预约数量属性，并计算入住日期和退房日期之间的最大被预约数量
			int[] bookedDates = room.getBookedDates();
			int maxBooked = 0;
			for (int i = checkInDays; i < checkOutDays; i++) {
				maxBooked = Math.max(maxBooked, bookedDates[i]);
			}
			// 判断最大被预约数量是否小于房间对象的数量，如果是则表示有空闲数量，并将该房间对象添加到availableRooms中
			if (maxBooked < room.getNum()) {
				availableRooms.add(room);
			}
		}
		// 判断可用房间列表是否为空，如果为空则提示没有可用房间，并返回
		if (availableRooms.isEmpty()) {
			System.out.println("没有可用房间");
			return;
		}

		// 如果不为空，则打印表头，并使用格式化字符串来打印每个房间对象的信息，包括房号，房型，具体信息，数量，价格（无早餐)
		System.out.println("以下是可预约的房间信息：");
		System.out.println();
		System.out.println("房号\t房型\t具体信息\t数量\t价格（不含早餐100元）");
		for (Room room : availableRooms) {
			int maxBooked = 0;
			int[] bookedDates = room.getBookedDates();
			for (int i = checkInDays; i < checkOutDays; i++) {
				maxBooked = Math.max(maxBooked, bookedDates[i]);
			}
			int freeNum = room.getNum() - maxBooked;
			System.out.printf("%s\t%s\t%s\t%d\t%.0f\n", room.getId(), room.getType(), room.getInfo(), freeNum,
					room.getPrice());
		}

		// 让用户输入要预约的房号
		Room room = null;
		boolean validRoomId = false;
		while (!validRoomId) {
			System.out.println();
			System.out.print("请输入要预约的房号：");
			if (!Constants.sc.hasNext()) {
				System.out.println("请输入一个有效的房号");
			} else {
				String roomId = Constants.sc.nextLine();
				// 根据id从map中获取对应的房间对象，如果不存在则提示错误信息并继续循环
				room = Constants.rooms.get(roomId);
				if (room == null) {
					System.out.println("输入错误，请重新输入");
				} else {
					validRoomId = true; // 跳出循环
				}
			}
		}
		// 新建订单
		Order order = new Order(Constants.currentUser.getUsername(), room, checkInDate, checkOutDate, false, 0);
		order.setRoom(room);
		order.setCheckInDate(checkInDate);
		order.setCheckOutDate(checkOutDate);
		// 是否有早餐
		while (true) {
			System.out.print("是否需要早餐（y/n）：");
			if (!Constants.sc.hasNext("[yn]")) {
				System.out.println("请输入y或n，请重新输入");
				continue;
			}
			String needBreakfast = Constants.sc.nextLine();
			if ("y".equals(needBreakfast)) {
				order.setBreakfast(true);
			}
			break;
		}
		if (order.getBreakfast()) {
			order.settotalPrice(room.getPrice() * days + 100 * days);
		} else {
			order.settotalPrice(room.getPrice() * days);
		}
		// 判断天数是否大于3天，大于或等于3天打八折
		if (days >= 3) {
			order.settotalPrice(order.gettotalPrice() * 0.8);
		}
		// 添加订单
		Constants.orders.add(order);
		// 更新房号对应已预约数量
		Room.updateBookedDates(room.getId(), checkInDate, checkOutDate, 1);
		// 打印预约成功的信息和订单详情
		System.out.println("预约成功！以下是您的订单详情：");
		System.out.println("--------------------");
		System.out.println("预约用户：" + order.getUsername());
		System.out.println("房号" + order.getRoom().getId());
		System.out.println("房型：" + order.getRoom().getType());
		System.out.println("具体信息：" + order.getRoom().getInfo());
		System.out.println("入住日期：" + order.getCheckInDate());
		System.out.println("退房日期：" + order.getCheckOutDate());
		System.out.println("是否订购早餐：" + (order.getBreakfast() ? "是" : "否"));
		System.out.println("订单总额：" + (int) order.gettotalPrice());
		System.out.println("--------------------");
		return;
	}

	// showMyOrders方法，实现显示当前用户订单功能，遍历订单列表，并打印属于当前用户的订单对象的信息
	private static void showMyOrders() throws Exception {
		DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy M d");
		// 判断订单列表是否为空，如果为空则提示没有订单信息
		if (Constants.orders.isEmpty()) {
			System.out.println("没有订单信息");
		} else {
			System.out.println("以下是你的订单信息：");
			for (Order order : Constants.orders) {
				// 判断订单的用户名是否与当前用户的用户名相同，如果是则打印该订单的信息
				if (order.getUsername().equals(Constants.currentUser.getUsername())) {
					System.out.println("房号：" + order.getRoom().getId());
					System.out.println("房型：" + order.getRoom().getType());
					System.out.println("具体信息：" + order.getRoom().getInfo());
					System.out.println("入住日期：" + sdf.format(order.getCheckInDate()));
					System.out.println("退房日期：" + sdf.format(order.getCheckOutDate()));
					System.out.println("是否订购早餐：" + (order.getBreakfast() ? "是" : "否"));
					System.out.println("订单总额：" + order.gettotalPrice());
					System.out.println("--------------------");
				}
			}
			while (true) {
				System.out.print("是否要进行取消订单操作？（y/n）：");
				if (!Constants.sc.hasNext("[yn]")) {
					System.out.println("请输入y或n，请重新输入");
					continue;
				} else {
					break;
				}
			}
			String answer = Constants.sc.nextLine();
			if (answer.equalsIgnoreCase("y")) {
				cancelOrder();
			} else {
				UserMenu.userMenu();
			}
		}
	}

	// cancelOrder方法，实现取消订单功能
	private static void cancelOrder() {
		// 提示用户输入要取消的订单的房号，并获取用户输入的字符串
		System.out.println();
		System.out.print("请输入要取消的订单的房号(如有相同房号订单，默认取消最早的订单)：");
		String id = Constants.sc.nextLine();
		// 遍历订单列表，如果有与该房号相同且属于当前用户的订单，则删除该订单，并提示取消成功，并返回
		for (Order order : Constants.orders) {
			if (order.getRoom().getId().equals(id) && order.getUsername().equals(Constants.currentUser.getUsername())) {
				// 获取该订单对象的入住日期和退房日期
				LocalDate checkInDate = order.getCheckInDate();
				LocalDate checkOutDate = order.getCheckOutDate();
				if (checkInDate.equals(LocalDate.now())) {
					System.out.println("抱歉，您不能在入住当天取消订单");
					return;
				} else {
					Room.updateBookedDates((String) order.getRoom().getId(), checkInDate, checkOutDate, -1);
					// 删除该订单
					Constants.orders.remove(order);
					System.out.println("取消成功");
					return;
				}
			}
		}
		// 如果没有找到相应的订单，则提示没有该订单
		System.out.println("没有该订单");
	}
}
