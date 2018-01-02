package client;

import java.io.IOException;

import content.ContentType;
import content.Id;
import content.IdException;
import content.Message;
import content.Ticket;
import content.User;

public class MainClient {

	public static void main(String[] args) {
		System.out.println("Lancement du client");
		CommunicatorClient comm = new CommunicatorClient("localhost", 3636);
		try {
			for(int i = 0; i< 3; i++) {
				User u = new User(new Id(Id.generate(ContentType.USER)));
				Ticket t = new Ticket(new Id(Id.generate(ContentType.TICKET)));
				Message message = new Message(new Id(Id.generate(ContentType.MESSAGE)), u, t, 1000L, "message"+i);
				comm.send(message);
			}
		} catch (IdException | IOException e) {
			e.printStackTrace();
		} finally {
			comm.close();
		}
		System.out.println("Fin du client");
		

	}

}
