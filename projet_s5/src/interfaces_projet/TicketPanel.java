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
	
	private static final int MIN_ROW_LAYOUT = 4;
	
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
			layout.setRows(nbMessage+1);
		layout.setColumns(1);
	}
	
	private void initComponent() {
		layout = new GridLayout(MIN_ROW_LAYOUT, 1);
		layout.setVgap(10);
		setLayout(layout);
		setBorder(new LineBorder(Color.BLACK));
		setBackground(Color.LIGHT_GRAY);
	}
	
	public void loadMessage() {
		int i = 0;
		nbMessage = 0;
		for(long messageId : messages) {
			nbMessage++;
			updateGridLayout();
			add(ClientDB.findMessage(messageId), i++);
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
		return intitule + " - " + group.getName();
	}
	
	public void addMessage(MessagePanel message) {
		messages.add(message.getId());
		loadMessage();
		//TODO find better
	}
	
	


}
