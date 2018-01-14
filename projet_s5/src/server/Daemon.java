package server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Daemon {
	private ServerSocket serverSocket;
	private List<Handler> handlerList;
	public Daemon(int port) throws IOException {
		serverSocket = new ServerSocket(port); 
	}
	
	public void listen() {
		for(;;){
			try{
				Socket client = serverSocket.accept();
				CommunicatorServer comm = new CommunicatorServer(client);
				new Thread(new Handler(comm,new Database(MainServer.url, MainServer.user, MainServer.passwd), this)).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
