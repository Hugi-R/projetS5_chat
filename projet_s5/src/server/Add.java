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
	
	protected int addUtilisateur (long id, String motDePasse, String nomUtilisateur ,String prenom ,String courriel,Categorie categorie,java.sql.Statement state) {
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
	
	protected int addPosseder (long idgrp , long idutil,java.sql.Statement state) {
		int i = 0 ;
		sql = "SELECT * FROM posseder WHERE idGrp= '"+idgrp+"' AND idUtil = '"+idutil+"';";
		if( foundValues(state) ) {
			System.err.println("[KO] l'utilisateur: "+idgrp+" est deja dans le groupe: "+idutil);
			return i;
		}
		sql = "INSERT INTO posseder (idGrp, idUtil) VALUES ( '"+idgrp+"','"+idutil+"');";	
		i =executeUpdate(state);
		return i;
			
	}
	
	protected int addMessage (long id , long auteur ,String text,java.sql.Statement state) {
		int i = 0 ;
		sql = "SELECT * FROM message WHERE idMessage = '"+id+"';";
			if( foundValues(state)) {
				System.err.println("[KO] le message "+id+" existe deja.");
				return -1;
			}
		sql = "INSERT INTO message (idMessage,dateMessage , auteur , message) VALUES ( '"+id+"',NOW(),'"+auteur+"','"+text+"');";	
		i =executeUpdate(state);
		return i;
	}
	
	protected int addStatus (long idlecteur,long idMessage, Etat etat,java.sql.Statement state) {
		int i = 0 ;
		sql = "SELECT * FROM status WHERE idLecteur= '"+idlecteur+"' AND idMess ='"+idMessage+"' ;";
		if( foundValues(state)) {
			System.err.println("[KO] le message:"+idMessage+" possede deja un status pour le destinataire:"+idlecteur);
			return -1;
		}
		sql = "INSERT INTO status (idLecteur, idMess,etat) VALUES ( '"+idlecteur+"','"+idMessage+"','"+etat+"');";	
		i =executeUpdate(state);
		return i;
	}
	
	protected int addDestinataire (long idmess , long idgrp ,java.sql.Statement state) {
		int i = 0 ;
		sql = "SELECT * FROM destinataire WHERE idGrpDestinataire= '"+idgrp+"' AND idMes = '"+idmess+"';";
		if( foundValues(state)) {
			System.err.println("[KO] le message:"+idmess+" possede deja un destinataire:"+idgrp);
			return -1;
		}
		sql = "INSERT INTO destinataire (idMes, idGrpDestinataire) VALUES ( '"+idmess+"','"+idgrp+"');";	
		i =executeUpdate(state);
		return i;
	}
	
	protected int addTicket (long idTicket , long idMessage,java.sql.Statement state) {
		int i = 0 ;
		sql = "SELECT * FROM ticket WHERE idTicket= '"+idTicket+"' AND idMessa = '"+idMessage+"';";
		if( foundValues(state)) {
			System.err.println("[KO] Le ticket"+idTicket+" possede deja le message:"+idMessage);
				return -1;
		}
		sql = "INSERT INTO ticket (idTicket, idMessa) VALUES ( '"+idTicket+"','"+idMessage+"');";	
		i = executeUpdate(state);
		return i;
	}
}
