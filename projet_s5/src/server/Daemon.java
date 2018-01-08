package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Daemon {
	private ServerSocket serverSocket;
	
	public Daemon(int port) throws IOException {
		serverSocket = new ServerSocket(port); 
	}
	
	public void listen() {
		for(;;){
			try{
				Socket client = serverSocket.accept();
				CommunicatorServer comm = new CommunicatorServer(client);
				new Thread(new Handler(comm)).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	

}
