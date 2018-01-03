package client;

import java.util.HashMap;

import packet.Ticket;
import utils.Id;

public class TicketList {
	private HashMap<Id, Ticket> tickets = new HashMap<>();
	
	public Ticket find(Id id) {
		Ticket ret = null;
		if(tickets.containsKey(id)) {
			ret = tickets.get(id);
		} else {
			//TODO retrieve data from server
		}
		return ret;
		
	}


}
