-- Adminer 4.3.1 MySQL dump

SET NAMES utf8;
SET time_zone = '+00:00';

SET NAMES utf8mb4;

DROP TABLE status;
DROP TABLE message;
DROP table ticket;
DROP TABLE posseder;
DROP TABLE groupe;
DROP TABLE utilisateur;

CREATE TABLE `utilisateur` (
  `idUtilisateur` bigint(20) NOT NULL,
  `motDePasse` char(40) DEFAULT NULL,
  `nomUtilisateur` varchar(30) DEFAULT NULL,
  `prenom` varchar(30) DEFAULT NULL,
  `courriel` varchar(120) DEFAULT NULL,
  `categorie` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`idUtilisateur`),
  UNIQUE KEY `courriel` (`courriel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `groupe` (
  `idGroupe` bigint(20) NOT NULL,
  `nomGroupe` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`idGroupe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;





CREATE TABLE `posseder` (
  `idGroupePosseder` bigint(20) NOT NULL,
  `idUtilisateurPosseder` bigint(20) NOT NULL,
  PRIMARY KEY (`idGroupePosseder`,`idUtilisateurPosseder`),
  KEY `fk_idUtilisateurPosseder` (`idUtilisateurPosseder`),
  CONSTRAINT `fk_idUtilisateurPosseder` FOREIGN KEY (`idUtilisateurPosseder`) REFERENCES `utilisateur` (`idUtilisateur`),
  CONSTRAINT `posseder_ibfk_1` FOREIGN KEY (`idGroupePosseder`) REFERENCES `groupe` (`idGroupe`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `ticket` (
  `idTicket` bigint(20) NOT NULL,
  `idGroupeDestinataire` bigint(20) DEFAULT NULL,
  `objet` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`idTicket`),
  KEY `idGroupeDestinataire` (`idGroupeDestinataire`),
  CONSTRAINT `ticket_ibfk_1` FOREIGN KEY (`idGroupeDestinataire`) REFERENCES `groupe` (`idGroupe`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `message` (
  `idMessage` bigint(20) NOT NULL,
  `dateMessage` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `auteur` bigint(20) DEFAULT NULL,
  `idTicketMessage` bigint(20) DEFAULT NULL,
  `message` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`idMessage`),
  KEY `fk_auteur` (`auteur`),
  CONSTRAINT `fk_auteur` FOREIGN KEY (`auteur`) REFERENCES `utilisateur` (`idUtilisateur`),
  KEY `fk_idTicketMessage` (`idTicketMessage`),
  CONSTRAINT `message_ibfk_l` FOREIGN KEY (`idTicketMessage`) REFERENCES `ticket` (`idTicket`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `status` (
  `idLecteur` bigint(20) NOT NULL,
  `idMessageStatus` bigint(20) NOT NULL,
  `etat` int(1) DEFAULT NULL,
  PRIMARY KEY (`idMessageStatus`,`idLecteur`),
  KEY `fk_idLecteur` (`idLecteur`),
  CONSTRAINT `fk_idLecteur` FOREIGN KEY (`idLecteur`) REFERENCES `utilisateur` (`idUtilisateur`),
  KEY `fk_idMessageStatus` (`idMessageStatus`),
  CONSTRAINT `status_ibfk_1` FOREIGN KEY (`idMessageStatus`) REFERENCES `message` (`idMessage`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO utilisateur (idUtilisateur, motDePasse, nomUtilisateur, prenom ,courriel,categorie) VALUES (189358927445373257,null,'NoName','NoName',null,'INVITE') ;

