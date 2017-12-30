package client;

import java.io.IOException;

public class MainClient {

	public static void main(String[] args) {
		System.out.println("Lancement du client");
		CommunicatorClient comm = new CommunicatorClient("localhost", 3636);
		try {
				comm.send("Hello");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			comm.close();
		}
		System.out.println("Fin du client");

	}

}
