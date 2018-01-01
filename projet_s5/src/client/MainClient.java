package client;

import java.io.IOException;

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
			for(int i = 1; i< 30; i+=10) {
				Message message = new Message(new Id(i), new User(new Id(i+1)), new Ticket(new Id(i+2)), 1000L, "message"+i);
				comm.send(message.toJson());
			}
		} catch (IdException | IOException e) {
			e.printStackTrace();
		} finally {
			comm.close();
		}
		System.out.println("Fin du client");

	}

}
