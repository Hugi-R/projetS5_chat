package content;

import java.util.HashMap;

import communication.Id;

public class GroupList {
	private static HashMap<Id, Group> groups = new HashMap<>();
	
	public static Group find(Id id) {
		Group ret = null;
		if(groups.containsKey(id)) {
			ret = groups.get(id);
		} else {
			//TODO retrieve data from server
		}
		return ret;
		
	}

}
