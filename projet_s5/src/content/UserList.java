package content;

import java.util.HashMap;

import communication.Id;

public class UserList {
	private static HashMap<Id, User> users = new HashMap<>();
	
	public static User find(Id id) {
		User ret = null;
		if(users.containsKey(id)) {
			ret = users.get(id);
		} else {
			//TODO retrieve user data from server
		}
		return ret;
		
	}

}
