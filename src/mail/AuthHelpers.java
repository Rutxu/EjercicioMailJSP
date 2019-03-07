package mail;


import java.sql.*;
import com.mysql.jdbc.Driver;

public class AuthHelpers {

	public static boolean checkUser(String username, String password) {

		boolean st = false;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "practicas");
			PreparedStatement ps = con.prepareStatement("select * from users where name=? and password=? and validate =1F");
			ps.setString(1, username);
			ps.setString(2, password);
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			st = rs.next();
			System.out.println(st);

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return st;

	}

	public static boolean insertUser(String username,String email, String password) {

		boolean st = false;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "practicas");
			PreparedStatement ps = con.prepareStatement("INSERT INTO users(id, name,email, password) VALUES (NULL, ?, ?, ?)");
			ps.setString(1, username);
			ps.setString(2, email);
			ps.setString(3, password);
			System.out.println(ps);
			int rs = ps.executeUpdate();
			if (rs == 1) {
				st = true;
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return st;

	}
	
	public static boolean insertValidation(String username, String validation) {

		boolean st = false;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "practicas");
			PreparedStatement ps = con.prepareStatement("UPDATE users SET validate = ? WHERE name = ?");
			ps.setString(1, validation );
			ps.setString(2, username);
			System.out.println(ps);
			int rs = ps.executeUpdate();
			if (rs == 1) {
				st = true;
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return st;

	}
	
	public static String getPass(String username) {
		String result = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "practicas");
			PreparedStatement ps = con.prepareStatement("select password from users where name=?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			result = rs.getString("password");

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return result;	
	}
	
	public static boolean validate(String confirmHash) {
		
		boolean vl = false;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "practicas");
			PreparedStatement ps = con.prepareStatement("UPDATE users SET validate = True WHERE validate = ?");
			ps.setString(1, confirmHash );
			int rs = ps.executeUpdate();
			if (rs == 1) {
				vl = true;
			}
			
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return vl;
		
	}

}
