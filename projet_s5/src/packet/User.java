package packet;

import java.util.List;

public class User extends Content {
	private static final long serialVersionUID = 1777039227152623262L;
	private String nom;
	private String prenom;
	private boolean agent;
	private List<Group> groupList;
	private List<Ticket> ticketList;
	
	

	public User(byte command, long id, String nom ,String prenom, boolean agent, List<Group> groupList , List<Ticket> ticketList ) {
		super(command, id);
		this.nom = nom;
		this.prenom = prenom;
		this.agent = agent;
		this.groupList = groupList;
		this.ticketList = ticketList;
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
	
	private String idDesGroup () {
		String result = "";
		for(Group g : groupList) {
			result = result+"-"+g.getId();
		}
		return result;
	}
	
	@Override
	public String toString() {
		return super.toString()+"Nom : "+nom+", Prenom : "+prenom+",Is a agent ["+agent+"] , liste des groupes ["+idDesGroup()+"-]";
	}
}
