package database;

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
                             ("select * from account where user=? and password=?");
         ps.setString(1, uname);
         ps.setString(2, pass);
         ResultSet rs =ps.executeQuery();
         st = rs.next();
			
			con.close();
        
      }catch(Exception e)
      {
          e.printStackTrace();
      }
         return st;                 
     }
     
     public static int createUser(String uname, String pass, int accType, String email, String fname, String mname, String lname) {
    	Connection con;
    	int result = 0;
    	// 0 - Success, 1 - Username Taken, 2 - E-Mail Taken, 3 - Account Exists (Both), 4 - Server Error
    	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection
                    ("jdbc:mysql://localhost:" + port + "/" + dbname, username, password);

			PreparedStatement ps =con.prepareStatement
			                      ("select * from account where user=?");
			ps.setString(1, uname);
			  
			ResultSet rs =ps.executeQuery();
			  
			if(rs.next()) {
				  result = 1;
			}

			ps = con.prepareStatement
			                      ("select * from account where email=?");
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
				          ("INSERT INTO account"
				          + "(user, password, acctype, email, fname, mname, lname) VALUES"
				          + "(?,?,?,?,?,?,?)");
				
				ps.setString(1, uname);
				ps.setString(2, pass);
				ps.setInt	(3, accType);
				ps.setString(4, email);
				ps.setString(5, fname);
				ps.setString(6, mname);
				ps.setString(7, lname);
				
				ps.executeUpdate();
				
				con.close();
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
	 						+ "(productID, stockAmt) VALUES"
	 					    + "(?,?)");

			ps.setInt		(1, id);
			ps.setInt		(2, 0);
				
			ps.executeUpdate();
			
			ps = con.prepareStatement
	 				("INSERT INTO sales"
	 						+ "(productID, amountSold) VALUES"
	 					    + "(?,?)");

			ps.setInt		(1, id);
			ps.setInt		(2, 0);
				
			ps.executeUpdate();
			
			con.close();
    	 } catch (Exception ex) {
    		 ex.printStackTrace();
    		 
    		 return false;
    	 }
    	 
    	 return true;
     }
     
     // ! ---------  NOT DONE ---------- !
     public static boolean addShippingAddress(int accountID, String houseNo, String street, String subdivision,
    		 									String city, String postalCode, String country) {
    	 Connection con;
    	 
    	 try {
    		Class.forName("com.mysql.jdbc.Driver");
 			con = DriverManager.getConnection
                     ("jdbc:mysql://localhost:" + port + "/" + dbname, username, password);
 			PreparedStatement ps = con.prepareStatement
 				("QUERY/UPDATE STATEMENT HERE");
			
			//ps.executeUpdate(); for updates/inserts
 			//ResultSet rs = ps.executeQuery(); for queries
			
			con.close();
    	 } catch (Exception ex) {
    		 ex.printStackTrace();
    		 
    		 return false;
    	 }
    	 
    	 return true;
     }
     
     // ! ---------  NOT DONE ---------- !
     public static int getAccountID(String user) {
    	 Connection con;
    	 int userID = -1;
    	 
    	 try {
    		Class.forName("com.mysql.jdbc.Driver");
 			con = DriverManager.getConnection
                     ("jdbc:mysql://localhost:" + port + "/" + dbname, username, password);
 			PreparedStatement ps = con.prepareStatement
 				("SELECT id FROM account where user = '" + user +"';");
			
			//ps.executeUpdate(); for updates/inserts
 			ResultSet rs = ps.executeQuery();
			
 			rs.next();
 			userID = rs.getInt(1);
 			
			con.close();
    	 } catch (Exception ex) {
    		 ex.printStackTrace();
    		 
    		 return userID;
    	 }
    	 
    	 return userID;
     }
     
     // ! ---------  NOT DONE ---------- !
     public static boolean addBillingAddress(int accountID, String houseNo, String street, String subdivision,
    		 									String city, String postalCode, String country) {
    	 Connection con;
    	 
    	 try {
    		Class.forName("com.mysql.jdbc.Driver");
 			con = DriverManager.getConnection
                     ("jdbc:mysql://localhost:" + port + "/" + dbname, username, password);
 			PreparedStatement ps = con.prepareStatement
 				("QUERY/UPDATE STATEMENT HERE");
			
			//ps.executeUpdate(); for updates/inserts
 			//ResultSet rs = ps.executeQuery(); for queries
			
			con.close();
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
 				("DELETE FROM products WHERE productID = ?");
 			
 			ps.setInt(1, productID);
			
			ps.executeUpdate(); //for updates
 			//ResultSet rs = ps.executeQuery(); for queries
			
			con.close();
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
			
			con.close();
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
			
			con.close();
    	 } catch (Exception ex) {
    		 ex.printStackTrace();
    		 
    		 return false;
    	 }
    	 
    	 return true;
     }
     
     // ! ---------  NOT DONE ---------- !
     /*
      *  Insert a row in cart table with the
      *  accountID of the buyer and the productID
      *  of the product to buy and the amount of it
      */
     public static boolean addToCart(int accountID, int productID, int amount) {
    	 Connection con;
    	 
    	 try {
    		Class.forName("com.mysql.jdbc.Driver");
 			con = DriverManager.getConnection
                     ("jdbc:mysql://localhost:" + port + "/" + dbname, username, password);
 			PreparedStatement ps = con.prepareStatement
 				("QUERY/UPDATE STATEMENT HERE");
			
			//ps.executeUpdate(); for updates
 			//ResultSet rs = ps.executeQuery(); for queries
			
			con.close();
    	 } catch (Exception ex) {
    		 ex.printStackTrace();
    		 
    		 return false;
    	 }
    	 
    	 return true;
     }
     
     // ! ---------  NOT DONE ---------- !
     /*
      *  Delete a row from cart table
      */
     public static boolean deleteFromCart(int accountID, int productID) {
    	 Connection con;
    	 
    	 try {
    		Class.forName("com.mysql.jdbc.Driver");
 			con = DriverManager.getConnection
                     ("jdbc:mysql://localhost:" + port + "/" + dbname, username, password);
 			PreparedStatement ps = con.prepareStatement
 				("DELETE FROM cart WHERE accountID = ? AND productID = ?;");
 			
 			ps.setInt(1, accountID);
 			ps.setInt(2, productID);
			
			ps.executeUpdate(); //for updates
 			//ResultSet rs = ps.executeQuery(); for queries
			
			con.close();
    	 } catch (Exception ex) {
    		 ex.printStackTrace();
    		 
    		 return false;
    	 }
    	 
    	 return true;
     }
     
     // ! ---------  NOT DONE ---------- !
     /*
      *  Delete all rows with the given accountID
      */
     public static boolean clearCart(int accountID) {
    	 Connection con;
    	 
    	 try {
    		Class.forName("com.mysql.jdbc.Driver");
 			con = DriverManager.getConnection
                     ("jdbc:mysql://localhost:" + port + "/" + dbname, username, password);
 			PreparedStatement ps = con.prepareStatement
 				("QUERY/UPDATE STATEMENT HERE");
			
			//ps.executeUpdate(); for updates
 			//ResultSet rs = ps.executeQuery(); for queries
			
			con.close();
    	 } catch (Exception ex) {
    		 ex.printStackTrace();
    		 
    		 return false;
    	 }
    	 
    	 return true;
     }
     
     // ! ---------  NOT DONE ---------- !
     /*
      *  Update amount of a product in the customer's cart
      *  to be used in instances where the customer 
      *  increases/decreases the amount to buy
      */
     public static boolean updateCartItem(int accountID, int productID, int amount) {
    	 Connection con;
    	 
    	 try {
    		Class.forName("com.mysql.jdbc.Driver");
 			con = DriverManager.getConnection
                     ("jdbc:mysql://localhost:" + port + "/" + dbname, username, password);
 			PreparedStatement ps = con.prepareStatement
 				("QUERY/UPDATE STATEMENT HERE");
			
			//ps.executeUpdate(); for updates
 			//ResultSet rs = ps.executeQuery(); for queries
			
			con.close();
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
			
			con.close();
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
			
			ps.executeUpdate(); //for updates/inserts
 			//ResultSet rs = ps.executeQuery(); for queries
			
			con.close();
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