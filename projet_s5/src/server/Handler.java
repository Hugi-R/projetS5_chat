package server;

import java.io.IOException;

public class Handler implements Runnable{
	CommunicatorServer comm;

	public Handler(CommunicatorServer comm) {
		this.comm = comm;
	}

	@Override
	public void run() {
		System.out.println("Lancement handler");
		while(!comm.isClosed()) {
			try {
				System.out.println("Attente ...");
				String str = comm.receive();
				System.out.println("Message recu : "+str);
				if(str == null)
					comm.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Fin handler");
		
	}

}
