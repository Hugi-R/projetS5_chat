package server;


import java.sql.SQLException;

public class Add {
	private static String sql;
	
	protected int addgroup (long val , String nom,java.sql.Statement state) {
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
}
