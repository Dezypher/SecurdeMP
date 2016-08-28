package models;

public class LoginLog extends Log{
	private int accountID;
	
	public int getAccountID() {
		return accountID;
	}
	
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
}