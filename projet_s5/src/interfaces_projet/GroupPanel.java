package interfaces_projet;

import java.util.ArrayList;
import javax.swing.JPanel;
import java.util.List;

public class GroupPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private long id;
	private String name;
	private List<TicketPanel> ticketList;
	//TODO user list ?
	
	
	public GroupPanel(long id, String name) {
		super();
		this.id = id;
		this.name = name;
                this.ticketList = new ArrayList<>();
	}


	public long getId() {
		return id;
	}


	public String getName() {
		return name;
	}
        
        public List<TicketPanel> getTicketList() {
            return ticketList;
        }
        
	@Override
	public String toString() {
		return name;
	}


}
