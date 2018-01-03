package server;

import java.sql.SQLException;

import client.Etat;


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
	
	protected int changeStatus( long idMess , long idUtil,Etat etat,java.sql.Statement state) {
		int i=0;
		sql="UPDATE status SET etat = '"+etat+"' WHERE idMess = '"+idMess+"' AND idLecteur = '"+idUtil+"';";
		i = executeUpdate(state);
		return i;
	}
}
