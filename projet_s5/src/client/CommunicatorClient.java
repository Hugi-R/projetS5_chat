package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

import packet.Commands;
import packet.Content;
import packet.Packet;

public class CommunicatorClient {
	private String server;
	private int portNumber;
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Queue<Packet> fifo;
	private boolean listening;
	
	public CommunicatorClient(String server, int portNumber) {
		this.server = server;
		this.portNumber = portNumber;
		this.socket = null;
	}
	
	public void open() throws IOException {
		socket = new Socket(server, portNumber);
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
		fifo = new LinkedList<>();
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
	
	public void send(Packet data) throws IOException{
		if((socket == null) || (socket.isClosed() || !socket.isConnected())) {
			open();
		}
		out.writeObject(data);
	}
	
	public Packet receive() throws IOException {
		if(listening) {
			while(fifo.isEmpty()) {
				try {
					TimeUnit.MILLISECONDS.sleep(50L);
				} catch (InterruptedException e) {
					e.printStackTrace();
					Thread.currentThread().interrupt();
				}
			}
			return fifo.poll();
		} else {
			throw new IOException("Client not listening");
		}
	}
	
	public void listen() {
		listening = true;
		Packet p;
		int failure = 0;
		while(!socket.isClosed() && (failure < 10)) {
			try {
				p = (Packet) in.readObject();
				if((p.getCommand() & Commands.UPDATE) == Commands.UPDATE) {
					update(p);
				} else {
					fifo.add(p);
				}
				failure = 0;
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
				failure++;
			}
		}
		listening = false;
		if(failure > 9) {
			System.err.println("Listen stoped for reason : too many failure");
		}
	}
	
	private void update(Packet p) {
		System.out.println("OOO--- UPDATE : "+p);
		try {
			ClientDB.update((Content)p);
			MainClient.ui.update();
		} catch (ClassCastException e) {
			e.printStackTrace();
		}
	}
	
	

}
