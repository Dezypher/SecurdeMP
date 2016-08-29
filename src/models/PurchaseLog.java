package models;

public class PurchaseLog extends Log {
	private int customerID;
	private int productID;
	private String productName;
	private float totalPrice;
	
	
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public int getproductID() {
		return productID;
	}
	public void setproductID(int productID) {
		this.productID = productID;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
}
