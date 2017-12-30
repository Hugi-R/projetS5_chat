package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CommunicatorServer {
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	
	public CommunicatorServer(Socket socket) throws IOException {
		clientSocket = socket;
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	}
	
	public void send(String data) {
		out.println(data);
	}
	
	public String receive() throws IOException{
		String data;
		data = in.readLine();
		return data;
	}
	
	public void close() {
		out.close();
		try {
			in.close();
			clientSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isClosed() {
		return clientSocket.isClosed();
	}

}
