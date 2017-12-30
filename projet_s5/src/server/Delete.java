package server;

import java.sql.SQLException;

public class Delete {
	private static String sql;
	
	protected int deletegroup (long val,java.sql.Statement state ) {
		int i= 0;
		sql = "DELETE FROM groupe where  idGroupe='"+val+"';";
		try {
			i = state.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
}
