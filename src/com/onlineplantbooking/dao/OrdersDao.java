package com.onlineplantbooking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.onlineplantbooking.model.Orders;
import com.onlineplantbooking.model.Product;
import com.onlineplantbooking.model.User;

public class OrdersDao {
 


public void insertOrder(Orders order) {
	ProductDao proDao=new ProductDao();
	UserDao userDao=new UserDao();
	int userId=userDao.findUserId(order.getUser());
	int plant_id=proDao.findProductId(order.getProduct());
	
	String insertQuery="insert into plant_orders(plant_id,user_id,quantity,total_price) values(?,?,?,?)";
	ConnectionUtil conUtil=new ConnectionUtil();
	Connection con=conUtil.getDbConnection();
	PreparedStatement pst=null;
	
	try {
	 pst=con.prepareStatement(insertQuery);
	 pst.setInt(1, plant_id);
	 pst.setInt(2, userId);
	 pst.setInt(3, order.getQuantity());
	 pst.setDouble(4, order.getTotalPrice());
	 pst.executeUpdate();
	 System.out.println("value inserted successfully");
		
	} catch (SQLException e) {
	
		e.printStackTrace();
		System.out.println("value not inserted in the table");
	}
	
	
    
	}

	public List<Orders> viewOrders(User currentUser){
		List<Orders> userOrdersList=new ArrayList<Orders>();
		String query="select * from plant_orders";            //where user_id=?";
		Connection con=ConnectionUtil.getDbConnection();
		ProductDao productDao=new ProductDao();
		
		
		UserDao userDao=new UserDao();
		//int  userId = UserDao.findUserId(currentUser);
		try {
			Statement stmt=con.createStatement();
			//PreparedStatement pst=con.prepareStatement(query);
			//pst.setInt(1,userId);
			ResultSet rs=stmt.executeQuery(query);
			while (rs.next()) {
				Product products=productDao.findProduct(rs.getInt(3));
				//User user=userDao.findUser(rs.getInt(2));
				System.out.println(products);
				Orders orders=new Orders(products,currentUser,rs.getInt(3),rs.getDouble(4));
				//userOrdersList.add(new Orders(rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getDouble(5)));
				userOrdersList.add(orders);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userOrdersList;
	}
		
		public static void update(int quantity,int order_id) throws SQLException
		{
			String updateQuery="update plant_orders set quantity =? where order_id= ?";
			Connection con=ConnectionUtil.getDbConnection();
			PreparedStatement pstmt=con.prepareStatement(updateQuery);
		    pstmt.setInt(1,quantity);
		    pstmt.setInt(2, order_id);
		    int i=pstmt.executeUpdate();
		    System.out.println(i+"row updated");
		    pstmt.close();
		    con.close();
             			
      }

	 public static void delete(int orderId) throws SQLException {
		 String deleteQuery="delete from plant_orders where order_id=?";
		 Connection con=ConnectionUtil.getDbConnection();
		 PreparedStatement pstmt1=con.prepareStatement(deleteQuery);
		 pstmt1.setInt(1,orderId);
		 int i=pstmt1.executeUpdate();
		 System.out.println(i+"row deleted");
		 pstmt1.close();
		 con.close();
		 
	 }
	 
}

	









