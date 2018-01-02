package packet;

import java.io.Serializable;

public abstract class Content implements Serializable{
	private static final long serialVersionUID = -3334205753488398387L;
	protected long id;
	
	public long getId() {
		return id;
	}
	
	public Content(long id) {
		this.id = id;
	}
	
	public Content() {
		id = 0;
	}
	
	@Override
	public String toString() {
		return "{Id : "+id+", ";
	}

}
