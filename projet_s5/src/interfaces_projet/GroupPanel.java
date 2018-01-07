package interfaces_projet;

import javax.swing.JPanel;

public class GroupPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private long id;
	private String name;
	//TODO user list ?
	
	
	public GroupPanel(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}


	public long getId() {
		return id;
	}


	public String getName() {
		return name;
	}
	

}
