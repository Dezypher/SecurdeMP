package models;

import java.util.ArrayList;

public class Cart {
	private int cartID, customerID;
	private ArrayList<CartItem> products;

	public int getCartID() {
		return cartID;
	}

	public void setCartID(int cartID) {
		this.cartID = cartID;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public ArrayList<CartItem> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<CartItem> products) {
		this.products = products;
	}
	
	public CartItem getProduct(int index) {
		return products.get(index);
	}
	
	public void setProduct(CartItem product, int index) {
		this.products.set(index, product);
	}
	
	public void addProduct(CartItem product) {
		this.products.add(product);
	}
}
