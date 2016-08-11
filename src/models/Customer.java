package models;

public class Customer extends Account {
	private int billingID, shoppingID, cardID, creditCardInfoID;

	public int getBillingID() {
		return billingID;
	}

	public void setBillingID(int billingID) {
		this.billingID = billingID;
	}

	public int getShoppingID() {
		return shoppingID;
	}

	public void setShoppingID(int shoppingID) {
		this.shoppingID = shoppingID;
	}

	public int getCardID() {
		return cardID;
	}

	public void setCardID(int cardID) {
		this.cardID = cardID;
	}

	public int getCreditCardInfoID() {
		return creditCardInfoID;
	}

	public void setCreditCardInfoID(int creditCardInfoID) {
		this.creditCardInfoID = creditCardInfoID;
	}
}
