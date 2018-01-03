package client;

import java.io.IOException;

import packet.Commands;
import packet.Message;
import packet.Packet;
import packet.Request;

public class MainClient {

	public static void main(String[] args) {
		System.out.println("Lancement du client");
		CommunicatorClient comm = new CommunicatorClient("localhost", 3636);
		try {
			Packet request = new Request(Commands.RETRIEVE, 97506691153493440L);
			System.out.println("request : "+request);
			comm.send(request);
			Message resp = (Message) comm.receive();
			System.out.println("response : "+resp);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			comm.close();
		}
		System.out.println("Fin du client");

	}

}
