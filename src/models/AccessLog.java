package models;

public class AccessLog extends Log {
	public static final int TYPE_LOGIN = 1, TYPE_LOGOUT = 2;
	private int userID;
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
}
