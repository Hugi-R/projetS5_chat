package interfaces_projet;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import client.ClientDB;

public class TicketPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private long id;
	private String intitule;
	private UserPanel creator;
	private GroupPanel group;
	private List<Long> messages;
	
	public TicketPanel(long id, String intitule, UserPanel creator, GroupPanel group, List<Long> messages) {
		super();
		this.id = id;
		this.creator = creator;
		this.group = group;
		this.messages = messages;
		this.intitule = intitule;
		
		setLayout(new GridLayout(0, 1));
		setBorder(new LineBorder(Color.BLACK));
		setBackground(Color.LIGHT_GRAY);

		Iterable<Long> iter = this.messages;
		for(Long message : iter) {
			this.add(ClientDB.findMessage(message));
		}
	}


	public long getId() {
		return id;
	}

	public String getName() {
		return intitule;
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


	@Override
	public String toString() {
		return "TicketPanel [id=" + id + ", creator=" + creator.getName() + ", group=" + group + ", intitule=" + intitule + ", messages=" + messages + "]";
	}
	
	


}
