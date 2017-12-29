package content;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;

import communication.ContentException;
import communication.Id;
import communication.Jsonify;

public class Message extends Content implements Jsonify {
	private String textMessage;
	private User writer;
	private Ticket parent;
	private long time;

	public String getMessage() {
		return textMessage;
	}

	public User getWriter() {
		return writer;
	}

	public Ticket getParent() {
		return parent;
	}
	
	public long getTime() {
		return time;
	}

	@Override
	public String toJson() {
		JsonObject json = new JsonObject();
		json.set("type", ContentType.MESSAGE.toString());
		json.set("id", getId().get());
		json.set("user", writer.getId().get());
		json.set("ticket", parent.getId().get());
		json.set("timestamp", time);
		json.set("message", textMessage);
		return json.toString();
	}

	@Override
	public void fromJson(String str) throws ContentException {
		JsonObject json = Json.parse(str).asObject();
		if(ContentType.MESSAGE.toString().equals(json.getString("type", null)))
			throw new ContentException("Invalid message");
		id = new Id(json.getLong("id", 0));
		if(id.get() == 0L)
			throw new ContentException("Invalid Id");
		//TODO finish for the rest
	}
	
	

}
