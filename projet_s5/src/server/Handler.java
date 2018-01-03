package server;

import java.io.IOException;

import packet.Content;
import packet.ContentType;
import packet.Id;
import packet.Message;

public class Handler implements Runnable{
	CommunicatorServer comm;

	public Handler(CommunicatorServer comm) {
		this.comm = comm;
	}

	@Override
	public void run() {
		System.out.println("Lancement handler");
		Content data = null;
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
				switch (Id.type(data.getId())) {
				case ContentType.MESSAGE :
					Message m = (Message) data;
					System.out.println("Message recu : "+m.toString());
					break;
				default :
					System.err.println("Unknow type");
				}
			}
		}
		System.out.println("Fin handler");
		
	}

}
