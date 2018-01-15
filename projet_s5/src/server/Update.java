package server;

import java.sql.SQLException;

import client.Categorie;
import utils.StatusType;

public class Update {
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
	protected int changePasswd (long idUtil ,String newPasswd,java.sql.Statement state ) {
		int i = 0;
		sql="UPDATE utilisateur SET motDePasse = MD5('"+newPasswd+"') WHERE idUtilisateur='"+idUtil+"';";
		i = executeUpdate(state);
		return i;
	}
	
	protected int changeStatusToRead( long idMess , long idUtil,java.sql.Statement state) {
		int i=0;
		sql="UPDATE status SET etat = '"+StatusType.USER_READ+"' WHERE idMessageStatus = '"+idMess+"' AND idLecteur = '"+idUtil+"';";
		i = executeUpdate(state);
		return i;
	}
	protected int changeCategorie (long idUtil ,Categorie cat,java.sql.Statement state ) {
		int i = 0;
		sql="UPDATE utilisateur SET categorie = '"+cat+"' WHERE idUtilisateur='"+idUtil+"';";
		i = executeUpdate(state);
		return i;
	}
	
	protected int changeMessage(long idMessage,String newTexte,java.sql.Statement state) {
		int i = 0;
		sql="UPDATE message SET message = '"+newTexte.replace("'", "''")+"' WHERE idUtilisateur='"+idMessage+"';";
		i = executeUpdate(state);
		return i;
	}
	protected int changeUser(long idUser ,String name,String firstName , String mail , Categorie cat,java.sql.Statement state) {
		int i = 0;
		sql="UPDATE utilisateur SET nomUtilisateur = '"+name+"' , prenom = '"+firstName+"' , courriel = '"+mail+"',categorie ='"+cat+"' WHERE idUtilisateur = '"+idUser+"';";
		i = executeUpdate(state);
		return i;
	}
}
