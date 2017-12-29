package communication;

public interface Jsonify {
	
	/*
	 * Json structure : 
	 * Requiered :
	 * Type : "name"
	 * Id   : int
	 * 
	 * rest is optional / type dependent
	 * 
	 */
	
	public String toJson();
	
	public void fromJson(String json) throws ContentException;

}
