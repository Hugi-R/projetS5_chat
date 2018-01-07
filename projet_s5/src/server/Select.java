package server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import packet.Commands;
import packet.Group;
import packet.Message;
import packet.Ticket;
import packet.User;

public class Select {
	private static String sql;
	protected long connect (String motDePasse, String mail,java.sql.Statement state) {
		long i = -1;
		sql = "SELECT idUtilisateur FROM utilisateur WHERE motDePasse = SHA1('"+motDePasse+"') AND courriel= '"+mail+"';";
		try {
			ResultSet r = state.executeQuery(sql);
			if(r.next()){ 
				System.out.println("[OK] requete connect");
				i=r.getLong(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	
	protected List<Long> idMessageOfTicket (long idTicket,java.sql.Statement state) {
		List<Long> l = new ArrayList<>();
		sql = "SELECT DISTINCT idMessage FROM message WHERE idTicketMessage = '"+idTicket+"';";
		try {
			ResultSet r = state.executeQuery(sql);
			while(r.next()){ 
				l.add(r.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}
	protected List<Long> idutilInGroup (long idGrp,java.sql.Statement state) {
		List<Long> l = new ArrayList<>();
		sql = "SELECT * FROM posseder WHERE idGroupePosseder = '"+idGrp+"';";
		try {
			ResultSet r = state.executeQuery(sql);
			while(r.next()){ 
				l.add(r.getLong(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}
	
	protected Message RecupMessage (long idMessage ,java.sql.Statement state){
		sql= "SELECT UNIX_TIMESTAMP(dateMessage),auteur,message FROM message WHERE idMessage= '"+idMessage+"';";
		Message m = null;
		try {
			ResultSet r = state.executeQuery(sql);
			if(r.next()) {
				m = new Message(Commands.SEND,idMessage,r.getLong(2),0,r.getLong(1),r.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return m;
		}
		return m;
	}
	
	private List<Long> recupListTicketOfUser (long idUser,java.sql.Statement state){
		List<Long> ListTicket = new ArrayList<>();
		sql= "SELECT DISTINCT  idTicket FROM ticket,message,posseder where idTicketMessage = idTicket AND (( auteur='"+idUser+"') OR (idGroupeDestinataire = idGroupePosseder and idUtilisateurPosseder='"+idUser+"'));";
		try {
			ResultSet r = state.executeQuery(sql);
			while(r.next()) {
				ListTicket.add(r.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ListTicket;
	}
	private List<Long> recupListGroupOfUser (long idUser,java.sql.Statement state){
		List<Long> Listgroup = new ArrayList<>();
		sql= "SELECT idGroupePosseder FROM posseder,groupe WHERE idUtilisateurPosseder = '"+idUser+"' AND idGroupePosseder = idGroupe ;";
		try {
			ResultSet r = state.executeQuery(sql);
			while(r.next()) {
				Listgroup.add(r.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Listgroup;
	}
	
	protected User recupUserWithList(long idUser,java.sql.Statement state){
		sql= "SELECT nomUtilisateur,prenom,categorie FROM utilisateur WHERE idUtilisateur ='"+idUser+"';";
		User u = null;
		try {
			ResultSet r = state.executeQuery(sql);
			if(r.next()) {
				String nom = r.getString(1);
				String prenom = r.getString(2);
				String agent = r.getString(3);
				List<Long> groups = recupListGroupOfUser(idUser,state);
				List<Long> tickets = recupListTicketOfUser(idUser, state);
				u = new User(Commands.SEND,idUser,nom,prenom,agent,groups,tickets);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
	protected User recupUserShort(long idUser,java.sql.Statement state){
		sql= "SELECT nomUtilisateur,prenom,categorie FROM utilisateur WHERE idUtilisateur ='"+idUser+"';";
		User u = null;
		try {
			ResultSet r = state.executeQuery(sql);
			if(r.next()) {
				return new User(Commands.SEND,idUser,r.getString(1),r.getString(2),r.getString(3),null,null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return u;
		}
		return u;
	}
	protected String nameOfGroup ( long idGroup ,java.sql.Statement state){
		sql="SELECT nomGroupe FROM groupe WHERE idGroupe = '"+idGroup+"';";
		try {
			ResultSet r = state.executeQuery(sql);
			if(r.next()){ 
				return r.getString(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	protected Group retrieveGroup (long idGroup,java.sql.Statement state) {
		String name;
		if ((name=nameOfGroup(idGroup, state))==null)
			return null;
		else
			return new Group(Commands.SEND, idGroup, name);
	}
	private long retrieveAuthorOfTicket (long idTicket,java.sql.Statement state){
		sql="SELECT DISTINCT auteur  FROM ticket,message WHERE idTicket='"+idTicket+"' ;";
		try {
			ResultSet r = state.executeQuery(sql);
			if(r.next()){ 
				return r.getLong(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1 ;
	}
	
	protected Ticket retrieveTicket (long idTicket,java.sql.Statement state){
		long idGroup = 0;
		long idAuteur ;
		String name ;
		sql = "SELECT idGroupeDestinataire,objet FROM ticket WHERE idTicket ='"+idTicket+"' ";
		try {
			ResultSet r = state.executeQuery(sql);
			if(r.next()){ 
				idGroup = r.getLong(1);
				name = r.getString(2);
			}else {
				return null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		if ( (idAuteur = retrieveAuthorOfTicket(idTicket, state)) ==-1) {
			return null;
		}
		return new Ticket(Commands.SEND,idTicket,idAuteur,idGroup,name,idMessageOfTicket(idTicket, state));
	}
}
