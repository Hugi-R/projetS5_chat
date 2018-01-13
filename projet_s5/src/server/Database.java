package server;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import client.Categorie;
import packet.Group;
import packet.Message;
import packet.Ticket;
import packet.User;

public class Database {
	private Connection conn = null;
	private java.sql.Statement state = null ;
	private Add add = new Add();
	private Delete delete = new Delete();
	private Select select = new Select();
	private Update update = new Update();
	
	//mise en prive suite a une demande de sonartlint
	public Database (String url , String user , String passwd) {
		start(url,user,passwd);
	}
	
	public int start(String url , String user , String passwd) {
		int i =0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("[OK] driver charge");
			
			conn = DriverManager.getConnection(url, user, passwd);
			System.out.println("[OK] connection etablie");
			state = conn.createStatement();		
		} catch (Exception e) {
			e.printStackTrace();
			i=1;
		}
		return i;
	}
	
	public int closeConnection() {
		if (isStarted()) {
			try {
				state.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("[OK] connection close");
			return 1;
		}else {
			System.err.println("[KO] connection deja close ");
			return -1;
		}
		
	}
	
	public boolean isStarted() {
		if (state == null) {
			System.err.println("[KO] la connection n'a pas ete etablie");
			return false;
		}
		return true ;
	}
/* fonction d'ajout de donnee dans la base *******************/
	public int addgroup(long idGroup, String nom){
		if (isStarted())
			return add.addGroup(idGroup,nom,state);
		else
			return -1;
	}
	public int addUser(long id, String password, String name ,String firstName ,String email,Categorie category){
			if (isStarted())
				return add.addUser(id, password, name, firstName, email, category, state);
			else
				return -1;
	}
	public int addGroupToUser(long idGrp , long idUser) {
		if (isStarted())
			return add.addGroupToUser(idGrp, idUser, state);
		else
			return -1;
	}
	public int addMessage (long idMessage, long author,long idTicket,String text) {
		if (isStarted())
			return add.addMessage(idMessage, author,idTicket, text, state);
		else
			return -1;
	}
	public int addStatus (long idReader,long idMessage, byte etat) {
		if (isStarted())
			return add.addStatus(idReader, idMessage, etat, state);
		else
			return -1;
	}
	public int addTicket (long idTicket , long idGroupRecipient,String objet) {
		if (isStarted())
			return add.addTicket(idTicket, idGroupRecipient,objet, state);
		else
			return -1;
	}
	
/*fonction de suppression de donnée********************************/
	public int deleteGroup (long idGroup) {
		if (isStarted())
			return delete.deletegroup(idGroup, state);
		else
			return -1;
	}
	public int takeUserOutOfGroup (long idGroup , long idUser) {
		if (isStarted())
			return delete.takeUserOutOfGroup(idGroup,idUser, state);
		else
			return -1;
	}
	public int deleteUser (long idUser ) {
		if (isStarted())
			return delete.deleteUser(idUser, state);
		else
			return -1;
	}
/*fonction de selection*********************************************/
	public long connect (String password, String email) {
		if (isStarted())
			return select.connect(password, email, state);
		else
			return -1;
	}
	public List<Long> idMessageOfTicket (long idTicket){
		if (isStarted())
			return select.idMessageOfTicket(idTicket, state);
		else
			return new ArrayList<>();
	}
	public List<Long> idUserInGroup (long idGroup){
		if (isStarted())
			return select.idutilInGroup(idGroup, state);
		else
			return new ArrayList<>();
	}
	public Message retrieveMessage (long idMessage){
		if (isStarted())
			return select.RecupMessage(idMessage, state);
		else
			return null;
	}
	public User retrieveUserWithList(long idUser){
		if (isStarted())
			return select.recupUserWithList(idUser, state);
		else
			return null;
	}
	public User retrieveUserShort(long idUser){
		if (isStarted())
			return select.recupUserShort(idUser, state);
		else
			return null;
	}
	public String nameOfGroup ( long idGroup ) {
		if(isStarted())
			return select.nameOfGroup(idGroup, state);
		else
			return null;
	}
	public Group retrieveGroup (long idGroup) {
		if(isStarted())
			return select.retrieveGroup(idGroup, state);
		else
			return null;
	}
	public Ticket retriveTicket (long idTicket) {
		if (isStarted()) {
			return select.retrieveTicket(idTicket, state);
		}else
			return null ;
	}
	public List<User> retrieveAllUser(){
		if (isStarted()) {
			return select.retrieveAllUser(state);
		}else
			return null ;
	}
	public List<Group> retrieveAllGroup(){
		if (isStarted())
			return select.retrieveAllGroup(state);
		else
			return null ;
	}
	public HashMap<Byte,List<Long>> recupStatus(long idMessage){
		if (isStarted())
			return select.recupStatus(idMessage, state);
		else
			return null ;
	}
	public String recupMail(long idUser) {
		if (isStarted())
			return select.recupMail(idUser, state);
		else
			return null ;
	}
/*fonction de changement de valeur dans la base de donnee**********/
	public int changePasswd(long idUser,String newPassword) {
		if (isStarted())
			return update.changePasswd(idUser, newPassword, state);
		else
			return -1;
	}
	public int changeEtatStatus(long idMessage , long idUser,byte etat) {
		if (isStarted())
			return update.changeStatus(idMessage, idUser, etat, state);
		else
			return -1;
	}
	public int changeCategorieUser(long idUser ,Categorie category) {
		if (isStarted())
			return update.changeCategorie(idUser, category, state);
		else
			return -1;
	}
	public int changeContentsMessage(long idMessage,String newText) {
		if (isStarted())
			return update.changeMessage(idMessage, newText, state);
		else
			return -1;
	}
	public int changeUser(long idUser ,String name,String firstName , String mail , Categorie cat) {
		if (isStarted())
			return update.changeUser(idUser, name, firstName, mail, cat, state);
		else
			return -1;
	}
}
