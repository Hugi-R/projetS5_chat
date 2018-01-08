package interfaces_projet;

import java.util.List;

import javax.swing.JPanel;

public class TicketPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private long id;
	private String nom;
	private UserPanel creator;
	private GroupPanel group;
	private List<MessagePanel> messages;
	
	
	public TicketPanel(long id, UserPanel creator, String intitule, GroupPanel group, List<MessagePanel> messages) {
		super();
		this.id = id;
		this.creator = creator;
		this.group = group;
		this.messages = messages;
		this.nom = intitule;
	}


	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public UserPanel getCreator() {
		return creator;
	}


	public GroupPanel getGroup() {
		return group;
	}


	public List<MessagePanel> getMessages() {
		return messages;
	}


	@Override
	public String toString() {
		return "TicketPanel [id=" + id + ", creator=" + creator + ", group=" + group + ", messages=" + messages + "]";
	}
	
	


}
