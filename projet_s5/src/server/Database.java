package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import client.Categorie;
import client.Etat;

public class Database {
	private static Connection conn = null;
	private static java.sql.Statement state = null ;
	private static Add add = new Add();
	private static Delete delete = new Delete();
	
	public static int start(String url , String user , String passwd) {
		int i =0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("driver charger [OK]");
			
			conn = DriverManager.getConnection(url, user, passwd);
			System.out.println("connection [OK]");
			state = conn.createStatement();		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("PATATE");
			e.printStackTrace();
			i=1;
		}
		return i;
	}
	
	public static boolean isStarted() {
		if (state == null) {
			System.out.println("la connection n'a pas été établie");
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
	protected static int addmessage (long id , String date , long auteur,String text) {
		if (isStarted())
			return add.addMessage(id, date, auteur, text, state);
		else
			return -1;
	}
	protected static int addstatus (long idlecteur,long idMessage, Etat etat) {
		if (isStarted())
			return add.addstatus(idlecteur, idMessage, etat, state);
		else
			return -1;
	}
	protected static int adddestinataire (long idmess , long idgrp) {
		if (isStarted())
			return add.adddestinataire(idmess, idgrp, state);
		else
			return -1;
	}
	
/*fonction de suppression de donnée********************************/
	public static int deletegroup (long val) {
		if (isStarted())
			return delete.deletegroup(val, state);
		else
			return -1;
	}
	
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/projet";
		String user = "root";
		String passwd = "";
		start(url,user,passwd);
		String groupe = "sfhj";
		long val = 05645221756555L;
		long val1 = 541570458174L;
		String prenom= "sdfghj";
		String motDePasse = "retggdhjfg5e";
		Categorie cat = Categorie.ETUDIANT;
		Date current = new Date();
		 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		 String date = dateFormat.format(current);
		 Etat etat = Etat.RECUT;
		String texte = "bonjour a salle 205 du batiment U2 a une ampoule cassée";
		System.out.println(addutilisateur(val, motDePasse, groupe, prenom, cat));
		System.out.println(addgroup(val1, groupe));
		System.out.println(addposseder(val1, val));
		System.out.println(addstatus(val1, val, etat));
		System.out.println(adddestinataire(val, val1));
	}

}
