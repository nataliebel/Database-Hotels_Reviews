package databases;

import java.sql.*;

import objects.User;

public class UsersDB {
	private Connection con;
	
	private static final String INSERT_NEW_USER = "insert into users(Name,Password,Gender,Key_Country) values(?,?,?,?)";
	private static final String SELECT_USER_BY_NAME = "select * from users where Name= ? and Password= ?";
	private static final String SELECT_USER_BY_KEY = "select * from users where Key_User = ?";
	private static final String UPDATE_USER_PASSWORD = "update users set Password = ? where Key_User = ?";

	public UsersDB(Connection con) {
		this.con = con;
	}
	
	public boolean changeUserPassword(int keyUser, String newPassword) {
		boolean isChanged = false;
		try {
			String query = UPDATE_USER_PASSWORD;
			PreparedStatement pst = this.con.prepareStatement(query);
			
			pst.setString(1, newPassword);
			pst.setInt(2, keyUser);

			pst.executeUpdate();
			isChanged = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isChanged;
	}

	// for register user
	public boolean saveUser(User user) {
		boolean set = false;
		try {
			// Insert register data to database
			String query = INSERT_NEW_USER;
			
			PreparedStatement pst = this.con.prepareStatement(query);
			
			pst.setString(1, user.getName());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getGender());
			pst.setInt(4, user.getKeyCountry());

			pst.executeUpdate();
			set = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return set;
	}

	// for login user
	public User login(String userName, String pass) {
		User user = null;
		try {
			String query = SELECT_USER_BY_NAME;
			PreparedStatement pst = this.con.prepareStatement(query);
			
			pst.setString(1, userName);
			pst.setString(2, pass);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				user = new User(rs.getInt("Key_User"), rs.getString("Name"), rs.getString("Password"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public User getUserByKey(int keyUser) {
		User user = null;
		try {
			String query = SELECT_USER_BY_KEY;
			PreparedStatement pst = this.con.prepareStatement(query);
			
			pst.setInt(1, keyUser);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				user = new User(rs.getInt("Key_User"), rs.getString("Name"), rs.getString("Password"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

}
