package interfaces_projet;

import java.util.List;

import javax.swing.JPanel;

public class TicketPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private long id;
	private UserPanel creator;
	private GroupPanel group;
	private List<Long> messages;
	
	
	public TicketPanel(long id, UserPanel creator, GroupPanel group, List<Long> messages) {
		super();
		this.id = id;
		this.creator = creator;
		this.group = group;
		this.messages = messages;
	}


	public long getId() {
		return id;
	}


	public UserPanel getCreator() {
		return creator;
	}


	public GroupPanel getGroup() {
		return group;
	}


	public List<Long> getMessages() {
		return messages;
	}
	
	


}
