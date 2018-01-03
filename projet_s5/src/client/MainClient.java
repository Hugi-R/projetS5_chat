package client;

import java.io.IOException;

import packet.Commands;
import packet.ContentType;
import packet.Message;
import utils.Id;

public class MainClient {

	public static void main(String[] args) {
		/*System.out.println("Lancement du client");
		CommunicatorClient comm = new CommunicatorClient("localhost", 3636);
		try {
			for(int i = 0; i< 3; i++) {
				long u = Id.generate(ContentType.USER);
				long t = Id.generate(ContentType.TICKET);
				Message message = new Message(Commands.SEND, Id.generate(ContentType.MESSAGE), u, t, 1000L, "message"+i);
				comm.send(message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			comm.close();
		}
		System.out.println("Fin du client");*/
		
		System.out.println(Id.generate(ContentType.USER));
		System.out.println(Id.generate(ContentType.TICKET));
		System.out.println(Id.generate(ContentType.MESSAGE));
		System.out.println(Id.generate(ContentType.GROUP));

	}

}
