package communication;

public class Id {
	private long id;
	
	public Id(long id) {
		this.id = id;
	}
	
	public long get() {
		return id;
	}
	
	public String toString() {
		return Long.toString(id);
	}
	
}
