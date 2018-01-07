package server;

import java.io.IOException;

import packet.Commands;
import packet.Connect;
import packet.Content;
import packet.ContentType;
import packet.Message;
import packet.Packet;
import packet.Request;
import packet.Ticket;
import utils.Id;

public class Handler implements Runnable{
	private CommunicatorServer comm;
	private long connectedUser;
	

	public Handler(CommunicatorServer comm) {
		this.comm = comm;
		connectedUser = 0;
	}

	@Override
	public void run() {
		System.out.println("Lancement handler");
		Packet data = null;
		int i = 0;
		
		while(!comm.isClosed() && i < 10) {
			try {
				i++;
				System.out.println("Attente ...");
				data = comm.receive();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			if(data == null) {
				comm.close();
			} else {
				if(connectedUser != 0) {
					System.out.println(data);
					switch (data.getCommand()) {
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
		System.out.println("Fin handler");
		
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
		long u = Database.connect(c.getPassword(), c.getUsername());
		Packet resp = null;
		if(u == -1) {
			byte command = Commands.FAIL | Commands.CONNECT;
			resp = new Packet(command);
		} else {
			resp = Database.retrieveUserWithList(u);
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
			response = Database.retrieveMessage(r.getId());
			break;
		case ContentType.USER :
			if(isAll) {
				response = Database.retrieveUserWithList(r.getId());
			} else {
				response = Database.retrieveUserShort(r.getId());
			}
			break;
		case ContentType.GROUP :
			response = Database.retrieveGroup(r.getId());		
			break;
		case ContentType.TICKET :
			response = Database.retriveTicket(r.getId());
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
		switch(contentType) {
		case ContentType.MESSAGE :
			Message message = (Message) data;
			while(Database.addMessage(Id.generate(contentType), message.getUser(), message.getTicket(), message.getTextMessage()) == -1);
			break;
		case ContentType.TICKET :
			Ticket ticket = (Ticket) data;
			while(Database.addTicket(Id.generate(contentType), ticket.getGroupId(), ticket.getName()) == -1);
			break;
		default :
			System.err.println("commandSend : contentType invalid");	
		}
		
	}

}
