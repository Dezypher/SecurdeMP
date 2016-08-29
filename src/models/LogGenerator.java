package models;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogGenerator {
	/*
    private static Logger logger = Logger.getLogger(LogGenerator.class.getName());
    
    {
    	try {
			Logger.getLogger("").addHandler(new FileHandler("talaria.log"));
			Logger.getLogger("com.talaria").setLevel(Level.FINEST);
		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	*/
	
	public static final String logFile = "/Users/Solomon/Documents/talarialogs/talaria.log";
	
	public static Timestamp getNow() {
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		return new Timestamp(now.getTime());
	}
	
	public static void logEntry(String entry) {
	    Logger logger = Logger.getLogger("talaria");  
	    FileHandler fh;  

        try {
			fh = new FileHandler(logFile, true);
	        logger.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);  
	        
	        logger.info(entry);
	        
	        fh.close();
		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	public static Log generateLog(String details) {
		Log log = new Log();
		log.setTime(getNow());
		log.setDetails(details);
		
		logEntry("LOG: " + details);
		
		return log;
	}
	
	public static PurchaseLog generatePurchaseLog(String details, int productID, int customerID, float totalPrice) {
		PurchaseLog log = new PurchaseLog();
		log.setTime(getNow());
		log.setDetails(details);
		log.setproductID(productID);
		log.setCustomerID(customerID);
		log.setTotalPrice(totalPrice);
		
		logEntry("<PURCHASE> product: " + productID + " customer: " + customerID);
		
		return log;
	}
	
	public static SaleLog generateSaleLog(String details, int productID, 
			int customerID, float price) {
		SaleLog log = new SaleLog();
		log.setTime(getNow());
		log.setDetails(details);
		log.setProductID(productID);
		log.setCustomerID(customerID);
		log.setPrice(price);
		logEntry("<SALE> product: " + productID + " customer: " + customerID + " price: " + price);
		return log;
	}
	
	public static AddProductLog generateAddProductLog(String details, int userID, 
			int productID) {
		AddProductLog log = new AddProductLog();
		log.setTime(getNow());
		log.setDetails(details);
		log.setUserID(userID);
		log.setProductID(productID);
		logEntry("<ADDPRODUCT> product: " + details + " userID: " + userID);
		return log;
	}
	
	public static EditProductLog generateEditProductLog(String details, int userID, int productID) {
		EditProductLog log = new EditProductLog();
		log.setTime(getNow());
		log.setDetails(details);
		log.setUserID(userID);
		log.setProductID(productID);
		logEntry("<EDITPRODUCT> product: " + details + " userID: " + userID);
		return log;
	}
	
	public static AccessLog generateAccessLog(String details, int userID) {
		AccessLog log = new AccessLog();
		log.setTime(getNow());
		log.setDetails(details);
		log.setUserID(userID);
		return log;
	}
	
	public static LoginLog generateLoginLog(String details, int userID) {
		LoginLog log = new LoginLog();
		log.setTime(getNow());
		log.setDetails(details);
		log.setAccountID(userID);
		logEntry("<LOGIN> username: " + details);
		return log;
	}
	
	public static LogoutLog generateLogoutLog(String details, int userID) {
		LogoutLog log = new LogoutLog();
		log.setTime(getNow());
		log.setDetails(details);
		log.setAccountID(userID);
		logEntry("<LOGOUT> username: " + details);
		return log;
	}
	
}
