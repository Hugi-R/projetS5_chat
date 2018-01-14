package client;

import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import interfaces_projet.Interface_Connexion;
import interfaces_projet.Interface_Utilisateur_principale;
import packet.Commands;
import packet.ContentType;
import packet.Group;
import packet.ListOfGroup;
import packet.Packet;
import packet.Request;
import utils.Id;

public class MainClient {
	public static String serverAdress;
	public static int serverPort;
	public static CommunicatorClient comm; //public because a getter will not be very useful here
	public static List<Group> allGroups;
	private static long user;
	public static Interface_Utilisateur_principale ui;
	private static Interface_Connexion connectUI;
	
	public static void main(String[] args) {
		System.out.println("Lancement du client");
		if(args.length == 3) {
			serverAdress = args[1];
			serverPort = Integer.parseInt(args[2]);
		} else {
			serverAdress = "localhost";
			serverPort = 3636;
		}
		initConnectionServer();
		launchConnectUI();
		comm.listen();
	}
	
	public static void initConnectionServer() {
		if(comm == null) {
			comm = new CommunicatorClient(serverAdress, serverPort);
			try {
				comm.open();
			} catch (IOException e) {
				new JOptionPane();
				JOptionPane.showMessageDialog(null, "Cannot connect to server \""+serverAdress+":"+serverPort+"\" for reason \""+e.getMessage()+"\"", "Connection Error", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				comm.close();
				comm = null;
			}
		}
	}
	
	public static void launchMainUI() {
		if(ui == null && comm != null) {
			System.out.println("Starting main ui for "+user);
			ui = new Interface_Utilisateur_principale(user);
			ui.setVisible(true);
		}
	}
	
	public static void launchConnectUI() {
		if(connectUI == null && comm != null) {
			user = 0;
			connectUI = new Interface_Connexion();
			connectUI.setVisible(true);
		}
	}
	
	public static void setConnectedUser(long u) {
		flush(false);
		user = u;
		ClientDB.findUserAll(user);
		MainClient.retrieveAllGroups();
		launchMainUI();
	}
	
	public static long getConnectedUser() {
		return user;
	}
	
	public static void flush(boolean withComm, boolean withUser) {
		if(withComm) {
			comm.close();
			comm = null;
		}
		if(withUser)
			user = 0;
		if(ui != null) {
			ui.dispose();
			ui = null;
		}
		if(connectUI != null) {
			connectUI.dispose();
			connectUI = null;
		}
		ClientDB.flush();
	}
	
	public static void flush(boolean withComm) {
		flush(withComm, false);
	}
	
	public static void close() {
		flush(true);
		System.out.println("Goodbye");
		System.exit(0);
		
	}
	
	public static void retrieveAllGroups() {
		try {
			comm.send(new Request((byte)(Commands.RETRIEVE | Commands.ALL), Id.DEFAULT_ID_GROUP));
			Packet p = comm.receive();
			if((p.getCommand() & Commands.FAIL) == Commands.FAIL) {
				allGroups = null;
			} else {
				ListOfGroup l = (ListOfGroup) p;
				if(Id.type(l.getId()) == ContentType.GROUP) {
					allGroups = (List<Group>)l.getListId();
				} else {
					allGroups = null;
				}
			}
		} catch (IOException e) {
			allGroups = null;
			e.printStackTrace();
		}
		if(allGroups == null)
			System.err.println("Retrieve of all groups failled, some part of the application will not work properly.");
	}
	
	public static void repaint() {
		ui.repaint();
	}

}
