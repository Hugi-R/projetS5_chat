package client;

import java.io.IOException;

import packet.ContentType;
import packet.Id;
import packet.Message;
import packet.Ticket;
import packet.User;

public class MainClient {

	public static void main(String[] args) {
		System.out.println("Lancement du client");
		CommunicatorClient comm = new CommunicatorClient("localhost", 3636);
		try {
			for(int i = 0; i< 3; i++) {
				User u = new User(Id.generate(ContentType.USER));
				Ticket t = new Ticket(Id.generate(ContentType.TICKET));
				Message message = new Message(Id.generate(ContentType.MESSAGE), u.getId(), t.getId(), 1000L, "message"+i);
				comm.send(message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			comm.close();
		}
		System.out.println("Fin du client");
		

	}

}
