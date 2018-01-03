package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Daemon {
	private ServerSocket serverSocket;
	private List<Thread> clients;
	
	public Daemon(int port) throws IOException {
		serverSocket = new ServerSocket(port); 
		clients = new ArrayList<>();
	}
	
	public void listen() {
		try{
			Socket client = serverSocket.accept();
			CommunicatorServer comm = new CommunicatorServer(client);
			clients.add(new Thread(new Handler(comm)));
			clients.get(clients.size()-1).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	

}
