package content;

import com.eclipsesource.json.JsonObject;

public abstract class Content {
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
	
	/*
	 * Json structure : 
	 * Requiered :
	 * Type : "name"
	 * Id   : int
	 * 
	 * rest is optional / type dependent
	 * 
	 */
	
	public abstract String toJson();
	
	public abstract void fromJson(JsonObject jobj) throws ContentException, IdException;
	
	@Override
	public String toString() {
		return "{Id : "+id.get()+", ";
	}

}
