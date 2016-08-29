package database;

import java.sql.*;
import java.util.ArrayList;

import models.Account;
import models.Address;
import models.Product;
import models.PurchaseLog;
import models.Review;
import models.SaleLog;
import security.Encrypter;
import security.HTMLTagChecker;
import security.InputChecker;

public class DBHelper
 {	
	private final static String password = "abcd1234";
	private final static String username = "root";
	private final static String dbname = "talaria";
	private final static String port = "3306";
	
	public static String getSalt(String uname) {
	   	 Connection con;
	   	 String salt = "abcd1234";
	   	 
	   	 try {
	   		Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection
	                    ("jdbc:mysql://localhost:" + port + "/" + dbname, username, password);
				
				PreparedStatement ps =con.prepareStatement
		 				("SELECT salt FROM account WHERE user = ?;");
				
				ps.setString(1, uname);
				
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()) {
					salt = rs.getString(1);
				} else {
					System.out.println("User not found.");
					
					return salt;
				}
		    	 
		    	con.close();
	   	 } catch (Exception ex) {
	   		 ex.printStackTrace();
	   	 }
	   	 
	   	 return salt;
	}
	
     public static boolean checkUser(String uname,String pass) 
     {
      boolean st =false;
      try{
    	  
    //uncomment to encrypt
    	 String passWithSalt = pass + getSalt(uname);
    	 String encryptedPass = Encrypter.getEncrypted(passWithSalt);
    	 
    	 System.out.println("pass: " + encryptedPass);
    	 
     	if(encryptedPass == null)
     		return st;

	 //loading drivers for mysql
         Class.forName("com.mysql.jdbc.Driver");

 	 //creating connection with the database 
         Connection con=DriverManager.getConnection
                        ("jdbc:mysql://localhost:" + port + "/" + dbname, username, password);
         PreparedStatement ps =con.prepareStatement
                             ("select * from account where user=? and password=? and locked=0");
         ps.setString(1, uname);
         ps.setString(2, encryptedPass);
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
    	
    	//uncomment this to encrypt
    	
    	String salt = Encrypter.generateSalt();
    	String encryptedPass = Encrypter.getEncrypted(pass + salt);
    	
    	if(encryptedPass == null)
    		return 4;
    	
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
				          + "(user, password, acctype, email, fname, mname, lname, salt) VALUES"
				          + "(?,?,?,?,?,?,?,?)");
				
				ps.setString(1, uname);
				ps.setString(2, encryptedPass);
				ps.setInt	(3, accType);
				ps.setString(4, email);
				ps.setString(5, fname);
				ps.setString(6, mname);
				ps.setString(7, lname);
				ps.setString(8, salt);
				
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
			          ("INSERT INTO shipping_addr"
			          + "(accountID, houseNo, street, subdivision, city, postalCode, country) VALUES"
			          + "(?,?,?,?,?,?,?)");
			
 			ps.setInt	(1, accountID);
 			ps.setString(2, houseNo);
 			ps.setString(3, street);
 			ps.setString(4, subdivision);
 			ps.setString(5, city);
 			ps.setString(6, postalCode);
 			ps.setString(7, country);
 			
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
     public static int getAccountID(String user) {
    	 Connection con;
    	 int userID = -1;
    	 
    	 try {
    		Class.forName("com.mysql.jdbc.Driver");
 			con = DriverManager.getConnection
                     ("jdbc:mysql://localhost:" + port + "/" + dbname, username, password);
 			PreparedStatement ps = con.prepareStatement
 				("SELECT id FROM account where user = ?;");
			
 			ps.setString(1, user);
 			
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
			          ("INSERT INTO billing_addr"
			          + "(accountID, houseNo, street, subdivision, city, postalCode, country) VALUES"
			          + "(?,?,?,?,?,?,?)");
			
			ps.setInt	(1, accountID);
			ps.setString(2, houseNo);
			ps.setString(3, street);
			ps.setString(4, subdivision);
			ps.setString(5, city);
			ps.setString(6, postalCode);
			ps.setString(7, country);
			
			ps.executeUpdate(); //for updates/inserts
			
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
 				("UPDATE products SET disabled = 0 WHERE id = ?");
 			
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
 				("UPDATE products SET name=?, description=?, type=?, price=?, imagePath=? WHERE id=?;");
 			
 			ps.setString(1, name);
 			ps.setString(2, description);
 			ps.setInt(3, type);
 			ps.setFloat(4, price);
 			ps.setString(5, imagePath);
 			ps.setInt(6, productID);
 			
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
     
     public static Address getBillingAddress(int accountID) {
    	 Connection con;
    	 Address address = new Address();
    	 
    	 try {
    		Class.forName("com.mysql.jdbc.Driver");
 			con = DriverManager.getConnection
                     ("jdbc:mysql://localhost:" + port + "/" + dbname, username, password);
 			PreparedStatement ps = con.prepareStatement
 				("SELECT * FROM billing_addr WHERE accountID = ?");
 			
 			ps.setInt(1, accountID);
			
 			ResultSet rs = ps.executeQuery();
 			
 			if(rs.next()) {
				address.setHouseNum(rs.getString(2));
				address.setStreet(rs.getString(3));
				address.setSubdv(rs.getString(4));
				address.setCity(rs.getString(5));
				address.setPostcode(rs.getString(6));
				address.setCountry(rs.getString(7));
 			}
			
			con.close();
    	 } catch (Exception ex) {
    		 ex.printStackTrace();
    	 }
    	 
    	 return address;
     }
     
     public static Address getShippingAddress(int accountID) {
    	 Connection con;
    	 Address address = new Address();
    	 
    	 try {
    		Class.forName("com.mysql.jdbc.Driver");
 			con = DriverManager.getConnection
                     ("jdbc:mysql://localhost:" + port + "/" + dbname, username, password);
 			PreparedStatement ps = con.prepareStatement
 				("SELECT * FROM shipping_addr WHERE accountID = ?");
 			
 			ps.setInt(1, accountID);
			
 			ResultSet rs = ps.executeQuery();
 			
 			if(rs.next()) {
				address.setHouseNum(rs.getString(2));
				address.setStreet(rs.getString(3));
				address.setSubdv(rs.getString(4));
				address.setCity(rs.getString(5));
				address.setPostcode(rs.getString(6));
				address.setCountry(rs.getString(7));
 			}
			
			con.close();
    	 } catch (Exception ex) {
    		 ex.printStackTrace();
    	 }
    	 
    	 return address;
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
     
     public static boolean increaseSales(int productID, int amt) {
    	 Connection con;
    	 
    	 try {
    		Class.forName("com.mysql.jdbc.Driver");
 			con = DriverManager.getConnection
                     ("jdbc:mysql://localhost:" + port + "/" + dbname, username, password);
 			PreparedStatement ps = con.prepareStatement
 				("SELECT amountSold FROM sales WHERE productID = ?;");
			
 			ps.setInt(1, productID);

 			ResultSet rs = ps.executeQuery();
			
 			int currAmt = 0;
 			
			if(rs.next()) {
				currAmt = rs.getInt(1);
			}
			
			currAmt += amt;
			
			ps = con.prepareStatement
					("UPDATE sales SET amountSold = ? WHERE productID = ?;");
			
			ps.setInt(1, currAmt);
			ps.setInt(2, productID);
			
			ps.executeUpdate();
			
			con.close();
    	 } catch (Exception ex) {
    		 ex.printStackTrace();
    		 
    		 return false;
    	 }
    	 
    	 return true;
     }
     
     // ! ---------  NOT DONE ---------- !
     public static boolean addReview(String author, int productID, String review, float rating) {
    	 Connection con;
    	 
    	 try {
    		Class.forName("com.mysql.jdbc.Driver");
 			con = DriverManager.getConnection
                     ("jdbc:mysql://localhost:" + port + "/" + dbname, username, password);

 			PreparedStatement ps = con.prepareStatement
			          ("INSERT INTO review"
			          + "(author, productID, review, rating) VALUES"
			          + "(?,?,?,?)");
			
 			ps.setString	(1, author);
 			ps.setInt		(2, productID);
 			ps.setString	(3, review);
 			ps.setFloat		(4, rating);
 			
			ps.executeUpdate(); //for updates/inserts
 			//ResultSet rs = ps.executeQuery(); for queries
			
			con.close();
    	 } catch (Exception ex) {
    		 ex.printStackTrace();
    		 
    		 return false;
    	 }
    	 
    	 return true;
     }
     
     public static ArrayList<Review> getReviews(int productid) {
    	 Connection con;
    	 ArrayList<Review> reviews = new ArrayList<Review>();
    	 
    	 try {
     		Class.forName("com.mysql.jdbc.Driver");
  			con = DriverManager.getConnection
                      ("jdbc:mysql://localhost:" + port + "/" + dbname, username, password);
  			
  			PreparedStatement ps;
  			
  			ps =con.prepareStatement
  	 			("SELECT * FROM review WHERE productID = ?;");
  			
  			ps.setInt(1, productid);
  			
  			ResultSet rs = ps.executeQuery();
  			
  			while(rs.next()) {
  				Review review = new Review();
  				
  				review.setAuthor	(rs.getString(2));
  				review.setProductID	(rs.getInt(3));
  				review.setReview	(rs.getString(4));
  				review.setRating	(rs.getFloat(5));
  				
  				reviews.add(review);
  			} 
  	    	 
  	    	 con.close();
     	 } catch (Exception ex) {
     		 ex.printStackTrace();
     	 }
    	 
    	 return reviews;
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
 	    	 
 	    	 con.close();
    	 } catch (Exception ex) {
    		 ex.printStackTrace();
    		 
    		 return false;
    	 }
    	 
    	 return true;
     }
     
     public static int getAccountType(String uname) {
    	 Connection con;
    	 int accountType = -1;
    	 
    	 try {
    		Class.forName("com.mysql.jdbc.Driver");
 			con = DriverManager.getConnection
                     ("jdbc:mysql://localhost:" + port + "/" + dbname, username, password);
 			
 			PreparedStatement ps =con.prepareStatement
 	 				("SELECT acctype FROM account WHERE user = ?;");
 			
 			ps.setString(1, uname);
 			
 			ResultSet rs = ps.executeQuery();
 			
 			if(rs.next()) {
 				accountType = rs.getInt(1);
 			} else {
 				System.out.println("User not found.");
 				
 				return accountType;
 			}
 	    	 
 	    	con.close();
    	 } catch (Exception ex) {
    		 ex.printStackTrace();
    	 }
    	 
    	 return accountType;
     }
     
     public static ArrayList<Product> getProducts(int type) {
    	 Connection con;
    	 ArrayList<Product> products = new ArrayList<Product>();
    	 
    	 try {
     		Class.forName("com.mysql.jdbc.Driver");
  			con = DriverManager.getConnection
                      ("jdbc:mysql://localhost:" + port + "/" + dbname, username, password);
  			
  			PreparedStatement ps;
  			
  			if(type != 0) {
  				ps =con.prepareStatement
  	 				("SELECT * FROM products WHERE type = ? AND disabled = 0;");
  			
  				ps.setInt(1, type);
  			}else {
  				ps =con.prepareStatement
  	 				("SELECT * FROM products WHERE disabled = 0;;");
  			}
  			
  			ResultSet rs = ps.executeQuery();
  			
  			while(rs.next()) {
  				Product product = new Product();
  				
  				product.setProductID(rs.getInt(1));
  				product.setName(rs.getString(2));
  				product.setType(rs.getInt(3));
  				product.setDescription(rs.getString(4));
  				product.setPrice(rs.getFloat(5));
  				product.setImagePath(rs.getString(6));
  				
  				products.add(product);
  			} 
  	    	 
  	    	 con.close();
     	 } catch (Exception ex) {
     		 ex.printStackTrace();
     	 }
    	 
    	 return products;
    	 
     }
     
     public static ArrayList<Product> searchProduct(String input) {
    	 Connection con;
    	 ArrayList<Product> products = new ArrayList<Product>();
    	 
    	 try {
     		Class.forName("com.mysql.jdbc.Driver");
  			con = DriverManager.getConnection
                      ("jdbc:mysql://localhost:" + port + "/" + dbname, username, password);
  			
  			PreparedStatement ps;
  			
  			String like = "%" + input + "%";
  			
  			//edit for security o3o
  			if(InputChecker.checkInput(input)) {
  				ps =con.prepareStatement
  	 				("SELECT * FROM products WHERE name LIKE ? AND disabled = 0;");
  			
  				ps.setString(1, like);
  			}else {
  				ps =con.prepareStatement
  	 				("SELECT * FROM products WHERE disabled = 0;");
  			}
  			
  			ResultSet rs = ps.executeQuery();
  			
  			while(rs.next()) {
  				Product product = new Product();
  				
  				product.setProductID(rs.getInt(1));
  				product.setName(rs.getString(2));
  				product.setType(rs.getInt(3));
  				product.setDescription(rs.getString(4));
  				product.setPrice(rs.getFloat(5));
  				product.setImagePath(rs.getString(6));
  				
  				products.add(product);
  			} 
  	    	 
  	    	 con.close();
     	 } catch (Exception ex) {
     		 ex.printStackTrace();
     	 }
    	 
    	 return products;
     }
     
     public static ArrayList<Product> getProductsSorted(int sort) {
    	 //SORT 1 - Name, 2 - Type, 3 - Sales DESC, 4 - Sales ASC, 5 - Newest
    	 
    	 Connection con;
    	 ArrayList<Product> products = new ArrayList<Product>();
    	 
    	 try {
     		Class.forName("com.mysql.jdbc.Driver");
  			con = DriverManager.getConnection
                      ("jdbc:mysql://localhost:" + port + "/" + dbname, username, password);
  			
  			PreparedStatement ps;
  			
  			if(sort == 1)
  				ps =con.prepareStatement
  					("SELECT * FROM products, sales WHERE products.id = sales.productID ORDER BY name;");
  			else if(sort == 2)
  				ps =con.prepareStatement
					("SELECT * FROM products, sales WHERE products.id = sales.productID ORDER BY type;");
  			else if(sort == 3)
  				ps =con.prepareStatement
					("SELECT * FROM products, sales WHERE products.id = sales.productID ORDER BY amountSold DESC;");
  			else if(sort == 4)
  				ps =con.prepareStatement
					("SELECT * FROM products, sales WHERE products.id = sales.productID ORDER BY amountSold ASC;");
  			else if(sort == 5)
  				ps =con.prepareStatement
					("SELECT * FROM products, sales WHERE products.id = sales.productID ORDER BY id DESC;");
  			else 
  				ps =con.prepareStatement
					("SELECT * FROM products, sales WHERE products.id = sales.productID;");
  				
  			
  			ResultSet rs = ps.executeQuery();
  			
  			while(rs.next()) {
  				Product product = new Product();
  				
  				product.setProductID(rs.getInt(1));
  				product.setName(rs.getString(2));
  				product.setType(rs.getInt(3));
  				product.setDescription(rs.getString(4));
  				product.setPrice(rs.getFloat(5));
  				product.setImagePath(rs.getString(6));
  				product.setSales(rs.getInt(8));
  				
  				products.add(product);
  			} 
  	    	 
  	    	 con.close();
     	 } catch (Exception ex) {
     		 ex.printStackTrace();
     	 }
    	 
    	 return products;
    	 
     }
     
     public static ArrayList<Account> getAllAccounts() {

    	 Connection con;
    	 ArrayList<Account> accounts = new ArrayList<Account>();
    	 
    	 try {
     		Class.forName("com.mysql.jdbc.Driver");
  			con = DriverManager.getConnection
                      ("jdbc:mysql://localhost:" + port + "/" + dbname, username, password);
  			
  			PreparedStatement ps;
  			
  			ps =con.prepareStatement
  	 				("SELECT user, acctype, locked FROM account WHERE type != 4;");
  			
  			ResultSet rs = ps.executeQuery();
  			
  			while(rs.next()) {
  				Account account = new Account();
  				
  				account.setUsername(rs.getString(1));
  				account.setAccountType(rs.getInt(2));
  				account.setLocked(rs.getInt(3));
  				
  				accounts.add(account);
  			} 
  	    	 
  	    	 con.close();
     	 } catch (Exception ex) {
     		 ex.printStackTrace();
     	 }
    	 
    	 return accounts;
     }
     
     public static ArrayList<PurchaseLog> getPurchaseLogs(int accountID){

    	 Connection con;
    	 ArrayList<PurchaseLog> purchases = new ArrayList<PurchaseLog>();
    	 
    	 try {
     		Class.forName("com.mysql.jdbc.Driver");
  			con = DriverManager.getConnection
                      ("jdbc:mysql://localhost:" + port + "/" + dbname, username, password);
  			
  			PreparedStatement ps;
  			
  			ps =con.prepareStatement
  	 				("SELECT productid, timestamp FROM log_purchase WHERE accountid = accountID;");
  			
  			ResultSet rs = ps.executeQuery();
  			
  			while(rs.next()) {
  				PurchaseLog purchase = new PurchaseLog();
  				
  				purchase.setproductID(rs.getInt(1));
  				purchase.setTime(rs.getTimestamp(2));
  				
  				purchases.add(purchase);
  			} 
  	    	 
  	    	 con.close();
     	 } catch (Exception ex) {
     		 ex.printStackTrace();
     	 }
    	 
    	 return purchases;
     }
     
     public static boolean lockAccount(int accountID) {
    	 Connection con;
    	 
    	 try {
    		Class.forName("com.mysql.jdbc.Driver");
 			con = DriverManager.getConnection
                     ("jdbc:mysql://localhost:" + port + "/" + dbname, username, password);
 			PreparedStatement ps = con.prepareStatement
 				("UPDATE account SET locked = 0 WHERE id = ?");
 			
 			ps.setInt(1, accountID);
			
			ps.executeUpdate(); //for updates
 			//ResultSet rs = ps.executeQuery(); for queries
			
			con.close();
    	 } catch (Exception ex) {
    		 ex.printStackTrace();
    		 
    		 return false;
    	 }
    	 
    	 return true;
    	 
     }
     
     public static boolean unlockAccount(int accountID) {
    	 Connection con;
    	 
    	 try {
    		Class.forName("com.mysql.jdbc.Driver");
 			con = DriverManager.getConnection
                     ("jdbc:mysql://localhost:" + port + "/" + dbname, username, password);
 			PreparedStatement ps = con.prepareStatement
 				("UPDATE account SET locked = 1 WHERE id = ?");
 			
 			ps.setInt(1, accountID);
			
			ps.executeUpdate(); //for updates
 			//ResultSet rs = ps.executeQuery(); for queries
			
			con.close();
    	 } catch (Exception ex) {
    		 ex.printStackTrace();
    		 
    		 return false;
    	 }
    	 
    	 return true;
    	 
     }

     public static boolean createSaleLog(SaleLog log) {Connection con;
	 
		 try {
			Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection
	                 ("jdbc:mysql://localhost:" + port + "/" + dbname, username, password);
				PreparedStatement ps = con.prepareStatement
					("INSERT INTO log_sale"
							+ "(productid, timestamp) VALUES"
						    + "(?,?)");
				
			Object timestamp = log.getTime();
				
			ps.setInt		(1, log.getProductID());
			ps.setObject	(2, timestamp);
			
			ps.executeUpdate();
			
			con.close();
		 } catch (Exception ex) {
			 ex.printStackTrace();
			 
			 return false;
		 }
		 
		 return true;
    	 
     }
     
     public static boolean createPurchaseLog(PurchaseLog log) {Connection con;
		 
		 try {
			Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection
	                 ("jdbc:mysql://localhost:" + port + "/" + dbname, username, password);
				PreparedStatement ps = con.prepareStatement
					("INSERT INTO log_purchase"
							+ "(accountid, productid, timestamp) VALUES"
						    + "(?,?,?)");
				
			Object timestamp = log.getTime();

			ps.setInt		(1, log.getCustomerID());
			ps.setInt		(2, log.getproductID());
			ps.setObject	(3, timestamp);
			
			ps.executeUpdate();
	
			con.close();
		 } catch (Exception ex) {
			 ex.printStackTrace();
			 
			 return false;
		 }
		 
		 return true;
		 
	 }
     
     public static Product getProduct(int id) {
    	 Connection con;
    	 Product product = new Product();
    	 
    	 try {
     		Class.forName("com.mysql.jdbc.Driver");
  			con = DriverManager.getConnection
                      ("jdbc:mysql://localhost:" + port + "/" + dbname, username, password);
  			
  			PreparedStatement ps =con.prepareStatement
  	 				("SELECT * FROM products WHERE id = ?;");
  			
  			ps.setInt(1, id);
  			
  			ResultSet rs = ps.executeQuery();
  			
  			if(rs.next()) {
  				product = new Product();
  				
  				product.setProductID(rs.getInt(1));
  				product.setName(rs.getString(2));
  				product.setType(rs.getInt(3));
  				product.setDescription(rs.getString(4));
  				product.setPrice(rs.getFloat(5));
  				product.setImagePath(rs.getString(6));
  			} 
 	    	 
 	    	 con.close();
     	 } catch (Exception ex) {
     		 ex.printStackTrace();
     	 }
    	 
    	 return product;
    	 
     }
}