package interfaces_projet;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import client.ClientDB;
import client.MainClient;
import packet.Commands;
import packet.Message;
import packet.Packet;
import utils.Id;
import utils.StatusType;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Interface_Utilisateur_principale extends javax.swing.JFrame implements TreeSelectionListener{
	private static final long serialVersionUID = 1L;
	static String texteSaisieMessage = "Saisissez votre texte ici.";
	private long user;
	public class ConfirmDialogInFrame extends JFrame{
		private static final long serialVersionUID = 1L;
		public ConfirmDialogInFrame() {
	        setVisible(false);
	        setAlwaysOnTop(true);
	    }
	}
	private TicketPanel selectedTicket = null;
	
	/**
	 * Creates new form JFrame
	 */
	public Interface_Utilisateur_principale(long user) {
		setMinimumSize(new Dimension(200, 200));
		this.user = user;
		initComponents();
        this.setLocationRelativeTo(null);

		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				if (JOptionPane.showConfirmDialog(new ConfirmDialogInFrame(), "Are you sure to close the app?", "Really Closing?",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					// action on closing
					MainClient.close();
				}
			}
		});
		this.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
	}

	private void initComponents() {

		setPreferredSize(new Dimension(560, 600));		
        setTitle("forum universite");
		
		getContentPane().setLayout(new BorderLayout(0, 0));
								
		splitPane = new JSplitPane();
		splitPane.setPreferredSize(null);
		splitPane.setDividerSize(3);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		leftJPanel = new javax.swing.JPanel();				
		leftJPanel.setLayout(new BorderLayout(0, 0));
		
		ticketScrollPane = new javax.swing.JScrollPane();
		ticketScrollPane.setAutoscrolls(true);
		
		constructTicketTree(); //must be called after ticketScrollPane init
		leftJPanel.add(ticketScrollPane);
		
		newTicketButton = new JButton("Nouveau Ticket");
		newTicketButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				newTicketButtonAction();
			}
		});
		leftJPanel.add(newTicketButton, BorderLayout.SOUTH);
		rightJPanel = new javax.swing.JPanel();
								
		rightJPanel.setLayout(new java.awt.BorderLayout());
										
		ticketViewer = 	new javax.swing.JScrollPane();
		ticketViewer.setPreferredSize(new Dimension(150, 460));
		ticketViewer.setViewportView(new TicketPanel(0, "", null, null, null));
		textPanel = new javax.swing.JScrollPane();
		saisieMessage = new javax.swing.JTextArea();
		saisieMessage.setPreferredSize(new Dimension(4, 60));
		
		//Enter pressed
		saisieMessage.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "Enter pressed");
        saisieMessage.getActionMap().put("Enter pressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	// envoi message
            	sendMessage();
            	saisieMessage.setText(null);
            }
        });
		
		//Shift+Enter pressed
        saisieMessage.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, KeyEvent.SHIFT_DOWN_MASK, false), "Shift+Enter pressed");
		saisieMessage.getActionMap().put("Shift+Enter pressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                saisieMessage.setText(saisieMessage.getText()+"\n");
            }
        });
		
		saisieMessage.setText(texteSaisieMessage);
		saisieMessage.setToolTipText("<html>"
				+ "shift+Entrée: retour à la ligne"
		        + "<br>"
				+ "Entrée: envoyer le message"
				+ "</html>");
		saisieMessage.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				saisieMessageMouseClicked(evt);
			}
		});
		textPanel.setViewportView(saisieMessage);
		
		rightJPanel.add(textPanel, java.awt.BorderLayout.SOUTH);
		rightJPanel.add(ticketViewer, BorderLayout.CENTER);

		splitPane.setRightComponent(rightJPanel);
		splitPane.setLeftComponent(leftJPanel);

		pack();
		
		splitPane.setDividerLocation(0.25);
	}
	
	public void update() {
		arborescence.updateUI();
		ticketViewer.updateUI();
	}
	
	/*
	 * ticketScrollPane must be initialized
	 */
	private void constructTicketTree() {
		UserPanel user = ClientDB.findUserAll(this.user, true);
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Tickets");
		//System.out.println(user);
		if (!user.getTicketList().isEmpty()) {
			for (TicketPanel ticket : user.getTicketList()) {
				DefaultMutableTreeNode ticketNode = new DefaultMutableTreeNode(ticket);
				top.add(ticketNode);
			}
		}
		arborescence = new JTree(top);
		arborescence.setAutoscrolls(true);
		arborescence.setScrollsOnExpand(true);
		arborescence.setToggleClickCount(1);
		arborescence.addTreeSelectionListener(this);
		ticketScrollPane.setViewportView(arborescence);
		//update();
	}
	
	public void updateTicketTree(TicketPanel tp) {
		//DefaultMutableTreeNode top = (DefaultMutableTreeNode) arborescence.getModel().getRoot();
		//top.add(new DefaultMutableTreeNode(tp));
		constructTicketTree();
	}
	
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode node =  (DefaultMutableTreeNode) arborescence.getLastSelectedPathComponent();
		try {
			TicketPanel t = (TicketPanel) node.getUserObject();
			if(t != selectedTicket) {
				selectedTicket = t;
				displayTicket(t);
			}
		} catch (ClassCastException ec) {
			// expected to happen when user click on top node
		} 
	}
	
	private void newTicketButtonAction() {
		new Interface_CreationTicket().setVisible(true);
	}
	
	private void displayTicket(TicketPanel ticket) {
		ticketViewer.setViewportView(ticket);
		ticket.loadMessage();
		pack();
	}

	private void saisieMessageMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_saisieMessageMouseClicked

		Component component = evt.getComponent();
		if (saisieMessage.equals(component) && texteSaisieMessage.equals(saisieMessage.getText())) {
			saisieMessage.setText(null);
		}
	}

	private void sendMessage() {
		try {
			MainClient.comm.send(new Message(Commands.SEND, Id.DEFAULT_ID_MESSAGE, user, selectedTicket.getId(), 0L, StatusType.MESSAGE_PENDING, saisieMessage.getText()));
			Packet resp = MainClient.comm.receive();
			if((resp == null) || (resp.getCommand() & Commands.FAIL) == Commands.FAIL) {
    			JOptionPane.showMessageDialog(null, "Le serveur a refuser la creation du message .", "Erreur",JOptionPane.ERROR_MESSAGE);
        	} else {
        		Message m = (Message)resp;
        		ClientDB.add(m);
        		selectedTicket.add(ClientDB.findMessage(m.getId()));
        		ticketViewer.updateUI();
        	}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private javax.swing.JTree arborescence;
	private javax.swing.JPanel leftJPanel;
	private javax.swing.JPanel rightJPanel;
	private javax.swing.JScrollPane textPanel;
	private javax.swing.JScrollPane ticketViewer;
	private javax.swing.JScrollPane ticketScrollPane;
	private javax.swing.JTextArea saisieMessage;
	private JSplitPane splitPane;
	private JButton newTicketButton;
}
