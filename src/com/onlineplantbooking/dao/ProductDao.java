package com.onlineplantbooking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.onlineplantbooking.model.Product;

public class ProductDao {
   //show product   
	
	
	public List<Product> showProduct(){
    	  List<Product> productList=new ArrayList<Product>();
    	  
    	  
    	  String showQuery="select *from product_details";
    	  Connection con=ConnectionUtil.getDbConnection();
    	  
    	  try {
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(showQuery);
			while(rs.next())
			{
				Product product=new Product(rs.getString(2),rs.getString(3),Integer.parseInt(rs.getString(4)),rs.getString(5));
				productList.add(product);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productList;
		   	  
    	  
      }
    public static   int findProductId(Product product)
    {
		String Query="select plant_id from product_details where plant_name=? and category_name=?";
		Connection con=ConnectionUtil.getDbConnection();
		int productId=0;
	 
		    try {
				PreparedStatement ps=con.prepareStatement(Query);
				ps.setString(1, product.getPlantName());
				ps.setString(2,product.getCategoryName());
				ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
					productId=rs.getInt(1);
				}
				System.out.println(productId);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return productId;
			
    }
			
			
    public static Product findProduct(int productId)
	{
		String query="select * from product_details where plant_id=?";
		
		Connection con=ConnectionUtil.getDbConnection();
		Product products=null;
		try {
			PreparedStatement pre=con.prepareStatement(query);
			pre.setInt(1, productId);
			
			ResultSet rs=pre.executeQuery();
			
			if(rs.next())
			{
				products=new Product(rs.getString(2), rs.getString(3),Integer.parseInt(rs.getString(4)), rs.getString(5));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return products;
		
	}
    
    
    
    
    
    public static void updateProduct(String plantName,int plantId) throws ClassNotFoundException, SQLException {
				String query="commit";
    	String updateQuery = "update product_details set plant_name =?  where plant_id=?";
				System.out.println("update");

				Connection con = ConnectionUtil.getDbConnection();
				PreparedStatement pstmt2 = con.prepareStatement(query);
				pstmt2.executeUpdate();
				PreparedStatement pstmt = con.prepareStatement(updateQuery);
				pstmt.setString(1, plantName);
				pstmt.setInt(2, plantId);
				int i = pstmt.executeUpdate();
				System.out.println(i + "row updated");
				pstmt.close();
				con.close();
			
		}
							
				//delete method 
				
				public static void deleteProduct(int plantId) throws ClassNotFoundException, SQLException {
					String deleteQuery = "delete from product_details where plant_id=?";

					Connection con = ConnectionUtil.getDbConnection();
					//System.out.println("Connection successfully");
					PreparedStatement pstmt = con.prepareStatement(deleteQuery);
					pstmt.setInt(1, plantId);
					int i = pstmt.executeUpdate();
					System.out.println(i + "row deleted");
					pstmt.close();
					con.close();
				}
				
			}
		
    	
    
    
