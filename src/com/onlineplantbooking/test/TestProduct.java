package com.onlineplantbooking.test;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.onlineplantbooking.dao.AdminDao;
import com.onlineplantbooking.dao.OrdersDao;
import com.onlineplantbooking.dao.ProductDao;
import com.onlineplantbooking.dao.UserDao;
import com.onlineplantbooking.model.Orders;
import com.onlineplantbooking.model.Product;
import com.onlineplantbooking.model.User;

public class TestProduct{

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("\tOnline Plant Booking");
		System.out.println("\n1.Register\n2.Login \nEnter your Choice :");
		int choice = Integer.parseInt(scan.nextLine());
		
//Register name validation
		UserDao userdao = null;
		String name = null;
		String emailId = null;
		String password=null;
		String mobileNumber;
		String address=null;
		switch (choice) {
		case 1:
			userdao = new UserDao();
			int flag = 0;
			System.out.println("Enter user Name:");
			name = scan.nextLine();
			do {
				if (name.matches("[A-Z][a-z]{3,}")) {
					flag = 0;
					break;
				} else
					System.out.println("Enter valid name ");
				name = scan.nextLine();
				flag = 1;
			} while (flag == 1);
			
			
			// email id validation
			
			
			System.out.println("Enter email ID:");
			emailId = scan.nextLine();

			do {
				if (emailId.matches("[a-z0-9]+[@][a-z]+[.][a-z]+{8,15}")) {
					flag = 0;
					break;
				} else
					System.out.println("Enter valid email ");
				emailId = scan.nextLine();
				flag = 1;
			} while (flag == 1);

			
			//password validation
			
						
			System.out.println("Enter password:");
			 password = scan.nextLine();
			do {
				if (password.matches("[A-Za-z0-9]{8,10}")) {
					flag = 0;
					break;
				} else
					System.out.println("Enter valid password pattern");
				password = scan.nextLine();
				flag = 1;
			} while (flag == 1);
			
			
			//mobilenumber validation
			
			System.out.println("Enter mobile number");
			 mobileNumber=scan.nextLine();
			 do {
				 if(mobileNumber.matches("[0-9]{10}")){
				 
				 flag=0;
				 break;
			 }
			 else { 
				 System.out.println("Enter valid mobile number");
				 mobileNumber=scan.nextLine();
				 flag=1;
				 }
			 }while (flag==1);
			 
			 //address validation
			
			 Long mobileNo=Long.parseLong(mobileNumber);			 
			 System.out.println("Enter Your Address:");
			address=scan.nextLine();
			do {
				if(address.matches("[A-Za-z0-9/,._]+"))
				{
		           flag=0;
		           break;
				}
				else {
					System.out.println("Enter valid address");
					address=scan.nextLine();
					flag=1;
					break;
				}
				
			}while (flag==1);
			
						 
			User user = new User(name, emailId, password,mobileNo,address);
			
			userdao.insertUser(user);
	        
			
//			System.out.println("Enter update details");
//	        String update=scan.nextLine();
//			try {
//				userdao.update(update);
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//	       System.out.println("Enter your delete details");
//	       String delete=scan.nextLine();
//			try {
//				userdao.deleteUser(delete);
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
	//		}
			
				
			
			
	//Login 
	  
	case 2:
	userdao=new UserDao();
	System.out.println("User Login ");
	//email validation
	System.out.println("Enter  email ID");
	emailId=scan.nextLine();
	do {
		if (emailId.matches("[a-z0-9]+[@][a-z]+[.][a-z]+{8,15}")) {
			flag = 0;
			break;		} else
			System.out.println("Enter valid email ");
		emailId = scan.nextLine();
		flag = 1;
	} while (flag == 1);

	//password validation
	
	System.out.println("Enter password:");
    password = scan.nextLine();
	do {
		if (password.matches("[A-Za-z0-9@#$]{8,10}")) {
			flag = 0;
			break;
		} else
			System.out.println("Mismatch password");
			System.out.println("Enter valid password pattern");
		    password = scan.nextLine();
		    flag = 1;
	} while (flag == 1);
	User user1=new User(emailId,password);
	UserDao userDao=new UserDao();
	User currentUser = userDao.validateUser(emailId,password);
	
	
	

	
	//check condition admin login
	if(currentUser==null)
	{
		
			User adminuser = AdminDao.validateAdmin(emailId, password);
			
			System.out.println("Welcome!!\t" + adminuser.getName() + "\t as admin");
			System.out.println("\n1.List Users \n2.find user\n3.update product\n4.delete Product");
		    System.out.println("Enter Your choice");
		    choice = Integer.parseInt(scan.nextLine());

		switch (choice) {
//list user				
		case 1:{

			System.out.println("List All User");
			List<User> userList = UserDao.showAllUser();
			for (int i = 0; i < userList.size(); i++) {
				System.out.println("\n" + userList.get(i));
			}}
//find user
		case 2:{
			int userId=0;
			userdao = new UserDao();
			System.out.println("enter email_Id:\n");
			emailId=scan.nextLine();
		
			try {
				userId = UserDao.findUserId(currentUser);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			System.out.println(userId);
			break;
			}
//update product
		case 3:
		{
			System.out.println("enter your plant Name and plant_id");
			String plantName = scan.nextLine();
			int plantId = Integer.parseInt(scan.nextLine());
			System.out.println("run");
			ProductDao.updateProduct(plantName,plantId);
			
			break;
			}
//delete Product
		case 4:
		{
			System.out.println("enter your plant id for delete:");
			int plantId = Integer.parseInt(scan.nextLine());
		
			ProductDao.deleteProduct(plantId);
		}
		}
			}
//User Login				

	 else {
		System.out.println("welcome !!\t");
		//do
		//{
		System.out.println("\n1.show products");
		choice = Integer.parseInt(scan.nextLine());
		// UserDao orderDao = new UserDao();

		
		//show products
		
		String userFlagChoice = null;
		switch (choice) {
		case 1:
			ProductDao proDao = new ProductDao();
			OrdersDao order = new OrdersDao();

		List<Product> productsList = proDao.showProduct();
			for (int i = 0; i < productsList.size(); i++) {
				System.out.println(productsList.get(i));
              
			}

			System.out.println("\n1.Order Product\n2.View Orders\n3.update product\n4.delete product ");

			int orderChoice = Integer.parseInt(scan.nextLine());

			Product product = null;
			String userFlag = null;
		

			switch (orderChoice) {
			case 1:
				do {
					
					for (int i = 0; i < productsList.size(); i++) {
						System.out.println(productsList.get(i));

					}
					System.out.println("Enter the Product Name:");							
					String productName = scan.nextLine();
					ProductDao proDao1=new ProductDao();
					for (int i = 0; i < productsList.size(); i++) {
						if (productsList.get(i).getPlantName().equals(productName))//change condition 
						{
							product = productsList.get(i);
							
							System.out.println("your product is"+product);
						}
					}
					System.out.println("enter no of Products Needed");
					int noOf = Integer.parseInt(scan.nextLine());

					float amount =(float) (noOf * product.getPlantPrice());
					
					Orders orders=new Orders(product, currentUser, noOf, amount);
//					OrdersDao orderDao=new OrdersDao();
					order.insertOrder(orders);

					System.out.println("Your total amount is:" + amount);					
					System.out.println("do you want to buy more products(y/n)");
					userFlag = scan.nextLine();
				} while (userFlag.charAt(0) == 'y');
				
				System.out.println("Thank you !!");
				
				break;
			case 2:
				System.out.println("Orders in cart");
				//System.out.println("amma");
				OrdersDao orderDao=new OrdersDao();
				//orderDao.viewOrders(currentUser);
				List<Orders> OrderList = order.viewOrders(currentUser);
			   System.out.println(OrderList);
				break;
			case 3:
				System.out.println("update products");
				 int order_id=Integer.parseInt(scan.nextLine());
			     int quantity=Integer.parseInt(scan.nextLine());
				//double totalprice=Double.parseDouble(scan.nextLine());
				OrdersDao orderDao1=new OrdersDao();
				orderDao1.update(order_id,quantity);
			case 4:
				System.out.println("delete products");
				int orderId=Integer.parseInt(scan.nextLine());
				OrdersDao orderDao2=new OrdersDao();
				orderDao2.delete(orderId);
				
				
			}
		}//while(choice < 5);
		
	 
	 }
	}
	}	 			
				
}		
	
	
	
//		//while (userFlagChoice.charAt(0) == 'y');
//
//		if (userFlagChoice == userFlagChoice) {
//			System.out.println("please enter the email id:");
//		} else {
//			System.out.println("Not a registered mobile number");
//
//		}
//		
			

		//break;

	
//	}while (choice < 5);
//}

//}

//}












//			do {
//			System.out.println("Enter email ID:");
//			String email=scan.nextLine();
//			if(email.matches("[a-z0-9]+[@][a-z]+[.][a-z]+")) {
//				System.out.println("Enter valid email_id");
//			}
//			if(email.isEmpty()) {
//				System.out.println("email can't emplty");
//			}
//				
//			}
//			while( );
//			System.out.println("Enter password:");
//			String password=scan.nextLine();
//			User user=new User(name,email,password);
//			userDao.insertUser(user);
//			
//				 
//	
//	
//			
//		
//		case 2:
//		userDao=new Userdao();
//		System.out.println("User Login ");
//		System.out.println("Enter the registered mail ID");
//		String mailid=scan.nextLine();
//		User currentUser=userDao.validateUser(mailid);
//		}
//	}
//
//	}		

//}	
