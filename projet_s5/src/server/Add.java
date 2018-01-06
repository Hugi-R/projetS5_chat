package server;


import java.sql.SQLException;

import client.Categorie;
import client.Etat;

public class Add {
	private String sql;
	
	private int executeUpdate(java.sql.Statement state ) {
		int i = 0;
		try {
			i = state.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	private boolean foundValues (java.sql.Statement state ) {
		try {
			return state.executeQuery(sql).next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	protected int addGroup (long val , String nom,java.sql.Statement state) {
		int i = 0 ;
		sql = "SELECT * FROM groupe WHERE idGroupe= '"+val+"';";
		if( foundValues(state)) {
			System.err.println("[KO] le groupe: "+val+" existe deja.");
			return -1;
		}
		sql = "INSERT INTO groupe (idGroupe, nomGroupe) VALUES ( '"+val+"','"+nom+"');";
		i = executeUpdate(state);
		return i;
	}
	
	protected int addUser (long id, String motDePasse, String nomUtilisateur ,String prenom ,String courriel,Categorie categorie,java.sql.Statement state) {
		int i = 0;
		sql = "SELECT * FROM utilisateur WHERE idUtilisateur = '"+id+"';";
		if( foundValues(state)) {
			System.err.println("[KO] l'utilisateur: "+id+" existe deja.");
			return -1;
		}
		sql = "INSERT INTO utilisateur (idUtilisateur, motDePasse, nomUtilisateur, prenom ,courriel,categorie) VALUES ( '"+id+"',SHA1('"+motDePasse+"'),'"+nomUtilisateur+"', '"+prenom+"','"+courriel+"','"+categorie+"');";	
		i =executeUpdate(state);
		return i;
	}
	
	protected int addGroupToUser (long idgrp , long idutil,java.sql.Statement state) {
		int i = 0 ;
		sql = "SELECT * FROM posseder WHERE idGroupePosseder= '"+idgrp+"' AND idUtilisateurPosseder = '"+idutil+"';";
		if( foundValues(state) ) {
			System.err.println("[KO] l'utilisateur: "+idgrp+" est deja dans le groupe: "+idutil);
			return i;
		}
		sql = "INSERT INTO posseder (idGroupePosseder, idUtilisateurPosseder) VALUES ( '"+idgrp+"','"+idutil+"');";	
		i =executeUpdate(state);
		return i;
			
	}
	protected int addMessage (long id , long auteur,long idTicket ,String text,java.sql.Statement state) {
		int i = 0 ;
		sql = "SELECT * FROM message WHERE idMessage = '"+id+"';";
			if( foundValues(state)) {
				System.err.println("[KO] le message "+id+" existe deja.");
				return -1;
			}
		sql = "INSERT INTO message (idMessage,dateMessage , auteur ,idTicketMessage, message) VALUES ( '"+id+"',NOW(),'"+auteur+"','"+idTicket+"','"+text+"');";	
		i =executeUpdate(state);
		return i;
	}
	
	protected int addStatus (long idlecteur,long idMessage, Etat etat,java.sql.Statement state) {
		int i = 0 ;
		sql = "SELECT * FROM status WHERE idLecteur= '"+idlecteur+"' AND idMessageStatus ='"+idMessage+"' ;";
		if( foundValues(state)) {
			System.err.println("[KO] le message:"+idMessage+" possede deja un status pour le destinataire:"+idlecteur);
			return -1;
		}
		sql = "INSERT INTO status (idLecteur, idMessageStatus,etat) VALUES ( '"+idlecteur+"','"+idMessage+"','"+etat+"');";	
		i =executeUpdate(state);
		return i;
	}
	
	protected int addTicket (long idTicket , long idGroup,String objet,java.sql.Statement state) {
		int i = 0 ;
		sql = "SELECT * FROM ticket WHERE idTicket= '"+idTicket+"' AND idGroupeDestinataire = '"+idGroup+"';";
		if( foundValues(state)) {
			System.err.println("[KO] Le ticket"+idTicket+" existe deja");
				return -1;
		}
		sql = "INSERT INTO ticket (idTicket, idGroupeDestinataire,objet) VALUES ( '"+idTicket+"','"+idGroup+"','"+objet+"');";	
		i = executeUpdate(state);
		return i;
	}
}
