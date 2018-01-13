package server;

import java.io.IOException;

import interfaces_projet.Interface_Serveur;

public class MainServer {
	public static String url = "jdbc:mysql://eralyon.net:3306/projets5";
	public static String user = "projets5_server";
	public static String passwd = "projets5_psswd";
	
	public static void main(String[] args) {
		System.out.println("Lancement du serveur");
		Database.start(url,user,passwd);
		
		boolean gui = false;
		
		if(args.length == 2)
			gui = "gui".equals(args[1]);
		
		gui = true;
		try {
			if(gui){
				new Interface_Serveur().setVisible(true);;
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
