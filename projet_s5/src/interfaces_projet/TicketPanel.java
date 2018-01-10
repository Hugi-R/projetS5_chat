package interfaces_projet;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

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
		
		/* données test TODO: delete test*/
		setLayout(new GridLayout(0, 1));
		setBorder(new LineBorder(Color.BLACK));
		setBackground(Color.LIGHT_GRAY);

		UserPanel hugo =  new UserPanel(0, "ROUSSEL", "Hugo", "CAT", null, null);
		MessagePanel m = new MessagePanel(0, 0, hugo, "Message1");
		add(m);
		m = new MessagePanel(0, 0, hugo, "Ceci est un treeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"+
			"eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeees looooo"+
			"oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo"+
			"ong message");
		add(m);
		m = new MessagePanel(0, 0, hugo, "Message3");
		add(m);
		m = new MessagePanel(0, 0, hugo, "Message4");
		add(m);
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
		return "TicketPanel [id=" + id + ", creator=" + creator.getName() + ", group=" + group + ", messages=" + messages + "]";
	}
	
	


}
