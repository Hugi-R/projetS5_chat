package server;

import client.Categorie;
import utils.StatusType;

public class remplirDatabase {
	public static void main(String[] args){
		System.out.println("Lancement du serveur");
		String url = "jdbc:mysql://eralyon.net:3306/projets5";
		String user = "projets5_server";
		String passwd = "projets5_psswd";
		Database data = new Database(url,user,passwd);
		data.addUser(212626373094099446L, "courgette", "Selebran", "pierre", "pierre.selebran@univ-tlse3.fr", Categorie.ETUDIANT);
		data.addUser(166955891725051629L, "carrotte", "ROUSSEL", "hugo", "hugo.roussel@univ-tlse3.fr", Categorie.ENSEIGNANT_CHERCHEUR);
		data.addUser(190796928726647178L, "ananas", "ROUSSEL-FAYARD", "adrian", "adrian.roussel-fayard@univ-tlse3.fr", Categorie.AGENT);
		data.addgroup(256835400352577846L, "fac");
		data.addgroup(278609667354474505L, "personnel");
		data.addgroup(286339067366289492L, "all");
		
		data.addGroupToUser(256835400352577846L, 212626373094099446L);
		data.addGroupToUser(256835400352577846L, 166955891725051629L);
		data.addGroupToUser(278609667354474505L, 190796928726647178L);
		data.addGroupToUser(286339067366289492L, 212626373094099446L);
		data.addGroupToUser(286339067366289492L, 166955891725051629L);
		data.addGroupToUser(286339067366289492L,190796928726647178L);
		
		data.addTicket(317943147588095081L, 278609667354474505L, "probleme");
		data.addMessage(137126318033785457L, 212626373094099446L,317943147588095081L, "La lumiere ne marche pas dans la salle s205");
		data.addStatus(190796928726647178L, 137126318033785457L, StatusType.USER_READ);
		data.addMessage(83461768749910722L, 190796928726647178L,317943147588095081L, "message reçut nous serons rapidement sur site");
		data.addStatus(212626373094099446L,83461768749910722L, StatusType.USER_PENDING);
		
		data.addTicket(317780912113747362L, 278609667354474505L, "cafe URGENT");
		data.addMessage(100698344208134676L, 166955891725051629L,317780912113747362L, "la machine a cafe ne marche plus ");
		data.addStatus(190796928726647178L, 100698344208134676L, StatusType.USER_READ);
		data.addMessage(75385375474664354L,190796928726647178L,317780912113747362L, "desoler mais la fac ne s'occupe pas des machines veuillez contacter le 05  merci");
		data.addStatus(166955891725051629L, 75385375474664354L, StatusType.USER_SENT);
		
		data.addTicket(337516536014150299L, 256835400352577846L, "vacances");
		data.addMessage(130233831795944727L, 190796928726647178L,337516536014150299L, "service en vacance nous ne pourrons plus repondre ");
		data.addStatus(166955891725051629L, 130233831795944727L, StatusType.USER_READ);
		data.addStatus(212626373094099446L, 130233831795944727L, StatusType.USER_SENT);
		System.out.println(data.retriveTicket(317943147588095081L));
	}
}