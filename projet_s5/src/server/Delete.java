package server;

import java.sql.SQLException;

public class Delete {
	private static String sql;
	private int executeUpdate(java.sql.Statement state ) {
		int i = 0;
		try {
			i = state.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	
	protected int deletegroup (long val,java.sql.Statement state ) {
		int i= 0;
		sql = "DELETE FROM groupe where  idGroupe='"+val+"';";
		i = executeUpdate(state);
		return i;
	}
}
