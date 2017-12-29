package content;

import communication.ContentException;
import communication.Id;
import communication.IdException;

public abstract class Content {
	protected Id id;
	
	public Id getId() {
		return id;
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
	
	public abstract void fromJson(String json) throws ContentException, IdException;

}
