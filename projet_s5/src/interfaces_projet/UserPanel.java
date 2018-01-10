package interfaces_projet;

import javax.swing.JPanel;

import java.util.List;

public class UserPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private long id;
	private String nom;
	private String prenom;
	private String category;
	private List<TicketPanel> ticketList;
	private List<GroupPanel> groupList;
	
	
	public UserPanel(long id, String nom, String prenom, String category, List<TicketPanel> ticketList, List<GroupPanel> groupList) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.category = category;
		this.ticketList = ticketList;
		this.groupList = groupList;
	}


        public List<GroupPanel> getGroupList(){
            return this.groupList;
        }
        
	@Override
	public String toString() {
		return "UserPanel [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", category=" + category
				+ ", ticketList=" + ticketList + ", groupList=" + groupList + "]";
	}
	
	public String getName() {
		return nom+" "+prenom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public String getNom() {
		return nom;
	}

}
