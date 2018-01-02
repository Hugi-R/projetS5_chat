package content;

import java.io.Serializable;

public abstract class Content implements Serializable{
	private static final long serialVersionUID = -3334205753488398387L;
	protected Id id;
	
	public Id getId() {
		return id;
	}
	
	public Content(Id id) {
		this.id = id;
	}
	
	public Content() {
		id = null;
	}
	
	@Override
	public String toString() {
		return "{Id : "+id.get()+", ";
	}

}
