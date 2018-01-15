package packet;

public class Message extends Content {
	private static final long serialVersionUID = -123551427390012409L;
	private long user;
	private long ticket;
	private long time;
	private byte status;
	private String textMessage;
	
	public Message(byte command, long id, long user, long ticket, long time, byte status, String textMessage) {
		super(command, id);
		this.textMessage = textMessage;
		this.user = user;
		this.ticket = ticket;
		this.time = time;
		this.status = status;
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
	
	public byte getStatus() {
		return status;
	}
	
	@Override
	public String toString() {
		return super.toString()+"User : "+user+", Ticket : "+ticket+", Time : "+time+", status ="+status+" Text : "+textMessage+" }";
	}
	
	

}
