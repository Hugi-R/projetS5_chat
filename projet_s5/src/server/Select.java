package server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Select {
	private static String sql;
	
	protected long searchUtil (String motDePasse, String nomUtilisateur ,String prenom,java.sql.Statement state) {
		long i = -1;
		sql = "SELECT * FROM utilisateur WHERE motDePasse = MD5('"+motDePasse+"') AND prenom = '"+prenom+"' AND nomUtilisateur = '"+nomUtilisateur+"';";
		try {
		ResultSet r = state.executeQuery(sql);
		if(r.next()){ 
			i=r.getLong(1);
		}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return i;
	}
	
	protected List<Long> idMessageOfTicket (long idTicket,java.sql.Statement state) {
		List<Long> l = new ArrayList<>();
		sql = "SELECT * FROM ticket WHERE idTicket = '"+idTicket+"';";
		try {
			ResultSet r = state.executeQuery(sql);
			while(r.next()){ 
				l.add(r.getLong(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}
	protected List<Long> idutilInGroup (long idGrp,java.sql.Statement state) {
		List<Long> l = new ArrayList<>();
		sql = "SELECT * FROM posseder WHERE idGrp = '"+idGrp+"';";
		try {
			ResultSet r = state.executeQuery(sql);
			while(r.next()){ 
				l.add(r.getLong(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}
	protected List<Long> RecupMessageDest (long idDest,long date,java.sql.Statement state){
		List<Long> l = new ArrayList<>();
		sql = "SELECT DISTINCT * FROM posseder,destinataire,message WHERE idUtil = '"+idDest+"' AND idGrp = idGrpDestinataire AND dateMessage >'"+date+"';";
		try {
			ResultSet r = state.executeQuery(sql);
			while(r.next()){ 
				l.add(r.getLong(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	
	}
}
