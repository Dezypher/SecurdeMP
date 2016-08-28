package models;

import java.sql.Time;

public class LogGenerator {
	public static Log generateLog(Time time, String details) {
		Log log = new Log();
		log.setTime(time);
		log.setDetails(details);
		return log;
	}
	
	public static PurchaseLog generatePurchaseLog(Time time, String details, int customerID, 
			Cart cart, float totalPrice) {
		PurchaseLog log = new PurchaseLog();
		log.setTime(time);
		log.setDetails(details);
		log.setCustomerID(customerID);
		log.setCart(cart);
		log.setTotalPrice(totalPrice);
		return log;
	}
	
	public static SaleLog generateSaleLog(Time time, String details, int productID, 
			int customerID, float price) {
		SaleLog log = new SaleLog();
		log.setTime(time);
		log.setDetails(details);
		log.setProductID(productID);
		log.setCustomerID(customerID);
		log.setPrice(price);
		return log;
	}
	
	public static AddProductLog generateAddProductLog(Time time, String details, int userID, 
			int productID, int quantity) {
		AddProductLog log = new AddProductLog();
		log.setTime(time);
		log.setDetails(details);
		log.setUserID(userID);
		log.setProductID(productID);
		log.setQuantity(quantity);
		return log;
	}
	
	public static EditProductLog generateEditProductLog(Time time, String details, int userID, int productID) {
		EditProductLog log = new EditProductLog();
		log.setTime(time);
		log.setDetails(details);
		log.setUserID(userID);
		log.setProductID(productID);
		return log;
	}
	
	public static AccessLog generateAccessLog(Time time, String details, int userID) {
		AccessLog log = new AccessLog();
		log.setTime(time);
		log.setDetails(details);
		log.setUserID(userID);
		return log;
	}
	
}
