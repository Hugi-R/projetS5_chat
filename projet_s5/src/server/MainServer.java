package server;

import java.io.IOException;

public class MainServer {
	public static void main(String[] args) {
		System.out.println("Lancement du serveur");
		try {
			Daemon d = new Daemon(3636);
			d.listen();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println("Fin du serveur");
	}
}
