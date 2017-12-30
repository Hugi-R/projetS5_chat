package content;

import java.util.HashMap;

public class UserList {
	private static HashMap<Id, User> users = new HashMap<>();
	
	public static User find(Id id) {
		User ret = null;
		if(users.containsKey(id)) {
			ret = users.get(id);
		} else {
			//TODO retrieve data from server
		}
		return ret;
		
	}

}
