package communication;

public class Id {
	private long id;
	
	public Id(long id) throws IdException{
		if(id == 0L)
			throw new IdException("0 is not a valid id");
		this.id = id;
	}
	
	public long get() {
		return id;
	}
	
	public String toString() {
		return Long.toString(id);
	}
	
}
