package packet;

import java.util.List;

public class User extends Content {
	private static final long serialVersionUID = 1777039227152623262L;
	private String nom;
	private String prenom;
	private boolean agent;
	private List<Group> groupList;
	private List<Ticket> ticketList;
	
	

	public User(byte command, long id) {
		super(command, id);
	}
	
	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public boolean isAgent() {
		return agent;
	}

	public List<Group> getGroupList() {
		return groupList;
	}

	public List<Ticket> getTicketList() {
		return ticketList;
	}

	@Override
	public String toString() {
		return super.toString()+" TODO }";
	}
}
