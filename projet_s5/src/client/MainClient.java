package client;

import java.io.IOException;

import javax.swing.JFrame;

import interfaces_projet.Interface_Utilisateur_principale;
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
				JFrame frame = new JFrame("test");
				frame.add(ClientDB.find(83461768749910722L));
				frame.setVisible(true);
		
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			comm.close();
		}
		
		//new Interface_Connexion().setVisible(true);
		
		
		/*for(int i = 0; i < 5; i++) {
			System.out.println("Message : "+Id.generate(ContentType.MESSAGE));
			System.out.println("User : "+Id.generate(ContentType.USER));
			System.out.println("Ticket : "+Id.generate(ContentType.TICKET));
			System.out.println("Group : "+Id.generate(ContentType.GROUP));
		}*/


	}

}
