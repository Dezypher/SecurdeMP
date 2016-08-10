package account;

import java.sql.*;
import java.util.ArrayList;

public class DBHelper
 {	
	private final static String password = "abcd1234";
	private final static String username = "root";
	private final static String dbname = "talaria";
	private final static String port = "3306";
	
     public static boolean checkUser(String uname,String pass) 
     {
      boolean st =false;
      try{

	 //loading drivers for mysql
         Class.forName("com.mysql.jdbc.Driver");

 	 //creating connection with the database 
         Connection con=DriverManager.getConnection
                        ("jdbc:mysql://localhost:" + port + "/" + dbname, username, password);
         PreparedStatement ps =con.prepareStatement
                             ("select * from user_account where user_name=? and user_password=?");
         ps.setString(1, uname);
         ps.setString(2, pass);
         ResultSet rs =ps.executeQuery();
         st = rs.next();
        
      }catch(Exception e)
      {
          e.printStackTrace();
      }
         return st;                 
     }
     
     public static int createUser(String uname, String pass, String email, String fname, String lname) {
    	Connection con;
    	int result = 0;
    	// 0 - Success, 1 - Username Taken, 2 - E-Mail Taken, 3 - Account Exists (Both), 4 - Server Error
    	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection
                    ("jdbc:mysql://localhost:" + port + "/" + dbname, username, password);

			PreparedStatement ps =con.prepareStatement
			                      ("select * from user_account where user_name=?");
			ps.setString(1, uname);
			  
			ResultSet rs =ps.executeQuery();
			  
			if(rs.next()) {
				  result = 1;
			}

			ps = con.prepareStatement
			                      ("select * from user_account where email=?");
			ps.setString(1, email);
			  
			rs =ps.executeQuery();
			
			if(rs.next()) {
				if(result == 1) {
					result = 3;
				} else {
					result = 2;
				}
			}
			
			if(result == 0) {
				ps = con.prepareStatement
				          ("INSERT INTO user_account"
				          + "(user_name, user_password, email, first_name, last_name) VALUES"
				          + "(?,?,?,?,?)");
				
				ps.setString(1, uname);
				ps.setString(2, pass);
				ps.setString(3, email);
				ps.setString(4, fname);
				ps.setString(5, lname);
				
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = 4;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = 4;
		}
    	 
    	 return result;
     }
     
     public static boolean createProduct(String name, String description, int type, float price, String imagePath) {
    	 Connection con;
    	 
    	 try {
    		Class.forName("com.mysql.jdbc.Driver");
 			con = DriverManager.getConnection
                     ("jdbc:mysql://localhost:" + port + "/" + dbname, username, password);
 			PreparedStatement ps = con.prepareStatement
 				("INSERT INTO products"
 						+ "(name, type, description, price, imagePath) VALUES"
 					    + "(?,?,?,?,?)");

			ps.setString	(1, name);
			ps.setInt		(2, type);
			ps.setString	(3, description);
			ps.setFloat		(4, price);
			ps.setString	(5, imagePath);
			
			ps.executeUpdate();

			ps =con.prepareStatement
 	 				("SELECT id FROM products ORDER BY id DESC LIMIT 1");
 			
 			ResultSet rs = ps.executeQuery();
 			
 			int id = -1;
 			
 			if(rs.next()) {
 				id = rs.getInt(1);
 			} else {
 				return false;
 			}
			
			ps = con.prepareStatement
	 				("INSERT INTO stock"
	 						+ "(stockAmt, productID) VALUES"
	 					    + "(?,?)");

			ps.setInt		(1, 0);
			ps.setInt		(2, id);
				
			ps.executeUpdate();
    	 } catch (Exception ex) {
    		 ex.printStackTrace();
    		 
    		 return false;
    	 }
    	 
    	 return true;
     }
     
     // ! ---------  NOT DONE ---------- !
     public static boolean deleteProduct(int productID) {
    	 Connection con;
    	 
    	 try {
    		Class.forName("com.mysql.jdbc.Driver");
 			con = DriverManager.getConnection
                     ("jdbc:mysql://localhost:" + port + "/" + dbname, username, password);
 			PreparedStatement ps = con.prepareStatement
 				("QUERY/UPDATE STATEMENT HERE");
			
			//ps.executeUpdate(); for updates
 			//ResultSet rs = ps.executeQuery(); for queries
    	 } catch (Exception ex) {
    		 ex.printStackTrace();
    		 
    		 return false;
    	 }
    	 
    	 return true;
     }
     
     // ! ---------  NOT DONE ---------- !
     public static boolean editProduct(int productID, String name, String description, int type, float price, String imagePath) {
    	 Connection con;
    	 
    	 try {
    		Class.forName("com.mysql.jdbc.Driver");
 			con = DriverManager.getConnection
                     ("jdbc:mysql://localhost:" + port + "/" + dbname, username, password);
 			PreparedStatement ps = con.prepareStatement
 				("QUERY/UPDATE STATEMENT HERE");
			
			//ps.executeUpdate(); for updates
 			//ResultSet rs = ps.executeQuery(); for queries
    	 } catch (Exception ex) {
    		 ex.printStackTrace();
    		 
    		 return false;
    	 }
    	 
    	 return true;
     }
     
     // ! ---------  NOT DONE ---------- !
     /*
      *  Update stock amount of a product using it's product ID
      *  in the stock table.
      */
     public static boolean updateStock(int productID, int stockAmt) {
    	 Connection con;
    	 
    	 try {
    		Class.forName("com.mysql.jdbc.Driver");
 			con = DriverManager.getConnection
                     ("jdbc:mysql://localhost:" + port + "/" + dbname, username, password);
 			PreparedStatement ps = con.prepareStatement
 				("QUERY/UPDATE STATEMENT HERE");
			
			//ps.executeUpdate(); for updates
 			//ResultSet rs = ps.executeQuery(); for queries
    	 } catch (Exception ex) {
    		 ex.printStackTrace();
    		 
    		 return false;
    	 }
    	 
    	 return true;
     }
     
     // ! ---------  NOT DONE ---------- !
     public static boolean addReview(int authorID, int productID, String review, int rating) {
    	 Connection con;
    	 
    	 try {
    		Class.forName("com.mysql.jdbc.Driver");
 			con = DriverManager.getConnection
                     ("jdbc:mysql://localhost:" + port + "/" + dbname, username, password);
 			PreparedStatement ps = con.prepareStatement
 				("QUERY/UPDATE STATEMENT HERE");
			
			//ps.executeUpdate(); for updates/inserts
 			//ResultSet rs = ps.executeQuery(); for queries
    	 } catch (Exception ex) {
    		 ex.printStackTrace();
    		 
    		 return false;
    	 }
    	 
    	 return true;
     }
     
     // ! ---------  NOT DONE ---------- !
     public static boolean deleteReview(int reviewID) {
    	 Connection con;
    	 
    	 try {
    		Class.forName("com.mysql.jdbc.Driver");
 			con = DriverManager.getConnection
                     ("jdbc:mysql://localhost:" + port + "/" + dbname, username, password);
 			PreparedStatement ps = con.prepareStatement
 				("QUERY/UPDATE STATEMENT HERE");
			
			//ps.executeUpdate(); for updates/inserts
 			//ResultSet rs = ps.executeQuery(); for queries
    	 } catch (Exception ex) {
    		 ex.printStackTrace();
    		 
    		 return false;
    	 }
    	 
    	 return true;
     }
     
     // ! ---------  NOT DONE ---------- !
     public static boolean updateReview(int reviewID, String review) {
    	 Connection con;
    	 
    	 try {
    		Class.forName("com.mysql.jdbc.Driver");
 			con = DriverManager.getConnection
                     ("jdbc:mysql://localhost:" + port + "/" + dbname, username, password);
 			PreparedStatement ps = con.prepareStatement
 				("QUERY/UPDATE STATEMENT HERE");
			
			//ps.executeUpdate(); for updates/inserts
 			//ResultSet rs = ps.executeQuery(); for queries
    	 } catch (Exception ex) {
    		 ex.printStackTrace();
    		 
    		 return false;
    	 }
    	 
    	 return true;
     }
     
     public static int getAccountType(String username) {
    	 Connection con;
    	 int accountType = -1;
    	 
    	 try {
    		Class.forName("com.mysql.jdbc.Driver");
 			con = DriverManager.getConnection
                     ("jdbc:mysql://localhost:" + port + "/" + dbname, username, password);
 			
 			PreparedStatement ps =con.prepareStatement
 	 				("SELECT accType FROM account WHERE user = ?;");
 			
 			ps.setString(1, username);
 			
 			ResultSet rs = ps.executeQuery();
 			
 			if(rs.next()) {
 				accountType = rs.getInt(1);
 			} else {
 				return accountType;
 			}
    	 } catch (Exception ex) {
    		 ex.printStackTrace();
    	 }
    	 
    	 return accountType;
     }
     
}