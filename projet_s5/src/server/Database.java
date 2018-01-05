package server;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import client.Categorie;
import client.Etat;
import packet.Message;
import packet.User;

public class Database {
	private static Connection conn = null;
	private static java.sql.Statement state = null ;
	private static Add add = new Add();
	private static Delete delete = new Delete();
	private static Select select = new Select();
	private static Update update = new Update();
	
	//mise en prive suite a une demande de sonartlint
	private Database () {	
	}
	
	public static int start(String url , String user , String passwd) {
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
	
	public static int closeConnection() {
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
	
	public static boolean isStarted() {
		if (state == null) {
			System.err.println("[KO] la connection n'a pas été établie");
			return false;
		}
		return true ;
	}
/* fonction d'ajout de donnée dans la base *******************/
	public static int addgroup(long idGroup, String nom){
		if (isStarted())
			return add.addGroup(idGroup,nom,state);
		else
			return -1;
	}
	public static int addUser(long id, String password, String name ,String firstName ,String email,Categorie category){
			if (isStarted())
				return add.addUser(id, password, name, firstName, email, category, state);
			else
				return -1;
	}
	protected static int addGroupToUser(long idGrp , long idUser) {
		if (isStarted())
			return add.addGroupToUser(idGrp, idUser, state);
		else
			return -1;
	}
	protected static int addMessage (long idMessage, long author,String text) {
		if (isStarted())
			return add.addMessage(idMessage, author, text, state);
		else
			return -1;
	}
	protected static int addStatus (long idReader,long idMessage, Etat etat) {
		if (isStarted())
			return add.addStatus(idReader, idMessage, etat, state);
		else
			return -1;
	}
	protected static int addRecipient (long idMessage , long idGroup) {
		if (isStarted())
			return add.addDestinataire(idMessage, idGroup, state);
		else
			return -1;
	}
	protected static int addTicket (long idTicket , long idMessage) {
		if (isStarted())
			return add.addTicket(idTicket, idMessage, state);
		else
			return -1;
	}
	
/*fonction de suppression de donnée********************************/
	public static int deleteGroup (long idGroup) {
		if (isStarted())
			return delete.deletegroup(idGroup, state);
		else
			return -1;
	}
/*fonction de selection*********************************************/
	public static long connect (String password, String email) {
		if (isStarted())
			return select.connect(password, email, state);
		else
			return -1;
	}
	public static List<Long> idMessageOfTicket (long idTicket){
		if (isStarted())
			return select.idMessageOfTicket(idTicket, state);
		else
			return new ArrayList<>();
	}
	public static List<Long> idUserInGroup (long idGroup){
		if (isStarted())
			return select.idutilInGroup(idGroup, state);
		else
			return new ArrayList<>();
	}
	public static List<Long> retrieveMessageForRecipient (long idUser,long date){
		if (isStarted())
			return select.RecupMessageDest(idUser, date, state);
		else
			return new ArrayList<>();
	}
	public static Message retrieveMessage (long idMessage){
		if (isStarted())
			return select.RecupMessage(idMessage, state);
		else
			return null;
	}
	public static User retrieveUserWithList(long idUser){
		if (isStarted())
			return select.RecupUserWithList(idUser, state);
		else
			return null;
	}
	public static User retrieveUserShort(long idUser){
		if (isStarted())
			return select.RecupUserShort(idUser, state);
		else
			return null;
	}
/*fonction de changement de valeur dans la base de donnée**********/
	public static int changePasswd(long idUser,String newPassword) {
		if (isStarted())
			return update.changePasswd(idUser, newPassword, state);
		else
			return -1;
	}
	public static int changeEtatStatus(long idMessage , long idUser,Etat etat) {
		if (isStarted())
			return update.changeStatus(idMessage, idUser, etat, state);
		else
			return -1;
	}
	public static int changeCategorieUser(long idUser ,Categorie category) {
		if (isStarted())
			return update.changeCategorie(idUser, category, state);
		else
			return -1;
	}
	public static int changeContentsMessage(long idMessage,String newText) {
		if (isStarted())
			return update.changeMessage(idMessage, newText, state);
		else
			return -1;
	}
}
