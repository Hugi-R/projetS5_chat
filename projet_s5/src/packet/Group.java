package packet;

public class Group extends Content{
	private static final long serialVersionUID = 8180388956052672819L;
	private String groupName;
	
	public Group(byte command, long id, String nomGroup) {
		super(command, id);
		this.groupName = nomGroup;
	}

	public String getNomGroup() {
		return groupName;
	}

	@Override
	public String toString() {
		return super.toString()+" groupName :"+groupName+" }";
	}
	

}
