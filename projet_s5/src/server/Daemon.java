package server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import packet.Content;

public class Daemon {
	private ServerSocket serverSocket;
	private List<Handler> handlerList;
	private Database localDB;
	public Daemon(int port) throws IOException {
		serverSocket = new ServerSocket(port); 
	}
	
	public void listen() {
		handlerList = new ArrayList<>();
		localDB = new Database(MainServer.url, MainServer.user, MainServer.passwd);
		for(;;){
			try{
				Socket client = serverSocket.accept();
				CommunicatorServer comm = new CommunicatorServer(client);
				Handler h = new Handler(comm,new Database(MainServer.url, MainServer.user, MainServer.passwd), this);
				handlerList.add(h);
				new Thread(h).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void removeHandler(Handler h) {
		handlerList.remove(h);
	}
	
	public void updateClient(long id) {
		List<Long> users = localDB.retrieveUserRecipientOfMessage(id); 
		for(Handler h : handlerList) {
			if(users.contains(h.getConnectedUser())) {
				h.update(id);
			}
		}
	}
}
