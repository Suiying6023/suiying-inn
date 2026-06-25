package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.LinkedHashMap;

/**
 * {@code Room} 类是一个用于表示房间信息的类。
 * <p>
 * 该类包含了以下属性：
 * <ul>
 * <li>{@link #id }：一个 {@code String} 对象，用于表示房号。</li>
 * <li>{@link #type }：一个 {@code String} 对象，用于表示房型。</li>
 * <li>{@link #info }：一个 {@code String} 对象，用于表示具体信息。</li>
 * <li>{@link #num }：一个 {@code int} 值，用于表示数量。</li>
 * <li>{@link #price }：一个 {@code double} 值，用于表示价格（无早餐）。</li>
 * <li>{@link #bookedDates }：一个 {@code int} 数组，用于表示被预约数量（未来七天）。</li>
 * <li>{@link #rooms }：一个 {@code LinkedHashMap} 对象，用于存储房间对象，以房号为键，房间对象为值。</li>
 * </ul>
 *
 * 该类提供了以下方法，用于获取和设置属性值，以及判断和更新被预约数量：
 * <ul>
 * <li>{@link #Room(String, String, String, int, double) }：构造方法，使用参数初始化属性值，并将被预约数量数组填充为0。</li>
 * <li>{@link #getRooms() }：获取房间列表的方法，返回一个 {@code LinkedHashMap}
 * 对象，并初始化一些房间对象作为示例数据。</li>
 * <li>{@link #getId() }：获取房号的方法，返回一个 {@code String} 对象。</li>
 * <li>{@link #getType() }：获取房型的方法，返回一个 {@code String} 对象。</li>
 * <li>{@link #getInfo() }：获取具体信息的方法，返回一个 {@code String} 对象。</li>
 * <li>{@link #getNum() }：获取数量的方法，返回一个 {@code int} 值。</li>
 * <li>{@link #getPrice() }：获取价格的方法，返回一个 {@code double} 值。</li>
 * <li>{@link #setNum(int) }：设置数量的方法，使用参数修改属性值。</li>
 * <li>{@link #setPrice(double) }：设置价格的方法，使用参数修改属性值。</li>
 * <li>{@link #getBookedDates() }：获取被预约数量数组的方法，返回一个 {@code int} 数组。</li>
 * <li>{@link #setBookedDates(int[]) }：设置被预约数量数组的方法，使用参数修改属性值。</li>
 * <li>{@link #equals(Object) }：判断两个房间对象是否相等的方法，根据 id，type 和 info 属性进行比较，并返回一个
 * {@code boolean} 值。</li>
 * <li>{@link #updateBookedDates(String, LocalDate, LocalDate, int) }：更新被预约数量数组的方法，接收一个房号，一个入住日期，一个退房日期和一个数量作为参数，</li>
 * <li>并根据参数修改对应房间对象的被预约数量数组中的元素值。</li>
 * </ul>
 * 
 * @author Suiying
 */

public class Room {
	private final String id;
	private final String type;
	private final String info;
	private int num;
	private double price;
	private int[] bookedDates;
	private static LinkedHashMap<String, Room> rooms = new LinkedHashMap<String, Room>();

	public Room(String id, String type, String info, int num, double price) {
		this.id = id;
		this.type = type;
		this.info = info;
		this.num = num;
		this.price = price;
		this.bookedDates = new int[7];
		Arrays.fill(this.bookedDates, 0);
	}

	public static LinkedHashMap<String, Room> getRooms() {
		rooms = new LinkedHashMap<>();
		rooms.put("1001", new Room("1001", "大床房", "高级大床房", 2, 600));
		rooms.put("1002", new Room("1002", "大床房", "豪华大床房", 2, 700));
		rooms.put("1003", new Room("1003", "大床房", "景观大床房", 2, 850));
		rooms.put("2001", new Room("2001", "双床房", "标准双床房", 2, 600));
		rooms.put("2002", new Room("2002", "双床房", "高级双床房", 2, 800));
		rooms.put("2003", new Room("2003", "双床房", "豪华双床房", 2, 1000));
		rooms.put("3001", new Room("3001", "套房", "景致小套房", 1, 1500));
		rooms.put("3002", new Room("3002", "套房", "婴儿小套房", 1, 2000));
		rooms.put("3003", new Room("3003", "套房", "亲子大套房", 1, 2500));
		return rooms;
	}

	public String getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public String getInfo() {
		return info;
	}

	public int getNum() {
		return num;
	}

	public double getPrice() {
		return price;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int[] getBookedDates() {
		return bookedDates;
	}

	public void setBookedDates(int[] bookedDates) {
		this.bookedDates = bookedDates;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Room) {
			Room r = (Room) obj;
			return id.equals(r.id) && type.equals(r.type) && info.equals(r.info);
		}
		return false;
	}

	// updateBookedDates方法，接收一个房号，一个入住日期，一个退房日期和一个数量作为参数，更新该房号对应的房间对象的被预约数量属性
	public static void updateBookedDates(String id, LocalDate checkInDate, LocalDate checkOutDate, int num) {
		// 获取当前日期
		LocalDate today = LocalDate.now();
		// 计算入住日期和退房日期分别与当前日期相差的天数
		int checkInDays = (int) ChronoUnit.DAYS.between(today, checkInDate);
		int checkOutDays = (int) ChronoUnit.DAYS.between(today, checkOutDate);
		// 以房号确定房间
		Room room = rooms.get(id);
		// 获取房间对象的被预约数量属性
		int[] bookedDates = room.getBookedDates();
		// 使用for循环，从checkInDays开始，到checkOutDays结束（不包括），将bookedDates数组中对应位置的元素加上数量
		for (int i = checkInDays; i < checkOutDays; i++) {
			bookedDates[i] += num;
		}
		// 将更新后的bookedDates数组赋值给房间对象
		room.setBookedDates(bookedDates);
	}
}
