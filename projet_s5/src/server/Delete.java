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
	
	protected int deletegroup (long idGroup,java.sql.Statement state ) {
		int i= 0;
		sql = "DELETE FROM groupe WHERE  idGroupe='"+idGroup+"';";
		i += executeUpdate(state);
		return i;
	}
	protected int takeUserOutOfGroup (long idGroup , long idUser ,java.sql.Statement state) {
		int i= 0;
		sql = "DELETE FROM posseder WHERE  idGroupePosseder='"+idGroup+"' AND idUtilisateurPosseder = '"+idUser+"';";
		i = executeUpdate(state);
		return i;
	}
	protected int deleteUser (long idUser ,java.sql.Statement state) {
		int i= 0;
		sql="UPDATE utilisateur SET motDePasse = NULL,courriel = NULL WHERE idUtilisateur='"+idUser+"';";
		i = executeUpdate(state);
		return i;
	}
}
