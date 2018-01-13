/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces_projet;

import java.io.IOException;

import javax.swing.JOptionPane;

import client.ClientDB;
import client.MainClient;
import packet.Commands;
import packet.Message;
import packet.Packet;
import packet.Ticket;
import utils.Id;
import utils.StatusType;


public class Interface_CreationTicket extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;
    private GroupPanel[] groups;

    /**
     * Creates new form Interface_Connexion
     */
    public Interface_CreationTicket() {
    	System.out.println(MainClient.getConnectedUser());
    	groups = MainClient.getConnectedUser().getGroupList().toArray(new GroupPanel[0]);
    	System.out.println(groups);
        initComponents();
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {

        saisieIntitule = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        saisieMessage = new javax.swing.JTextArea();
        BoutonAnnuler = new javax.swing.JButton();
        BoutonCreer = new javax.swing.JButton();
        listGroups = new javax.swing.JComboBox<>();
        labelIntitule = new javax.swing.JLabel();
        labelGroupDest = new javax.swing.JLabel();
        labelMessage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("nouveau ticket");
        setAlwaysOnTop(true);
        setResizable(false);

        saisieMessage.setColumns(20);
        saisieMessage.setRows(5);
        jScrollPane1.setViewportView(saisieMessage);

        BoutonAnnuler.setText("Annuler");
        BoutonAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoutonAnnulerActionPerformed(evt);
            }
        });

        BoutonCreer.setText("Creer");
        BoutonCreer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoutonCreerActionPerformed();
            }
        });

        listGroups.setModel(new javax.swing.DefaultComboBoxModel<>(groups));
        listGroups.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listGroupsActionPerformed(evt);
            }
        });

        labelIntitule.setText("Intitule");

        labelGroupDest.setText("groupe destinataire");

        labelMessage.setText("Message");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(BoutonAnnuler)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BoutonCreer)
                .addGap(70, 70, 70))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(labelMessage)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(130, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(listGroups, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saisieIntitule, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(labelIntitule))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(labelGroupDest)))
                .addGap(130, 130, 130))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelIntitule)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saisieIntitule, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelGroupDest)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(listGroups, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelMessage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BoutonAnnuler)
                    .addComponent(BoutonCreer))
                .addGap(20, 20, 20))
        );

        pack();
    }

    private void BoutonAnnulerActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    private void listGroupsActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO potentiellement a supprimer
    	
    }

    private void BoutonCreerActionPerformed() {
    	long idGroup = ((GroupPanel)listGroups.getSelectedItem()).getId();
        String textMessage = saisieMessage.getText();
        String textIntitule = saisieIntitule.getText();
        Packet resp = null;
        if( !textIntitule.isEmpty() && !textMessage.isEmpty()) {
        	Ticket ticket = new Ticket(Commands.SEND, Id.DEFAULT_ID_TICKET, MainClient.getConnectedUser().getId(), idGroup, textIntitule, null);
        	try {
				MainClient.comm.send(ticket);
				resp = MainClient.comm.receive();
				System.out.println("recu : "+resp);
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this.BoutonCreer, "Erreur de reseau :\n"+e.getStackTrace(),
    				    "Erreur",JOptionPane.ERROR_MESSAGE);
			}
        	if((resp == null) || (resp.getCommand() & Commands.FAIL) == Commands.FAIL) {
    			JOptionPane.showMessageDialog(this.BoutonCreer, "Erreur pour la creation du ticket.",
    				    "Erreur",JOptionPane.ERROR_MESSAGE);
        		
        	} else {
        		Ticket t = (Ticket) resp;
        		ClientDB.add(t);
        		//TODO update display for ticket
        		Message messageToSend = new Message(Commands.SEND, Id.DEFAULT_ID_MESSAGE, MainClient.getConnectedUser().getId(), t.getId(), 0L, StatusType.MESSAGE_PENDING, textMessage);
        		try {
					MainClient.comm.send(messageToSend);
					resp = MainClient.comm.receive();
					System.out.println("recu : "+resp);
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(this.BoutonCreer, "Erreur de reseau :\n"+e.getStackTrace(),
	    				    "Erreur",JOptionPane.ERROR_MESSAGE);
				}
        		if((resp == null) || (resp.getCommand() & Commands.FAIL) == Commands.FAIL) {
        			JOptionPane.showMessageDialog(this.BoutonCreer, "Erreur pour la creation du message .",
	    				    "Erreur",JOptionPane.ERROR_MESSAGE);
            		
            	} else {
            		ClientDB.add((Message)resp);
            	}
        	}
        	this.dispose();
        }else {
        	JOptionPane.showMessageDialog(this.BoutonCreer, "Veuillez tout completer.",
				    "Erreur",JOptionPane.ERROR_MESSAGE);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BoutonAnnuler;
    private javax.swing.JButton BoutonCreer;
    private javax.swing.JTextField saisieIntitule;
    private javax.swing.JLabel labelIntitule;
    private javax.swing.JLabel labelGroupDest;
    private javax.swing.JLabel labelMessage;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<GroupPanel> listGroups;
    private javax.swing.JTextArea saisieMessage;
    // End of variables declaration//GEN-END:variables
}
