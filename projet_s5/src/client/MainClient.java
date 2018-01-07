package client;

import java.io.IOException;

import interfaces_projet.Interface_Utilisateur_principale;
import interfaces_projet.TicketPanel;
import packet.Commands;
import packet.Connect;
import packet.Packet;
import packet.User;

public class MainClient {
	public static CommunicatorClient comm;
	public static User user;
	public static Interface_Utilisateur_principale ui;
	
	public static void main(String[] args) {
		System.out.println("Lancement du client");
		comm = new CommunicatorClient("localhost", 3636);
		user = null;
		try {			
			//log in
			Connect connect = new Connect((byte)(Commands.SEND | Commands.CONNECT), "hugo.roussel@univ-tlse3.fr", "carrotte");
			comm.send(connect);
			Packet connectResp = comm.receive();
			if(connectResp.getCommand() == (Commands.FAIL | Commands.CONNECT)) {
				System.err.println("Username or password invalid");
			} else {
				//TODO : store user in clientDB
				
				//retrieve a message
				TicketPanel ticket = ClientDB.findTicket(317943147588095081L);
				System.out.println(ticket.toString());
				for(Long id : ticket.getMessages()) {
					System.out.println(ClientDB.findMessage(id).toString());
				}
		
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			comm.close();
		}
		
		//new Interface_Connexion().setVisible(true);

		System.out.println("Fin client");
	}

}
