package packet;

import java.util.List;

public class Ticket extends Content{
	private static final long serialVersionUID = -7044448245749898134L;
	private List<Message> messageList;
	
	public Ticket(byte command, long id) {
		super(command, id);
	}
	
	public List<Message> getMessageList() {
		return messageList;
	}

	@Override
	public String toString() {
		return super.toString()+" TODO }";
	}

}
