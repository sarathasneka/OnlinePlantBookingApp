package com.onlineplantbooking.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.onlineplantbooking.model.User;


public class UserDao {
	public void insertUser(User user) {
		String insertQuery ="insert into user_details(user_name,email_id,user_password,mobile_number,address) values(?,?,?,?,?)";
	    
		ConnectionUtil conUtil = new ConnectionUtil();
		Connection con = conUtil.getDbConnection();
		
		PreparedStatement pst = null;
		
		try {
			pst = con.prepareStatement(insertQuery);
			pst.setString(1, user.getName());
			pst.setString(2, user.getEmailId());
			pst.setString(3, user.getPassword());
			pst.setLong(4,user.getMobileNumber());
			pst.setString(5,user.getAddress());
			int rows =pst.executeUpdate();
			if(rows>0) {
				System.out.println("Value inserted Succes");
			}
			
		} catch (SQLException e) {
			
            e.getMessage();
			e.printStackTrace();
			System.out.println("Value not inserted in the table");
		}
		
	}
	//register and login are same
	public static User validateUser(String emailId,String Password) {
		String validateQuery = "select * from user_details where role_name='user' and Email_id='" + emailId
				+ "'and user_password='" + Password + "'";

		Connection con = ConnectionUtil.getDbConnection();
		User user = null;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(validateQuery);
			if (rs.next()) {
				user = new User(rs.getString(2), emailId, Password, rs.getLong(5),rs.getString(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Statement error");
		}
		return user;
	}
	
public void update(String update) throws SQLException{ 

	String updateQuery="update user_details set user_password=? where email_id=?";
	Connection con=ConnectionUtil.getDbConnection();
	System.out.println("Connection suceessfully");
	
	
	PreparedStatement pstmt=con.prepareStatement(updateQuery);
	pstmt.setString(1,update.split(",")[0]);
	pstmt.setString(2,update.split(",")[1]);
	int i=pstmt.executeUpdate();
	System.out.println(i+"row updated");
	pstmt.close();
	con.close();
}
public void deleteUser(String delete) throws SQLException  {
	String deleteQuery="delete from user_detail where user_id=?";
	Connection con=ConnectionUtil.getDbConnection();
	System.out.println("Connection successfully");
	
	PreparedStatement pstmt=con.prepareStatement(deleteQuery);
	pstmt.setInt(1,Integer.parseInt(delete));
	int i=pstmt.executeUpdate();
	System.out.println(i+"row deleted");
	pstmt.close();
	con.close();
		
		
	} 


//show all user method 

	public static List<User> showAllUser() {
		List<User> userList = new ArrayList<User>();

		String selectQuery = "select * from user_details where role_name='user'";

		ConnectionUtil conUtil = new ConnectionUtil();
		Connection con = conUtil.getDbConnection();
		Statement stmt;

		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(selectQuery);
			while (rs.next()) {
				userList.add(new User(rs.getString(2), rs.getString(3),rs.getString(4),Long.parseLong(rs.getString(5)),rs.getString(6)));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userList;
	}

public static int findUserId(User currentUser) {
		
		String findUserID = "select user_id from user_details where user_name='"+currentUser.getName()+"'";
		Connection con = ConnectionUtil.getDbConnection();
		Statement stmt;
		
		int userId = 0;
		try {
			stmt = con.createStatement();
//			
			ResultSet rs = stmt.executeQuery(findUserID);

			if (rs.next()) {
				System.out.println(rs.getInt(1));
				userId = rs.getInt(1);
				//System.out.println(userId);
			}
		

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userId;



}
public static User findUser(int userId) {
	
	String findUserID = "select * from user_details where user_id="+userId;
	Connection con = ConnectionUtil.getDbConnection();
	Statement stmt;
	
	User user = null;
	try {
		stmt = con.createStatement();
//		
		ResultSet rs = stmt.executeQuery(findUserID);

		if (rs.next()) {
			//System.out.println(rs.getInt(1));
			user =new User(rs.getString(1),  rs.getString(2), rs.getString(3), rs.getLong(4), rs.getString(5));
			//System.out.println(userId);
		}
	

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return user;



}

}



 



