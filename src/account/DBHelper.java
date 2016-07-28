package account;

import java.sql.*;
import java.util.ArrayList;

public class DBHelper
 {	
	private final static String password = "abcd1234";
	private final static String username = "root";
	private final static String dbname = "scrd";
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
     
     public static boolean createTask(String title, String details, String user) {
    	 Connection con;
    	 
    	 try {
    		 Class.forName("com.mysql.jdbc.Driver");
 			con = DriverManager.getConnection
                     ("jdbc:mysql://localhost:" + port + "/" + dbname, username, password);
 			PreparedStatement ps =con.prepareStatement
 	 				("SELECT id FROM user_account WHERE user_name = ?;");
 			
 			ps.setString(1, user);
 			
 			ResultSet rs = ps.executeQuery();
 			
 			int id = 0;
 			
 			if(rs.next()) 
 				id = rs.getInt(1);
 			
 			ps =con.prepareStatement
 				("INSERT INTO todo_list"
 						+ "(title, details, status, user_id) VALUES"
 					    + "(?,?,?,?)");

			ps.setString(1, title);
			ps.setString(2, details);
			ps.setInt(3, 0);
			ps.setInt(4, id);
			
			
			ps.executeUpdate();
    	 } catch (Exception ex) {
    		 ex.printStackTrace();
    		 
    		 return false;
    	 }
    	 
    	 return true;
     }
     
     public static boolean editTask(String title, String details, String user) {
    	 
    	 
    	 return true;
     }
     
     public static boolean deleteTask(int id) {
    	 Connection con;
    	 
		 try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection
                 ("jdbc:mysql://localhost:" + port + "/" + dbname, username, password);
    		PreparedStatement ps = con.prepareStatement
	 				("DELETE FROM todo_list WHERE id = ?;");
    		
    		ps.setInt(1, id);
    		
    		ps.executeUpdate();
		 }catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
    	 
    	 return true;
     }
     
     public static ArrayList<Task> getTasks(String user) {
    	 Connection con;
    	 ArrayList<Task> tasks = new ArrayList<Task>();
    	 
    	 try {
    		 Class.forName("com.mysql.jdbc.Driver");
 			con = DriverManager.getConnection
                     ("jdbc:mysql://localhost:" + port + "/" + dbname, username, password);
 			
 			PreparedStatement ps =con.prepareStatement
 	 				("SELECT id FROM user_account WHERE user_name = ?;");
 			
 			ps.setString(1, user);
 			
 			ResultSet rs = ps.executeQuery();
 			
 			int id = 0;
 			
 			if(rs.next()) {
 				id = rs.getInt(1);
 			} else {
 				return tasks;
 			}
 			
 			
 			ps = con.prepareStatement
 				("SELECT title, details, status, id FROM todo_list WHERE user_id = " + id + ";");
			
			rs = ps.executeQuery();
			
			tasks = new ArrayList<Task>();

			while(rs.next()){
				Task task = new Task();
				
				task.setTitle(rs.getString(1));
				task.setDescription(rs.getString(2));
				
				boolean status = false;
				
				if(rs.getInt(3) == 1)
					status = true;
				
				task.setID(rs.getInt(4));
				
				task.setDone(status);
				
				tasks.add(task);
			}
    	 } catch (Exception ex) {
    		 ex.printStackTrace();
    	 }
    	 
    	 return tasks;
     }
}