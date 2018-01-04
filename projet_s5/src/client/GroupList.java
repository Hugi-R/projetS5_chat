package client;

import java.util.HashMap;

import packet.Group;
import utils.Id;

public class GroupList {
	private HashMap<Id, Group> groups = new HashMap<>();
	
	public Group find(Id id) {
		Group ret = null;
		if(groups.containsKey(id)) {
			ret = groups.get(id);
		} else {
			//TODO retrieve data from server
		}
		return ret;
		
	}

}
