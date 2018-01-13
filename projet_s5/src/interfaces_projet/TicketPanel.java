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
	
	private int nbMessage;
	private GridLayout layout;
	
	private static final int MIN_ROW_LAYOUT = 8;
	
	public TicketPanel(long id, String intitule, UserPanel creator, GroupPanel group, List<Long> messages) {
		super();
		this.id = id;
		this.creator = creator;
		this.group = group;
		this.messages = messages;
		this.intitule = intitule;
		nbMessage = 0;
		initComponent();
	}
	
	private void updateGridLayout() {
		if(nbMessage > MIN_ROW_LAYOUT)
			layout.setRows(nbMessage);
	}
	
	private void initComponent() {
		layout = new GridLayout(MIN_ROW_LAYOUT, 1);
		layout.setVgap(10);
		setLayout(layout);
		setBorder(new LineBorder(Color.BLACK));
		setBackground(Color.LIGHT_GRAY);
	}
	
	public void loadMessage() {
		for(long messageId : messages) {
			nbMessage++;
			updateGridLayout();
			add(ClientDB.findMessage(messageId));
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
		return "TicketPanel [id=" + id + ", creator=" + creator.getName() + ", group=" + group + ", messages=" + messages + "]";
	}
	
	


}
