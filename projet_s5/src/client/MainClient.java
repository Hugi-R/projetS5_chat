package client;

import java.io.IOException;

import interfaces_projet.Interface_CreationTicket;
import interfaces_projet.Interface_Utilisateur_principale;
import packet.Commands;
import packet.Connect;
import packet.Packet;
import packet.User;
import utils.Id;

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
				user = (User)connectResp;
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//new Interface_Connexion().setVisible(true);
		new Interface_CreationTicket().setVisible(true);;

		System.out.println(Id.DEFAULT_ID_MESSAGE);
		System.out.println("Fin client");
	}

}
