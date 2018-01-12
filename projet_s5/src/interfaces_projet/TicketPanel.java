package interfaces_projet;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import com.mysql.fabric.xmlrpc.Client;

import client.ClientDB;
import client.CommunicatorClient;
import packet.Commands;
import packet.Connect;
import packet.Packet;
import packet.User;

public class TicketPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private long id;
	private String intitule;
	private UserPanel creator;
	private GroupPanel group;
	private List<Long> messages;
	
	private int nbMessage;
	private GridLayout layout;
	
	public TicketPanel(long id, String intitule, UserPanel creator, GroupPanel group, List<Long> messages) {
		super();
		this.id = id;
		this.creator = creator;
		this.group = group;
		this.messages = messages;
		this.intitule = intitule;
		nbMessage = 0;
		layout = new GridLayout(nbMessage, 1);
		initComponent();
	}
	
	private void initComponent() {
		/* données test TODO: delete test*/
		setLayout(layout);
		setBorder(new LineBorder(Color.BLACK));
		setBackground(Color.LIGHT_GRAY);

		/*UserPanel hugo =  new UserPanel(0, "ROUSSEL", "Hugo", "CAT", null, null);
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
		MessagePanel m;
		m = new MessagePanel(0, 0, creator, "Ceci est un treeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"+
				"eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeees looooo"+
				"oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo"+
				"ong message");
		add(m);
		for(int i = 0; i < 20; i++) {
			m = new MessagePanel(0, 0, creator, "Message "+i);
			this.add(m);
		}*/
	}
	
	public void loadMessage() {
		for(long messageId : messages) {
			layout.setRows(++nbMessage);
			add(ClientDB.findMessage(messageId));
		}
	}
	
	public static void main(String[] args) {
		/* test qui marche pas
		CommunicatorClient comm = new CommunicatorClient("localhost", 3636);
		User user = null;
		try {			
			//log in
			Connect connect = new Connect((byte)(Commands.SEND | Commands.CONNECT), "hugo.roussel@univ-tlse3.fr", "carrotte");
			comm.send(connect);
			Packet connectResp = comm.receive();
			if(connectResp.getCommand() == (Commands.FAIL | Commands.CONNECT)) {
				System.err.println("Username or password invalid");
				System.exit(1);
			} else {
				user = (User)connectResp;
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		UserPanel hugo =  ClientDB.findUser(user.getId());
		JFrame j = new JFrame("test");
		JScrollPane s = new JScrollPane();
		j.add(s);
		TicketPanel t = new TicketPanel(0, "int", hugo, new GroupPanel(0, "group"), null);
		s.setViewportView(t);
		j.setVisible(true);
		j.pack();
		*/
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
