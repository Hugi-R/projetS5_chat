package client;

import packet.Content;
import packet.Packet;

public class Update implements Runnable {
	private Packet p;
	
	
	
	public Update(Packet p) {
		super();
		this.p = p;
	}

	@Override
	public void run() {
		System.out.println("UPDATE : "+p);
		try {
			ClientDB.update((Content)p);
			MainClient.ui.update();
		} catch (ClassCastException e) {
			e.printStackTrace();
		}
	}

}
