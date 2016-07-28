package account;

public class Task {
	private String title;
	private String description;
	private boolean done;
	private int id;
	
	public Task (String title, String description) {
		this.title = title;
		this.description = description;
		done = false;
	}
	
	public Task () {
		
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setDone(boolean done) {
		this.done = done;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public boolean getDone() {
		return done;
	}
	
	public int getID() {
		return id;
	}
}
