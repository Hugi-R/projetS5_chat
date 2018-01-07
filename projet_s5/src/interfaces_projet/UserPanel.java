package interfaces_projet;

import javax.swing.JPanel;

import client.GroupList;
import client.TicketList;

public class UserPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private long id;
	private String nom;
	private String prenom;
	private String category;
	private TicketList ticketList;
	private GroupList groupList;
	
	
	public UserPanel(long id, String nom, String prenom, String category, TicketList ticketList, GroupList groupList) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.category = category;
		this.ticketList = ticketList;
		this.groupList = groupList;
	}


	@Override
	public String toString() {
		return "UserPanel [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", category=" + category
				+ ", ticketList=" + ticketList + ", groupList=" + groupList + "]";
	}
	
	public String getName() {
		return prenom + " " + nom;
	}

}