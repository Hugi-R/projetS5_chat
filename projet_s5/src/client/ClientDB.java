package client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JPanel;

import interfaces_projet.GroupPanel;
import interfaces_projet.MessagePanel;
import interfaces_projet.TicketPanel;
import interfaces_projet.UserPanel;
import packet.Commands;
import packet.Content;
import packet.ContentType;
import packet.Group;
import packet.Message;
import packet.Packet;
import packet.Request;
import packet.Ticket;
import packet.User;
import utils.Id;

public class ClientDB {
	/*
	 * Store data for user
	 * If data not present, retrieve it from server
	 */
	private static HashMap<Long, GroupPanel> groupList = new HashMap<>();
	private static HashMap<Long, TicketPanel> ticketList = new HashMap<>();
	private static HashMap<Long, UserPanel> userList = new HashMap<>();
	private static HashMap<Long, MessagePanel> messageList = new HashMap<>();
	
	private ClientDB() {
		// no constructor
	}
	
	public static void flush() {
		groupList.clear();
		ticketList.clear();
		userList.clear();
		messageList.clear();
	}
	
	public static void add(Content packet) {
		switch(Id.type(packet.getId())) {
		case ContentType.MESSAGE :
			Message m = (Message) packet;
			MessagePanel message = new MessagePanel(m.getId(), m.getTime(), findUser(m.getUser()), m.getStatus(), m.getTextMessage());
			messageList.put(m.getId(), message);
			break;
		case ContentType.USER :
			User u = (User) packet;
			List<TicketPanel> tickets = new ArrayList<>();
			if(u.getTicketList() != null) {
				for(long ticketId : u.getTicketList()) {
					tickets.add(findTicket(ticketId));
				}
			}
			List<GroupPanel> groups = new ArrayList<>();
			if(u.getGroupList() != null) {
				for(long groupId : u.getGroupList()) {
					groups.add(findGroup(groupId));
				}
			}
			UserPanel user = new UserPanel(u.getId(), u.getNom(), u.getPrenom(), u.getCategory(), tickets, groups);
			userList.put(u.getId(), user);
			break;
		case ContentType.TICKET :
			Ticket t = (Ticket) packet;
			TicketPanel ticket = new TicketPanel(t.getId(), t.getName(), findUser(t.getCreatorId()), findGroup(t.getGroupId()), t.getMessageList());
			ticketList.put(t.getId(), ticket);
			break;
		case ContentType.GROUP :
			Group g = (Group) packet;
			GroupPanel group = new GroupPanel(g.getId(), g.getName());
			groupList.put(g.getId(), group);
			break;
		default :
			System.err.println("ClientDB find : unknow ContentType");
				
		}
	}
	
	public static JPanel find(long id){
		JPanel ret = null;
		switch(Id.type(id)) {
		case ContentType.MESSAGE :
			ret = findMessage(id);
			break;
		case ContentType.USER :
			ret = findUser(id);
			break;
		case ContentType.TICKET :
			ret = findTicket(id);
			break;
		case ContentType.GROUP :
			ret = findGroup(id);
			break;
		default :
			System.err.println("ClientDB find : unknow ContentType");
				
		}
		return ret;
		
	}
	
	public static void update(Content c) {
		switch(Id.type(c.getId())) {
		case ContentType.MESSAGE :
			messageList.remove(c.getId());
			add(c);
			Message m = (Message) c;
			TicketPanel tp = findTicket(m.getTicket());
			tp.addMessage(findMessage(m.getId()));
			break;
		case ContentType.USER :
			userList.remove(c.getId());
			add(c);
			break;
		case ContentType.TICKET :
			ticketList.remove(c.getId());
			add(c);
			break;
		case ContentType.GROUP :
			groupList.remove(c.getId());
			add(c);
			break;
		default :
			System.err.println("ClientDB update : unknow ContentType");
				
		}
	}
	
	public static MessagePanel findMessage(long id) {
		MessagePanel ret = null;
		if((ret = messageList.get(id)) == null) {
			Message m = (Message) retrieve(id);
			//System.out.println(m);
			if(m != null) {
				MessagePanel message = new MessagePanel(m.getId(), m.getTime(), findUser(m.getUser()), m.getStatus(), m.getTextMessage());
				messageList.put(m.getId(), message);
				ret = message;
			}
		}
		//System.out.println(ret);
		return ret;
	}
	
	public static UserPanel findUser(long id) {
		UserPanel ret = null;
		if((ret = userList.get(id)) == null) {
			User u = (User) retrieve(id);
			if(u != null) {
				UserPanel user = new UserPanel(u.getId(), u.getNom(), u.getPrenom(), u.getCategory(), null, null);
				userList.put(u.getId(), user);
				ret = user;
			}
		}
		return ret;
	}
	
	public static UserPanel findUserAll(long id, boolean forceRetrieve) {
		UserPanel ret = null;
		if(forceRetrieve || (ret = userList.get(id)) == null) {
			User u = (User) retrieveAll(id);
			if(u != null) {
				List<GroupPanel> groups = new ArrayList<>();
				for(long groupId : u.getGroupList()) {
					groups.add(ClientDB.findGroup(groupId));
				}
				List<TicketPanel> tickets = new ArrayList<>();
				for(long ticketId : u.getTicketList()) {
					tickets.add(ClientDB.findTicket(ticketId));
				}
				UserPanel user = new UserPanel(u.getId(), u.getNom(), u.getPrenom(), u.getCategory(), tickets, groups);
				userList.put(u.getId(), user);
				ret = user;
			}
		}
		return ret;
	}
	
	public static UserPanel findUserAll(long id) {
		return findUserAll(id, false);
	}
	
	public static GroupPanel findGroup(long id) {
		GroupPanel ret = null;
		if((ret = groupList.get(id)) == null) {
			Group g = (Group) retrieve(id);
			if(g != null) {
				GroupPanel group = new GroupPanel(g.getId(), g.getName());
				groupList.put(g.getId(), group);
				ret = group;
			}
		}
		
		return ret;
	}
	
	public static TicketPanel findTicket(long id) {
		TicketPanel ret = null;
		ret = ticketList.get(id);
		if(ret == null) {
			Ticket t = (Ticket) retrieve(id);
			if(t != null) {
				TicketPanel ticket = new TicketPanel(t.getId(), t.getName(), findUser(t.getCreatorId()), findGroup(t.getGroupId()), t.getMessageList());
				ticketList.put(t.getId(), ticket);
				ret = ticket;
			}
		}
		return ret;
	}
	
	private static Packet retrieve(long id) {
		Packet ret = null;
		try {
			MainClient.comm.send(new Request(Commands.RETRIEVE, id));
			ret = MainClient.comm.receive();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(ret);
		if((ret.getCommand() & Commands.FAIL ) == Commands.FAIL) {
			System.err.println("ClientDB.retrieve : Server responded with fail for "+id);
			new Exception().printStackTrace();
			ret = null;
		}
		return ret;
	}	
	
	private static Packet retrieveAll(long id) {
		Packet ret = null;
		try {
			MainClient.comm.send(new Request((byte)(Commands.RETRIEVE | Commands.ALL), id));
			ret = MainClient.comm.receive();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(ret);
		if((ret.getCommand() & Commands.FAIL ) == Commands.FAIL) {
			System.err.println("ClientDB.retrieveAll : Server responded with fail for"+id);
			new Exception().printStackTrace();
			ret = null;
		}
		return ret;
	}	
	

}
