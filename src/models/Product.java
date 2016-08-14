package models;

public class Product {
	public static int TYPE_BOOTS = 1, TYPE_SHOES = 2, TYPE_SANDALS = 3, TYPE_SLIPPERS = 4;
	private int productID, type;
	private float price;
	private String name, description, imagepath;
	
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImagePath() {
		return imagepath;
	}
	public void setImagePath(String imagepath) {
		this.imagepath = imagepath;
	}
}
