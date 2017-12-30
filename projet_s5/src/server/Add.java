package server;


import java.sql.SQLException;

import client.Categorie;

public class Add {
	private static String sql;
	
	protected int addGroup (long val , String nom,java.sql.Statement state) {
		int i = 0 ;
		sql = "SELECT * FROM groupe WHERE idGroupe= '"+val+"';";
		try {
			if( state.executeQuery(sql).next()) {
				System.out.println("la clé "+val+" existe deja.");
				return -1;
			};
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
			};
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("errre");
			e.printStackTrace();
			return i;
		}
		sql = "INSERT INTO utilisateur (idUtilisateur, motDePasse, nomUtilisateur, prenom ,categorie) VALUES ( '"+id+"',md5('"+motDePasse+"'),'"+nomUtilisateur+"', '"+prenom+"','"+categorie+"');";	
		System.out.println(sql);
		try {
			i = state.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
}
