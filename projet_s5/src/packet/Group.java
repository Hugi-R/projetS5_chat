package packet;

public class Group extends Content{
	private static final long serialVersionUID = 8180388956052672819L;
	private String nomGroup;
	
	public Group(byte command, long id, String nomGroup) {
		super(command, id);
		this.nomGroup = nomGroup;
	}

	public String getNomGroup() {
		return nomGroup;
	}

	@Override
	public String toString() {
		return super.toString()+" TODO }";
	}
	

}
