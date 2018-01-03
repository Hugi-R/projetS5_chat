package client;

import java.util.HashMap;

import packet.Id;
import packet.User;

public class UserList {
	private HashMap<Id, User> users = new HashMap<>();
	
	public User find(Id id) {
		User ret = null;
		if(users.containsKey(id)) {
			ret = users.get(id);
		} else {
			//TODO retrieve data from server
		}
		return ret;
		
	}

}
