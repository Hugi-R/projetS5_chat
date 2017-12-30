package communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CommunicatorClient {
	private String server;
	private int portNumber;
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	
	public CommunicatorClient(String server, int portNumber) {
		this.server = server;
		this.portNumber = portNumber;
		this.socket = null;
	}
	
	public void open() throws IOException {
		socket = new Socket(server, portNumber);
		out = new PrintWriter(socket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	
	public void close() {
		out.close();
		try {
			in.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void send(String data) throws IOException{
		if((socket != null) && (socket.isClosed() || !socket.isConnected())) {
			open();
		}
		out.println(data);
	}
	
	public String receive() throws IOException{
		String data;
		data = in.readLine();
		return data;
	}
	
	

}
