package server;


import java.sql.SQLException;

import client.Categorie;
import client.Etat;

public class Add {
	private static String sql;
	private static String sql1;
	
	protected int addGroup (long val , String nom,java.sql.Statement state) {
		int i = 0 ;
		sql = "SELECT * FROM groupe WHERE idGroupe= '"+val+"';";
		try {
			if( state.executeQuery(sql).next()) {
				System.out.println("la clé "+val+" existe deja.");
				return -1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return i;
		}
		sql = "INSERT INTO groupe (idGroupe, nomGroupe) VALUES ( '"+val+"','"+nom+"');";	
		try {
			i = state.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	
	protected int addUtilisateur (long id, String motDePasse, String nomUtilisateur ,String prenom ,Categorie categorie,java.sql.Statement state) {
		int i = 0;
		sql = "SELECT * FROM utilisateur WHERE idUtilisateur = '"+id+"';";
		try {
			if( state.executeQuery(sql).next()) {
				System.out.println("la clé "+id+" existe deja.");
				return -1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("errre");
			e.printStackTrace();
			return i;
		}
		sql = "INSERT INTO utilisateur (idUtilisateur, motDePasse, nomUtilisateur, prenom ,categorie) VALUES ( '"+id+"',md5('"+motDePasse+"'),'"+nomUtilisateur+"', '"+prenom+"','"+categorie+"');";	
		try {
			i = state.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	
	protected int addPosseder (long idgrp , long idutil,java.sql.Statement state) {
		int i = 0 ;
		sql = "SELECT * FROM posseder WHERE idGrp= '"+idgrp+"' AND idUtil = '"+idutil+"';";
		try {
			if( state.executeQuery(sql).next() ) {
				System.out.println(" l'utilisateur: "+idgrp+" est deja dans le groupe: "+idutil);
				return i;
			}else {
				System.out.println("fgh");
			}
			sql = "INSERT INTO posseder (idGrp, idUtil) VALUES ( '"+idgrp+"','"+idutil+"');";	
			try {
				i = state.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return i;
		}	
	}
	
	protected int addMessage (long id , String date , long auteur ,String text,java.sql.Statement state) {
		int i = 0 ;
		sql = "SELECT * FROM message WHERE idMessage = '"+id+"';";
		try {
			if( state.executeQuery(sql).next()) {
				System.out.println("la clé "+id+" existe deja.");
				return -1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return i;
		}
		sql = "INSERT INTO message (idMessage,dateMessage , auteur , message) VALUES ( '"+id+"','"+date+"','"+auteur+"','"+text+"');";	
		try {
			i = state.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	protected int addstatus (long idlecteur,long idMessage, Etat etat,java.sql.Statement state) {
		int i = 0 ;
		sql = "SELECT * FROM status WHERE idLecteur= '"+idlecteur+"' AND idMess ='"+idMessage+"' ;";
		try {
			if( state.executeQuery(sql).next()) {
				System.out.println("doublon : ce status est deja crée");
				return -1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return i;
		}
		sql = "INSERT INTO status (idLecteur, idMess,etat) VALUES ( '"+idlecteur+"','"+idMessage+"','"+etat+"');";	
		try {
			i = state.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	protected int adddestinataire (long idmess , long idgrp ,java.sql.Statement state) {
		int i = 0 ;
		sql = "SELECT * FROM destinataire WHERE idGrpDestinataire= '"+idgrp+"' AND idMes = '"+idmess+"';";
		try {
			if( state.executeQuery(sql).next()) {
				System.out.println("doublon : destinataire est deja crée");
				return -1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return i;
		}
		sql = "INSERT INTO destinataire (idMes, idGrpDestinataire) VALUES ( '"+idmess+"','"+idgrp+"');";	
		try {
			i = state.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
}
