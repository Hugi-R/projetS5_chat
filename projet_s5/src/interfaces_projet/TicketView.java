package interfaces_projet;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class TicketView extends JPanel {
	private static final long serialVersionUID = 1L;

	public TicketView() {
		setLayout(new GridLayout(0, 1));
		setBorder(new LineBorder(Color.BLACK));
		setBackground(Color.LIGHT_GRAY);
		
		
		UserPanel hugo =  new UserPanel(0, "ROUSSEL", "Hugo", "CAT", null, null);
		MessagePanel m = new MessagePanel(0, 0, hugo, "Message1");
		add(m);
		m = new MessagePanel(0, 0, hugo, "Ceci est un treeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeees loooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooong message");
		add(m);
		m = new MessagePanel(0, 0, hugo, "Message3");
		add(m);
		m = new MessagePanel(0, 0, hugo, "Message4");
		add(m);
	}
}
