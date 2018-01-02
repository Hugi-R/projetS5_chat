package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import content.Content;

public class CommunicatorClient {
	private String server;
	private int portNumber;
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	public CommunicatorClient(String server, int portNumber) {
		this.server = server;
		this.portNumber = portNumber;
		this.socket = null;
	}
	
	public void open() throws IOException {
		socket = new Socket(server, portNumber);
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
	}
	
	public void close() {
		try {
			out.close();
			in.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void send(Content data) throws IOException{
		if((socket == null) || (socket.isClosed() || !socket.isConnected())) {
			open();
		}
		out.writeObject(data);
	}
	
	public Content receive() throws IOException, ClassNotFoundException{
		return (Content) in.readObject();
	}
	
	

}
