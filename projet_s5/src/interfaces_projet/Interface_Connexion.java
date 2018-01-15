package interfaces_projet;

import java.io.IOException;

import javax.swing.JOptionPane;

import client.MainClient;
import packet.Commands;
import packet.Connect;
import packet.Packet;
import packet.User;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;


public class Interface_Connexion extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;
	static final String texteSaisieMDP = "mot de passe";
    static final String texteSaisieIdentifiant = "identifiant";
    /**
     * Creates new form Interface_Connexion
     */
    public Interface_Connexion() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        saisieIdentifiant = new javax.swing.JTextField();
        boutonConnexion = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();
        labelId = new javax.swing.JLabel();
        labelMdp = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("connexion");
        setAlwaysOnTop(true);
        setResizable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));


        boutonConnexion.setText("se connecter");
        boutonConnexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boutonConnexionActionPerformed();
            }
        });

        labelId.setText("identifiant");

        labelMdp.setText("mot de passe");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(boutonConnexion)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(labelId))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(labelMdp)
                        .addComponent(saisieIdentifiant, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(107, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(labelId)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saisieIdentifiant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelMdp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(boutonConnexion)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3Layout.setHorizontalGroup(
        	jPanel3Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel3Layout.createSequentialGroup()
        			.addGap(24)
        			.addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
        	jPanel3Layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(Alignment.LEADING, jPanel3Layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3.setLayout(jPanel3Layout);

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }

    private void boutonConnexionActionPerformed() {
        String username = saisieIdentifiant.getText();
		String password = jPasswordField1.getText();
        Packet resp = null;
        
        try {
			MainClient.comm.send(new Connect(username, password));
			resp = MainClient.comm.receive();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	//System.out.println(resp);
        if((resp == null) || (resp.getCommand() & Commands.FAIL) == Commands.FAIL) {
        	JOptionPane.showMessageDialog(this.boutonConnexion, "Connection refuse.",
				    "Erreur",JOptionPane.ERROR_MESSAGE);
            jPasswordField1.setText("");
        } else {
            MainClient.setConnectedUser(((User) resp).getId());
            this.dispose();
        }
    }

    private javax.swing.JButton boutonConnexion;
    private javax.swing.JLabel labelId;
    private javax.swing.JLabel labelMdp;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField saisieIdentifiant;
}
