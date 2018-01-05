package client;

import interfaces_projet.Interface_Connexion;
import interfaces_projet.Interface_Utilisateur_principale;
import packet.User;

public class MainClient {
	public static CommunicatorClient comm;
	public static User user;
	public static Interface_Utilisateur_principale ui;
	
	public static void main(String[] args) {
		System.out.println("Lancement du client");
		comm = new CommunicatorClient("localhost", 3636);
		user = null;
		/*try {
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
		}*/
		
		new Interface_Connexion().setVisible(true);
		
		//TODO close comm on exit
		//comm.close();

	}

}
