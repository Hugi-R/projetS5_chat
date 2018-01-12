package server;

import client.Categorie;
import utils.StatusType;

public class remplirDatabase {
	public static void main(String[] args){
		System.out.println("Lancement du serveur");
		String url = "jdbc:mysql://eralyon.net:3306/projets5";
		String user = "projets5_server";
		String passwd = "projets5_psswd";
		Database.start(url,user,passwd);
		Database.addUser(212626373094099446L, "courgette", "Selebran", "pierre", "pierre.selebran@univ-tlse3.fr", Categorie.ETUDIANT);
		Database.addUser(166955891725051629L, "carrotte", "ROUSSEL", "hugo", "hugo.roussel@univ-tlse3.fr", Categorie.ENSEIGNANT_CHERCHEUR);
		Database.addUser(190796928726647178L, "ananas", "ROUSSEL-FAYARD", "adrian", "adrian.roussel-fayard@univ-tlse3.fr", Categorie.AGENT);
		Database.addgroup(256835400352577846L, "fac");
		Database.addgroup(278609667354474505L, "personnel");
		Database.addgroup(286339067366289492L, "all");
		
		Database.addGroupToUser(256835400352577846L, 212626373094099446L);
		Database.addGroupToUser(256835400352577846L, 166955891725051629L);
		Database.addGroupToUser(278609667354474505L, 190796928726647178L);
		Database.addGroupToUser(286339067366289492L, 212626373094099446L);
		Database.addGroupToUser(286339067366289492L, 166955891725051629L);
		Database.addGroupToUser(286339067366289492L,190796928726647178L);
		
		Database.addTicket(317943147588095081L, 278609667354474505L, "probleme");
		Database.addMessage(137126318033785457L, 212626373094099446L,317943147588095081L, "La lumiere ne marche pas dans la salle s205");
		Database.addStatus(190796928726647178L, 137126318033785457L, StatusType.USER_READ);
		Database.addMessage(83461768749910722L, 190796928726647178L,317943147588095081L, "message reçut nous serons rapidement sur site");
		Database.addStatus(212626373094099446L,83461768749910722L, StatusType.USER_PENDING);
		
		Database.addTicket(317780912113747362L, 278609667354474505L, "cafe URGENT");
		Database.addMessage(100698344208134676L, 166955891725051629L,317780912113747362L, "la machine a cafe ne marche plus ");
		Database.addStatus(190796928726647178L, 100698344208134676L, StatusType.USER_READ);
		Database.addMessage(75385375474664354L,190796928726647178L,317780912113747362L, "desoler mais la fac ne s occupe pas des machines veuillez contacter le 05  merci");
		Database.addStatus(166955891725051629L, 75385375474664354L, StatusType.USER_SENT);
		
		Database.addTicket(337516536014150299L, 256835400352577846L, "vacances");
		Database.addMessage(130233831795944727L, 190796928726647178L,337516536014150299L, "service en vacance nous ne pourrons plus repondre ");
		Database.addStatus(166955891725051629L, 130233831795944727L, StatusType.USER_READ);
		Database.addStatus(212626373094099446L, 130233831795944727L, StatusType.USER_SENT);
		System.out.println(Database.retriveTicket(317943147588095081L));
	}
}