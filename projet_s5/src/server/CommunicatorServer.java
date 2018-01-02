package server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import content.Content;

public class CommunicatorServer {
	private Socket clientSocket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	public CommunicatorServer(Socket socket) throws IOException {
		clientSocket = socket;
		out = new ObjectOutputStream(clientSocket.getOutputStream());
		in = new ObjectInputStream(clientSocket.getInputStream());
	}
	
	public void send(Content data) throws IOException {
		out.writeObject(data);
	}
	
	public Content receive() throws ClassNotFoundException, IOException{
		Content data = null;
		try {
			data = (Content) in.readObject();
		} catch (EOFException e) {
			data = null;
		}
		return data;
	}
	
	public void close() {
		try {
			out.close();
			in.close();
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isClosed() {
		return clientSocket.isClosed();
	}

}
