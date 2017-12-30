package client;

import java.io.IOException;

public class MainClient {

	public static void main(String[] args) {
		System.out.println("Lancement du client");
		CommunicatorClient comm = new CommunicatorClient("localhost", 3636);
		try {
			for(int i = 0; i< 10; i++)
				comm.send("Hello "+i);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			comm.close();
		}
		System.out.println("Fin du client");

	}

}
