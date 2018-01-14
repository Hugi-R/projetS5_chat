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
		
		if(args.length == 2)
			gui = !"nogui".equals(args[1]);
		
		try {
			if(gui){
				new Interface_Serveur(new Database(url, user, passwd)).setVisible(true);
			}
			Daemon d = new Daemon(3636);
			d.listen();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println("Fin du serveur");
	}
}
