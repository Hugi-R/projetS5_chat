package packet;

import java.util.List;

public class ListOfGroup extends Content {
	private static final long serialVersionUID = 2582892649828034108L;
	private List<Group> list;

	public ListOfGroup(byte command, long id, List<Group> list) {
		super(command, id);
		this.list = list;
	}

	public List<Group> getListId() {
		return list;
	}

}
