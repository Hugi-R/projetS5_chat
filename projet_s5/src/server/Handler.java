package server;

import java.io.IOException;

import packet.Commands;
import packet.Connect;
import packet.ContentType;
import packet.Packet;
import packet.Request;
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
				System.out.println(data);
				switch (data.getCommand()) {
				case Commands.RETRIEVE :
					System.out.println("RETRIVE : "+data);
					retrieve(data);
					break;
				case Commands.SEND :
					System.out.println("SEND : "+data);
					send(data);
					break;
				case Commands.SEND | Commands.CONNECT :
					System.out.println("CONNECT : "+data);
					connect(data);
					break;
				default :
					System.err.println("Unknow command");
				}
			}
		}
		System.out.println("Fin handler");
		
	}
	

	private void connect(Packet data) {
		Connect c = (Connect) data;
		long u = Database.connect(c.getPassword(), c.getUsername());
		Packet resp = null;
		if(u == -1) {
			byte command = Commands.FAIL | Commands.CONNECT;
			resp = new Packet(command);
		} else {
			resp = Database.RecupUserWithList(u);
			connectedUser = u;
		}
		try {
			comm.send(resp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Retrieve data from database and send them to the client
	 * @param p
	 */
	private void retrieve(Packet p) {
		Request r = (Request) p;
		byte type = Id.type(r.getId());
		Packet response = null;
		
		//retrieve data for response
		switch (type) {
		case ContentType.MESSAGE :
			response = Database.RecupMessage(r.getId());
			break;
		case ContentType.USER :
			//TODO
			break;
		case ContentType.GROUP :
			//TODO
			break;
		case ContentType.TICKET :
			//TODO
			break;
		default :
			System.err.println("Unknow content type");
		}
		
		//send response
		if(response != null) {
			try {
				comm.send(response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * Execute command in packet and update database
	 * Usually new message or ticket
	 * @param p
	 */
	private void send(Packet p) {
		
	}

}
