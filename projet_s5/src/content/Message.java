package content;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;

import communication.ContentException;
import communication.Id;
import communication.IdException;

public class Message extends Content {
	private String textMessage;
	private User user;
	private Ticket ticket;
	private long time;

	public String getMessage() {
		return textMessage;
	}

	public User getWriter() {
		return user;
	}

	public Ticket getParent() {
		return ticket;
	}
	
	public long getTime() {
		return time;
	}

	@Override
	public String toJson() {
		JsonObject json = new JsonObject();
		json.set("type", ContentType.MESSAGE.toString());
		json.set("id", getId().get());
		json.set("user", user.getId().get());
		json.set("ticket", ticket.getId().get());
		json.set("timestamp", time);
		json.set("message", textMessage);
		return json.toString();
	}

	@Override
	public void fromJson(String str) throws ContentException, IdException {
		JsonObject json = Json.parse(str).asObject();
		
		if(ContentType.MESSAGE.toString().equals(json.getString("type", null)))
			throw new ContentException("Invalid message");

		id = new Id(json.getLong("id", 0));
		
		Id userId = new Id(json.getLong("user", 0));
		user = UserList.find(userId);
		
		Id ticketId = new Id(json.getLong("ticket", 0));
		ticket = TicketList.find(ticketId);
		
		time = json.getLong("time", 0);
		if(time == 0L)
			throw new ContentException("Invalid time");
		
		textMessage = json.getString("message", "");
		if("".equals(textMessage)) 
			throw new ContentException("Invalid message");
	}
	
	

}
