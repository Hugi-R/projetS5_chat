package server;

import java.io.IOException;
import java.util.List;

import packet.Commands;
import packet.Connect;
import packet.Content;
import packet.ContentType;
import packet.Group;
import packet.ListOfGroup;
import packet.Message;
import packet.Packet;
import packet.Request;
import packet.Ticket;
import utils.Id;

public class Handler implements Runnable{
	private CommunicatorServer comm;
	private long connectedUser;
	private Database database ;
	private Daemon daemon;

	public Handler(CommunicatorServer comm , Database database, Daemon daemon) {
		this.comm = comm;
		connectedUser = 0;
		this.database = database;
		this.daemon = daemon;
	}

	@Override
	public void run() {
		System.out.println("Lancement handler");
		Packet data = null;
		int i = 0;
		
		while(!comm.isClosed() && i < 10) {
			try {
				System.out.println("Attente ...");
				data = comm.receive();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
				data = null;
				i++;
			}
			
			if(data == null) {
				comm.close();
			} else {
				if(connectedUser != 0) {
					//System.out.println(data);
					switch (data.getCommand() & ~Commands.ALL) {
					case Commands.RETRIEVE :
						System.out.println("RETRIVE : "+data);
						commandRetrieve(data);
						break;
					case Commands.SEND :
						System.out.println("SEND : "+data);
						commandSend(data);
						break;
					case Commands.SEND | Commands.CONNECT :
						System.out.println("CONNECT : "+data);
						connect(data);
						break;
					default :
						System.err.println("Unknow command");
					}
				} else {
					if(data.getCommand() == (Commands.SEND | Commands.CONNECT)) {
						System.out.println("CONNECT : "+data);
						connect(data);
					} else {
						permissionDenied();
					}
				}
			}
		}
		System.out.println("Fin handler, i = "+i);
		
	}
	
	private void permissionDenied() {
		try {
			comm.send(new Packet((byte)(Commands.FAIL | Commands.CONNECT)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	private void connect(Packet data) {
		Connect c = (Connect) data;
		long u = database.connect(c.getPassword(), c.getUsername());
		Packet resp = null;
		if(u == -1) {
			byte command = Commands.FAIL | Commands.CONNECT;
			resp = new Packet(command);
		} else {
			resp = database.retrieveUserWithList(u);
			connectedUser = u;
		}
		try {
			comm.send(resp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retrieve data from database and send them to the client
	 * @param p
	 */
	private void commandRetrieve(Packet p) {
		Request r = (Request) p;
		byte type = Id.type(r.getId());
		boolean isAll = ((p.getCommand() & Commands.ALL)==Commands.ALL);
		Packet response = null;
		
		//retrieve data for response
		switch (type) {
		case ContentType.MESSAGE :
			response = database.retrieveMessage(r.getId(), connectedUser);
			break;
		case ContentType.USER :
			if(isAll) {
				response = database.retrieveUserWithList(r.getId());
			} else {
				response = database.retrieveUserShort(r.getId());
			}
			break;
		case ContentType.GROUP :
			if(isAll) {
				List<Group> l = database.retrieveAllGroup();
				response = new ListOfGroup(Commands.SEND, Id.DEFAULT_ID_GROUP, l);
			} else {
				response = database.retrieveGroup(r.getId());
			}
			break;
		case ContentType.TICKET :
			response = database.retriveTicket(r.getId());
			break;
		default :
			System.err.println("Unknow content type");
		}
		
		//send response
		if(response != null) {
			try {
				comm.send(response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				comm.send(new Content(Commands.FAIL, r.getId()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * Execute command in packet and update database
	 * Usually new message or ticket
	 * @param p
	 */
	private void commandSend(Packet p) {
		Content data = (Content) p;
		byte contentType = Id.type(data.getId());
		long id = 0L;
		int returnCode;
		int i = 0;
		switch(contentType) {
		case ContentType.MESSAGE :
			Message message = (Message) data;
			do {
				id = Id.generate(contentType);
				returnCode = database.addMessage(id, message.getUser(), message.getTicket(), message.getTextMessage());
				i++;
				if(i > 5) {
					fail();
					return;
				}
			}while((returnCode == -1) || (returnCode == 0));
			try {
				comm.send(database.retrieveMessage(id, connectedUser));
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case ContentType.TICKET :
			Ticket ticket = (Ticket) data;
			do {
				id = Id.generate(contentType);
				returnCode = database.addTicket(id , ticket.getGroupId(), ticket.getName());
				i++;
				if(i > 5) {
					fail();
					return;
				}
			}while((returnCode == -1) || (returnCode == 0));
			try {
				comm.send(new Ticket(Commands.SEND, id, ticket.getCreatorId(), ticket.getGroupId(), ticket.getName(), null));
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		default :
			System.err.println("commandSend : contentType invalid");	
		}
		//System.out.println("created id : "+id);
		
	}
	
	private void fail() {
		try {
			comm.send(new Packet(Commands.FAIL));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
