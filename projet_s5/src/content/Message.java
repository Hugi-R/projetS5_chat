package content;

import com.eclipsesource.json.JsonObject;

public class Message extends Content {
	private User user;
	private Ticket ticket;
	private long time;
	private String textMessage;
	
	public Message(Id id, User user, Ticket ticket, long time, String textMessage) {
		super(id);
		this.textMessage = textMessage;
		this.user = user;
		this.ticket = ticket;
		this.time = time;
	}
	
	public Message(JsonObject jobj) throws IdException, ContentException {
		super();
		fromJson(jobj);
	}

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
		json.set("type", ContentType.MESSAGE);
		json.set("id", getId().get());
		json.set("user", user.getId().get());
		json.set("ticket", ticket.getId().get());
		json.set("time", time);
		json.set("message", textMessage);
		return json.toString();
	}
	
	@Override
	public void fromJson(JsonObject json) throws IdException, ContentException {
		if(!ContentType.MESSAGE.equals(json.getString("type", null)))
			throw new ContentException("Invalid message : type is not message");
		
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
			throw new ContentException("Invalid message : no text");
		
	}
	
	@Override
	public String toString() {
		return super.toString()+"User : "+user+", Ticket : "+ticket+", Time : "+time+", Text : "+textMessage+" }";
	}
	
	

}
