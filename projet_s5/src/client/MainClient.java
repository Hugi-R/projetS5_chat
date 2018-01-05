package client;

import java.io.IOException;

import packet.Commands;
import packet.Connect;
import packet.Message;
import packet.Packet;
import packet.Request;

public class MainClient {

	public static void main(String[] args) {
		System.out.println("Lancement du client");
		CommunicatorClient comm = new CommunicatorClient("localhost", 3636);
		try {
			//try to request
			Packet request = new Request(Commands.RETRIEVE, 97506691153493440L);
			System.out.println("request : "+request);
			comm.send(request);
			Packet resp = comm.receive();
			System.out.println("response : "+resp);
			
			//log in
			Connect connect = new Connect((byte)(Commands.SEND | Commands.CONNECT), "toto@gmail.com", "mdp");
			comm.send(connect);
			Packet connectResp = comm.receive();
			if(connectResp.getCommand() == (Commands.FAIL | Commands.CONNECT)) {
				System.err.println("Username or password invalid");
			} else {
				//TODO : store user in clientDB
				
				//retriave a message
				request = new Request(Commands.RETRIEVE, 97506691153493440L);
				System.out.println("request : "+request);
				comm.send(request);
				Message message = (Message) comm.receive();
				System.out.println("response : "+message);
		
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			comm.close();
		}
		System.out.println("Fin du client");

	}

}
