DROP table ticket;
DROP TABLE destinataire;
DROP TABLE status;
DROP TABLE message;
DROP TABLE posseder;
DROP TABLE groupe;
DROP TABLE utilisateur;

CREATE TABLE utilisateur
	( idUtilisateur BIGINT(20),
	motDePasse CHAR(40),
	nomUtilisateur VARCHAR(30),
	prenom VARCHAR(30),
	courriel VARCHAR(120) UNIQUE,
	categorie VARCHAR(30),
	CONSTRAINT pk_idUtilisateur PRIMARY KEY (idUtilisateur),
	CONSTRAINT ck_motDePasse CHECK ( motDePasse IS NOT NULL),
	CONSTRAINT ck_categorie CHECK (categorie IN ('etudiant','agent','administration','enseignant-chercheur','invite' ))
	)ENGINE = InnoDB;

CREATE TABLE groupe
	(idGroupe BIGINT(20),
	  nomGroupe VARCHAR(30),
	  CONSTRAINT pk_idGroupe PRIMARY KEY (idGroupe)
	)ENGINE = InnoDB;
	
CREATE TABLE posseder
	( idGroupePosseder BIGINT(20),
	  idUtilisateurPosseder BIGINT(20),
	  CONSTRAINT pk_posseder PRIMARY KEY (idGroupePosseder,idUtilisateurPosseder),
	  CONSTRAINT fk_idGroupePosseder FOREIGN KEY ( idGroupePosseder) REFERENCES groupe(idGroupe),
	  CONSTRAINT fk_idUtilisateurPosseder FOREIGN KEY ( idUtilisateurPosseder) REFERENCES utilisateur(idUtilisateur)
	)ENGINE = InnoDB;
	
CREATE TABLE message
	( idMessage BIGINT(20),
	  dateMessage TIMESTAMP(6) ,
	  auteur BIGINT(20),
	  message VARCHAR(500),
	  CONSTRAINT pk_idMessage PRIMARY KEY ( idMessage),
	  CONSTRAINT fk_auteur FOREIGN KEY (auteur) REFERENCES utilisateur(idUtilisateur)
	)ENGINE = InnoDB;

CREATE TABLE status
	( idLecteur BIGINT(20),
	  idMessageStatus BIGINT(20),
	  etat VARCHAR(10),
	  CONSTRAINT pk_status PRIMARY KEY (idMessageStatus,idLecteur),
	  CONSTRAINT fk_idLecteur FOREIGN KEY (idLecteur) REFERENCES utilisateur(idUtilisateur),
	  CONSTRAINT fk_idMessageStatus FOREIGN KEY ( idMessageStatus) REFERENCES message(idMessage),
	  CONSTRAINT ck_etat CHECK (etat IN ('lu','reçut','non_reçut'))
	)ENGINE = InnoDB;
	
CREATE TABLE destinataire
	( idMessageDestinataire BIGINT(20),
	  idGroupDestinataire BIGINT(20),
	  CONSTRAINT pk_destinataire PRIMARY KEY (idMessageDestinataire,idGroupDestinataire),
	  CONSTRAINT fk_idMessageDestinataire FOREIGN KEY (idMessageDestinataire) REFERENCES message(idMessage),
	  CONSTRAINT fk_idGroupDestinataire FOREIGN KEY ( idGroupDestinataire) REFERENCES groupe(idGroupe)
	)ENGINE = InnoDB;

CREATE TABLE ticket 
    ( idTicket BIGINT(20),
	  idMessageTicket BIGINT(20),
	  CONSTRAINT pk_ticket PRIMARY KEY (idTicket,idMessageTicket),
	  CONSTRAINT fk_idMessageTicket FOREIGN KEY (idMessageTicket) REFERENCES message(idMessage)
	)ENGINE = InnoDB;

