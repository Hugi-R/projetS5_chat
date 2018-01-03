package server;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import client.Categorie;
import client.Etat;

public class Database {
	private static Connection conn = null;
	private static java.sql.Statement state = null ;
	private static Add add = new Add();
	private static Delete delete = new Delete();
	private static Select select = new Select();
	private static Update update = new Update();
	
	public static int start(String url , String user , String passwd) {
		int i =0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("[OK] driver charge");
			
			conn = DriverManager.getConnection(url, user, passwd);
			System.out.println("[OK] connection etablie");
			state = conn.createStatement();		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			i=1;
		}
		return i;
	}
	
	public static int closeConnection() {
		if (isStarted()) {
			try {
				state.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("[OK] connection close");
			return 1;
		}else {
			System.out.println("[KO] connection deja close ");
			return -1;
		}
		
	}
	
	public static boolean isStarted() {
		if (state == null) {
			System.out.println("[KO] la connection n'a pas été établie");
			return false;
		}
		return true ;
	}
/* fonction d'ajout de donnée dans la base *******************/
	public static int addgroup(long val, String nom){
		if (isStarted())
			return add.addGroup(val,nom,state);
		else
			return -1;
	}
	public static int addutilisateur(long id, String motDePasse, String nomUtilisateur ,String prenom ,Categorie categorie){
			if (isStarted())
				return add.addUtilisateur(id, motDePasse, nomUtilisateur, prenom, categorie, state);
			else
				return -1;
	}
	protected static int addposseder (long idgrp , long idutil) {
		if (isStarted())
			return add.addPosseder(idgrp, idutil, state);
		else
			return -1;
	}
	protected static int addmessage (long id , long date , long auteur,String text) {
		if (isStarted())
			return add.addMessage(id, date, auteur, text, state);
		else
			return -1;
	}
	protected static int addstatus (long idlecteur,long idMessage, Etat etat) {
		if (isStarted())
			return add.addStatus(idlecteur, idMessage, etat, state);
		else
			return -1;
	}
	protected static int adddestinataire (long idmess , long idgrp) {
		if (isStarted())
			return add.addDestinataire(idmess, idgrp, state);
		else
			return -1;
	}
	protected static int addticket (long idTicket , long idMessage) {
		if (isStarted())
			return add.addTicket(idTicket, idMessage, state);
		else
			return -1;
	}
	
/*fonction de suppression de donnée********************************/
	public static int deletegroup (long id) {
		if (isStarted())
			return delete.deletegroup(id, state);
		else
			return -1;
	}
/*fonction de selection*********************************************/
	public static long searchIdUtil (String motDePasse, String nomUtilisateur ,String prenom) {
		if (isStarted())
			return select.searchUtil(motDePasse, nomUtilisateur, prenom, state);
		else
			return -1;
	}
	public static List<Long> idMessageOfTicket (long idTicket){
		if (isStarted())
			return select.idMessageOfTicket(idTicket, state);
		else
			return null;
	}
	public static List<Long> idUtilInGroup (long idGrp){
		if (isStarted())
			return select.idutilInGroup(idGrp, state);
		else
			return null;
	}
/*fonction de changement de valeur dans la base de donnée**********/
	public static int changePasswd(long idUtil,String newPasswd) {
		if (isStarted())
			return update.changePasswd(idUtil, newPasswd, state);
		else
			return -1;
	}

	
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/projet";
		String user = "root";
		String passwd = "";
		start(url,user,passwd);
		String groupe = "sfhj";
		long val = 05645221745555L;
		long val1 = 54157045854L;
		String prenom= "sdfghj";
		String motDePasse = "retggdhjg5e";
		Categorie cat = Categorie.ETUDIANT;
		long date = 6542685L;
		 Etat etat = Etat.RECUT;
		String texte = "bonjour a salle 205 du batiment U2 a une ampoule cassée";
		/*System.out.println(addutilisateur(val, motDePasse, groupe, prenom, cat));
		System.out.println(addgroup(val1, groupe));
		System.out.println(addposseder(val1, val));
		System.out.println(addstatus(val1, val, etat));
		System.out.println(adddestinataire(val, val1));
		System.out.println(addticket(val, val1));
		String sql = "SELECT * FROM utilisateur;";*/
		/*try {
			ResultSet r = state.executeQuery(sql);
			while(r.next()){ 
				System.out.print(r.getObject(1) + "\n");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		addutilisateur(val1, "patate", "nomUtilisateur", "prenom", Categorie.INVITE);
		System.out.println(changePasswd(val1, "carrotte"));
	}

}
