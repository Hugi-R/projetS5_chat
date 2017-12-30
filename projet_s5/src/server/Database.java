package server;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	private static Connection conn = null;
	private static java.sql.Statement state ;
	private static String sql;
	public static int start(String url , String user , String passwd) {
		int i =0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("driver charger [OK]");
			
			conn = DriverManager.getConnection(url, user, passwd);
			System.out.println("connection [OK]");
			state = conn.createStatement();		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("PATATE");
			e.printStackTrace();
			i=1;
		}
		return i;
		
	}
	public static int addgroup (long val , String nom) {
		int i = 0 ;
		String sql = "INSERT INTO groupe (idGroupe, nomGroupe) VALUES ( '"+val+"','"+nom+"');";	
		try {
			i = state.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	public static int deletegroup (long val ) {
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
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/projet";
		String user = "root";
		String passwd = "";
		start(url,user,passwd);
		String groupe = "sfhj";
		long val = 0 ;
		System.out.println(deletegroup(val));
	}

}
