package models;

public class CreditCardInfo {
	private int customerID, ccNumber, cvcNumber, expDateMonth, expDateYear;

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public int getCcNumber() {
		return ccNumber;
	}

	public void setCcNumber(int ccNumber) {
		this.ccNumber = ccNumber;
	}

	public int getCvcNumber() {
		return cvcNumber;
	}

	public void setCvcNumber(int cvcNumber) {
		this.cvcNumber = cvcNumber;
	}

	public int getExpDateMonth() {
		return expDateMonth;
	}

	public void setExpDateMonth(int expDateMonth) {
		this.expDateMonth = expDateMonth;
	}

	public int getExpDateYear() {
		return expDateYear;
	}

	public void setExpDateYear(int expDateYear) {
		this.expDateYear = expDateYear;
	}
	
	
}
