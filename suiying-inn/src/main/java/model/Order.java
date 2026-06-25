package model;

import java.time.LocalDate;

/**
 * {@code Order} 类是一个用于表示订单信息的类。
 * <p>
 * 该类包含了以下属性：
 * <ul>
 * <li>{@link #username }：一个 {@code String} 对象，用于表示预约用户的用户名。</li>
 * <li>{@link #room }：一个 {@code Room} 对象，用于表示预约的房间对象。</li>
 * <li>{@link #checkInDate }：一个 {@code LocalDate} 对象，用于表示入住日期。</li>
 * <li>{@link #checkOutDate }：一个 {@code LocalDate} 对象，用于表示退房日期。</li>
 * <li>{@link #breakfast }：一个 {@code boolean} 值，用于表示是否订购早餐。</li>
 * <li>{@link #totalPrice }：一个 {@code double} 值，用于表示订单总额。</li>
 * </ul>
 *
 * 该类提供了以下方法，用于获取和设置属性值：
 * <ul>
 * <li>{@link #Order(String, Room, LocalDate, LocalDate, boolean) }：构造方法，使用参数初始化属性值。</li>
 * <li>{@link #getUsername() }：获取用户名的方法，返回一个 {@code String} 对象。</li>
 * <li>{@link #getRoom() }：获取房间对象的方法，返回一个 {@code Room} 对象。</li>
 * <li>{@link #getCheckInDate() }：获取入住日期的方法，返回一个 {@code LocalDate} 对象。</li>
 * <li>{@link #getCheckOutDate() }：获取退房日期的方法，返回一个 {@code LocalDate} 对象。</li>
 * <li>{@link #gettotalPrice() }：获取订单总额的方法，返回一个 {@code double} 值。</li>
 * <li>{@link #getBreakfast() }：获取是否订购早餐的方法，返回一个 {@code boolean} 值。</li>
 * <li>{@link #setRoom(Room) }：设置房间对象的方法，使用参数修改属性值。</li>
 * <li>{@link #setCheckInDate(LocalDate) }：设置入住日期的方法，使用参数修改属性值。</li>
 * <li>{@link #setCheckOutDate(LocalDate) }：设置退房日期的方法，使用参数修改属性值。</li>
 * <li>{@link #setBreakfast(boolean) }：设置是否订购早餐的方法，使用参数修改属性值。</li>
 * <li>{@link #settotalPrice(double) }：设置订单总额的方法，使用参数修改属性值。</li>
 * </ul>
 * 
 * @author Suiying
 */

public class Order {
	private final String username;
	private Room room;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private boolean breakfast;
	private double totalPrice;

	public Order(String username, Room room, LocalDate checkInDate, LocalDate checkOutDate, boolean breakfast,
			double totalPrice) {
		this.username = username;
		this.room = room;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.totalPrice = totalPrice;
		this.breakfast = breakfast;
	}

	public String getUsername() {
		return username;
	}

	public Room getRoom() {
		return room;
	}

	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	public double gettotalPrice() {
		return totalPrice;
	}

	public boolean getBreakfast() {
		return breakfast;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}

	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public void setBreakfast(boolean breakfast) {
		this.breakfast = breakfast;
	}

	public void settotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
