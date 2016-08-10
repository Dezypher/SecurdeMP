package account;

public class Account {
	public static final int TYPE_CUSTOMER 			= 1;
	public static final int TYPE_PRODUCTMANAGER 	= 2;
	public static final int TYPE_ACCOUNTINGMANAGER 	= 3;
	public static final int TYPE_ADMINISTRATOR 		= 4;
	
	public int id;
	public String username;
	public int accType;
	public String email;
	public String fname;
	public String mname;
	public String lname;
	
	public Account(int id, String username, int accType, String email, String fname, String mname, String lname) {
		this.id = id;
		this.username = username;
		this.accType = accType;
		this.email = email;
		this.fname = fname;
		this.mname = mname;
		this.lname = lname;
	}
	
	
}
