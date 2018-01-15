package server;

import java.io.IOException;

import interfaces_projet.Interface_Serveur;

public class MainServer {
	public static String url = "jdbc:mysql://eralyon.net:3306/projets5";
	public static String user = "projets5_server";
	public static String passwd = "projets5_psswd";
	public static void main(String[] args) {
		System.out.println("Lancement du serveur");
		
		boolean gui = true;
		int port = 3636;
		
		if(args.length >= 2){
			gui = !"nogui".equals(args[0]);
			port = Integer.parseInt(args[1]);
			if(args.length == 5){
				url = args[2];
				user = args[3];
				passwd = args[4];
			}
		}
		
		
		try {
			if(gui){
				new Interface_Serveur(new Database(url, user, passwd)).setVisible(true);
			}
			Daemon d = new Daemon(port);
			d.listen();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println("Fin du serveur");
	}
}
