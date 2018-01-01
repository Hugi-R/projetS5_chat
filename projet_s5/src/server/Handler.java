package server;

import java.io.IOException;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;

import content.ContentException;
import content.ContentType;
import content.IdException;
import content.Message;

public class Handler implements Runnable{
	CommunicatorServer comm;

	public Handler(CommunicatorServer comm) {
		this.comm = comm;
	}

	@Override
	public void run() {
		System.out.println("Lancement handler");
		String data = null;
		while(!comm.isClosed()) {
			try {
				System.out.println("Attente ...");
				data = comm.receive();
				System.out.println("data : "+data);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(data == null) {
				comm.close();
			} else {
				JsonObject jobj = Json.parse(data).asObject();
				switch(jobj.getString("type", "")) {
				case "" : 
					System.err.println("No type in data");
					break;
				case ContentType.MESSAGE : 
					try {
						Message message = new Message(jobj);
						System.out.println("Un message recu : " + message.toString());
					} catch (IdException | ContentException e) {
						e.printStackTrace();
					}
					break;
				default :
					System.err.println("Unknow type");
					
				}
			}
		}
		System.out.println("Fin handler");
		
	}

}
