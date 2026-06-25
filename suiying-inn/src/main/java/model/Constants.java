package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * {@code Constants} 类是一个用于存储一些常量和全局变量的类。
 * <p>
 * 该类包含了以下属性：
 * <ul>
 * <li>{@link #users }：一个 {@code Map} 对象，用于存储用户对象，以用户名为键，用户对象为值。</li>
 * <li>{@link #rooms }：一个 {@code LinkedHashMap} 对象，用于存储房间对象，以房号为键，房间对象为值。</li>
 * <li>{@link #currentUser }：一个 {@code User} 对象，用于表示当前登录的用户，如果没有登录则为 null。</li>
 * <li>{@link #orders }：一个 {@code ArrayList} 对象，用于存储订单对象。</li>
 * <li>{@link #sc }：一个 {@code Scanner} 对象，用于统一输入流。</li>
 * </ul>
 * 
 * @author Suiying
 */

public class Constants {
	public static final Map<String, User> users = new HashMap<>(); // 初始化用户储存
	public static LinkedHashMap<String, Room> rooms = new LinkedHashMap<String, Room>(); // 房间列表
	public static User currentUser = null;
	public static ArrayList<Order> orders = new ArrayList<>(); // 订单列表
	public static Scanner sc = new Scanner(System.in); // 统一输入流
}
