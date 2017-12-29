package content;

import java.util.HashMap;

import communication.Id;

public class TicketList {
	private static HashMap<Id, Ticket> tickets = new HashMap<>();
	
	public static Ticket find(Id id) {
		Ticket ret = null;
		if(tickets.containsKey(id)) {
			ret = tickets.get(id);
		} else {
			//TODO retrieve data from server
		}
		return ret;
		
	}


}
