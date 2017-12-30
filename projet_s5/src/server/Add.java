package server;


import java.sql.SQLException;

public class Add {
	private static String sql;
	
	protected int addgroup (long val , String nom,java.sql.Statement state) {
		int i = 0 ;
		sql = "INSERT INTO groupe (idGroupe, nomGroupe) VALUES ( '"+val+"','"+nom+"');";	
		try {
			i = state.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
}
