package models;

public class CartItem {
	private int amount, productManagerID, productID;

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getProductManagerID() {
		return productManagerID;
	}

	public void setProductManagerID(int productManagerID) {
		this.productManagerID = productManagerID;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}
}
