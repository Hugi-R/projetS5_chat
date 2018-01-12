/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces_projet;

import java.awt.Component;

import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import client.MainClient;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.BorderLayout;

/**
 *
 * @author adrian
 */
public class Interface_Utilisateur_principale extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;
	static String texteSaisieMessage = "Saisissez votre texte ici.";
	private UserPanel user;

	/**
	 * Creates new form JFrame
	 */
	public Interface_Utilisateur_principale(UserPanel user) {
		this.user = user;
		initComponents();
        this.setLocationRelativeTo(null);

		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				if (JOptionPane.showConfirmDialog(null, "Are you sure to close this window?", "Really Closing?",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					// action on closing
					MainClient.comm.close();
					System.exit(0);
				}
			}
		});
		this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jPanelPincipal = new javax.swing.JPanel();
		jPanel1 = new javax.swing.JPanel();
		jScrollPane4 = new javax.swing.JScrollPane();
		arborescence = new javax.swing.JTree();
		jPanel2 = new javax.swing.JPanel();
		jPanel3 = new javax.swing.JPanel();
		jScrollPaneMessageTicket = new javax.swing.JScrollPane();
		
        setTitle("forum universite");
        
		jPanelPincipal.setLayout(new java.awt.GridLayout(1, 3));

		jPanel1.setAlignmentX(0.0F);
		jPanel1.setAlignmentY(0.0F);
		jPanel1.setPreferredSize(arborescence.getPreferredSize());
		jPanel1.setLayout(new java.awt.BorderLayout());

		jScrollPane4.setAlignmentX(0.0F);
		jScrollPane4.setAutoscrolls(true);
		jScrollPane4.setPreferredSize(new java.awt.Dimension(100, 400));

		DefaultMutableTreeNode treeNode1 = new DefaultMutableTreeNode("test");
		if (!user.getGroupList().isEmpty()) {
			Iterable<GroupPanel> iterable2 = user.getGroupList();
			for (GroupPanel groupe : iterable2) {
				DefaultMutableTreeNode treeNode2 = new DefaultMutableTreeNode(groupe.getName());
				if (!groupe.getTicketList().isEmpty()) {
					Iterable<TicketPanel> iterable3 = groupe.getTicketList();
					System.out.println("iterable3=" + iterable3.toString());
					for (TicketPanel ticket : iterable3) {
						DefaultMutableTreeNode treeNode3 = new DefaultMutableTreeNode(ticket.getName());
						treeNode2.add(treeNode3);
					}
				}
				treeNode1.add(treeNode2);
			}
		}

		arborescence.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));

		arborescence.setAlignmentX(0.0F);
		arborescence.setAlignmentY(0.0F);
		arborescence.setAutoscrolls(true);
		arborescence.setMaximumSize(new java.awt.Dimension(4000, 1000));
		arborescence.setPreferredSize(new java.awt.Dimension(150, 300));
		arborescence.setScrollsOnExpand(true);
		arborescence.setToggleClickCount(1);
		jScrollPane4.setViewportView(arborescence);

		jPanel1.add(jScrollPane4, java.awt.BorderLayout.CENTER);

		jPanelPincipal.add(jPanel1);

		jPanel2.setLayout(new java.awt.BorderLayout());

		jPanel3.setLayout(new java.awt.BorderLayout());

		jPanel2.add(jPanel3, java.awt.BorderLayout.SOUTH);
		jScrollPane1 = new javax.swing.JScrollPane();
		saisieMessage = new javax.swing.JTextArea();

		saisieMessage.setColumns(20);
		saisieMessage.setRows(5);
		saisieMessage.setText(texteSaisieMessage);
		saisieMessage.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				saisieMessageMouseClicked(evt);
			}
		});
		jScrollPane1.setViewportView(saisieMessage);

		jScrollPane3 = 	new javax.swing.JScrollPane();
		ticketView = new TicketPanel(0L, "", this.user, new GroupPanel(0L, "group"), new ArrayList<>());
		jScrollPane3.add(ticketView);
		jScrollPane3.setViewportView(ticketView);
		
		jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);
		jPanel3.add(jScrollPane3, BorderLayout.NORTH);

		jPanelPincipal.add(jPanel2);

		getContentPane().add(jPanelPincipal, java.awt.BorderLayout.CENTER);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void saisieMessageMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_saisieMessageMouseClicked

		Component component = evt.getComponent();
		if (saisieMessage.equals(component) && texteSaisieMessage.equals(saisieMessage.getText())) {
			saisieMessage.setText(null);
		}
	}// GEN-LAST:event_saisieMessageMouseClicked

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
		// (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
		 * look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Interface_Utilisateur_principale.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Interface_Utilisateur_principale.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Interface_Utilisateur_principale.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Interface_Utilisateur_principale.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				List<TicketPanel> ticketList = new ArrayList<>();
				List<GroupPanel> groupList = new ArrayList<>();

				GroupPanel group = new GroupPanel(2, "Eleve");
				groupList.add(group);
				UserPanel Hugo = new UserPanel(1L, "ROUSSEL", "Hugo", "Eleve", ticketList, groupList);
				List<Long> messages = new ArrayList<>();
				ticketList.add(new TicketPanel(3L, "Test ticket", Hugo, group, messages));
				messages.add(32L);

				new Interface_Utilisateur_principale(Hugo).setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JTree arborescence;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanelPincipal;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JScrollPane jScrollPane4;
	private javax.swing.JScrollPane jScrollPaneMessageTicket;
	private javax.swing.JTextArea saisieMessage;
	private TicketPanel ticketView;
	// End of variables declaration//GEN-END:variables
}
