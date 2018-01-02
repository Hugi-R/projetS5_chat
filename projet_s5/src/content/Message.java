package content;

public class Message extends Content {
	private static final long serialVersionUID = -123551427390012409L;
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
	public String toString() {
		return super.toString()+"User : "+user+", Ticket : "+ticket+", Time : "+time+", Text : "+textMessage+" }";
	}
	
	

}
