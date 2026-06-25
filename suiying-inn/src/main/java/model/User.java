package model;

/**
 * {@code User} 类是一个用于表示用户信息的类。
 * <p>
 * 该类包含了以下属性：
 * <ul>
 * <li>{@link #username }：一个 {@code String} 对象，用于表示用户名。</li>
 * <li>{@link #password }：一个 {@code String} 对象，用于表示密码。</li>
 * <li>{@link #isAdmin }：一个 {@code boolean} 值，用于表示是否是管理员用户。</li>
 * </ul>
 *
 * 该类提供了以下方法，用于获取和设置属性值：
 * <ul>
 * <li>{@link #User(String, String, boolean) }：构造方法，使用参数初始化属性值。</li>
 * <li>{@link #getUsername() }：获取用户名的方法，返回一个 {@code String} 对象。</li>
 * <li>{@link #getPassword() }：获取密码的方法，返回一个 {@code String} 对象。</li>
 * <li>{@link #isAdmin() }：判断是否是管理员用户的方法，返回一个 {@code boolean} 值。</li>
 * <li>{@link #setPassword(String) }：设置密码的方法，使用参数修改属性值。</li>
 * </ul>
 * 
 * @author Suiying
 */

public class User {
	private final String username;
	private String password;
	private boolean isAdmin;

	public User(String username, String password, boolean isAdmin) {
		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setPassword(String newPassword) {
		this.password = newPassword;
	}
}
