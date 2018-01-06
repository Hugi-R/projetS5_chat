package client;

import java.io.IOException;
import java.util.HashMap;

import javax.swing.JPanel;

import interfaces_projet.MessagePanel;
import interfaces_projet.UserPanel;
import packet.Commands;
import packet.ContentType;
import packet.Message;
import packet.Packet;
import packet.Request;
import packet.User;
import utils.Id;

public class ClientDB {
	/*
	 * Store data for user
	 * If data not present, retrieve it from server
	 */
	//private static List<> groups;
	//private static TicketList tickets;
	private static HashMap<Long, UserPanel> userList = new HashMap<>();
	private static HashMap<Long, MessagePanel> messageList = new HashMap<>();
	
	private ClientDB() {
		// no constructor
	}
	
	public static JPanel find(long id){
		JPanel ret = null;
		switch(Id.type(id)) {
		case ContentType.MESSAGE :
			ret = findMessage(id);
			break;
		case ContentType.USER :
			//TODO
			break;
		case ContentType.TICKET :
			//TODO
			break;
		case ContentType.GROUP :
			//TODO
			break;
		default :
			System.err.println("ClientDB find : unknow ContentType");
				
		}
		return ret;
		
	}
	
	public static MessagePanel findMessage(long id) {
		MessagePanel ret = null;
		if((ret = messageList.get(id)) == null) {
			Message m = (Message) retrieve(id);
			if(m != null) {
				MessagePanel message = new MessagePanel(m.getId(), m.getTime(), findUser(m.getUser()), m.getTextMessage());
				messageList.put(m.getId(), message);
				ret = message;
			}
		}
		return ret;
	}
	
	public static UserPanel findUser(long id) {
		UserPanel ret = null;
		if((ret = userList.get(id)) == null) {
			User u = (User) retrieve(id);
			if(u != null) {
				//TODO groupList and ticketList
				UserPanel user = new UserPanel(u.getId(), u.getNom(), u.getPrenom(), u.getCategory(), null, null);
				userList.put(u.getId(), user);
				ret = user;
			}
		}
		return ret;
	}
	
	private static Packet retrieve(long id) {
		Packet ret = null;
		try {
			MainClient.comm.send(new Request(Commands.RETRIEVE, id));
			ret = MainClient.comm.receive();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if((ret.getCommand() & Commands.FAIL ) == Commands.FAIL)
			ret = null;
		return ret;
	}

}
