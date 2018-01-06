package packet;

import java.util.List;

public class User extends Content {
	private static final long serialVersionUID = 1777039227152623262L;
	private String nom;
	private String prenom;
	private String category;
	private List<Long> groupList;
	private List<Long> ticketList;
	
	

	public User(byte command, long id, String nom ,String prenom, String category, List<Long> groupList , List<Long> ticketList ) {
		super(command, id);
		this.nom = nom;
		this.prenom = prenom;
		this.category = category;
		this.groupList = groupList;
		this.ticketList = ticketList;
	}
	
	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getCategory() {
		return category;
	}

	public List<Long> getGroupList() {
		return groupList;
	}

	public List<Long> getTicketList() {
		return ticketList;
	}
	
	@Override
	public String toString() {
		return super.toString()+"Nom : "+nom+", Prenom : "+prenom+", Category "+category+", groups "+groupList+", tickets : "+ticketList+" }";
	}
}
