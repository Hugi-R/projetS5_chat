package packet;

public class Message extends Content {
	private static final long serialVersionUID = -123551427390012409L;
	private long user;
	private long ticket;
	private long time;
	private String textMessage;
	
	public Message(byte command, long id, long user, long ticket, long time, String textMessage) {
		super(command, id);
		this.textMessage = textMessage;
		this.user = user;
		this.ticket = ticket;
		this.time = time;
	}

	public long getUser() {
		return user;
	}

	public long getTicket() {
		return ticket;
	}

	public String getTextMessage() {
		return textMessage;
	}

	public long getTime() {
		return time;
	}
	
	@Override
	public String toString() {
		return super.toString()+"User : "+user+", Ticket : "+ticket+", Time : "+time+", Text : "+textMessage+" }";
	}
	
	

}
