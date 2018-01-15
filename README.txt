ROUSSEL Hugo, ROUSSEL-FAYARD Adrian, SELEBRAN Pierre
GitHub : https://github.com/Hugi-R/projetS5_chat
Le projet peut etre importer par eclipse : Import > Git project

installation de la base de donnée : 
	1- installer un serveur mysql ou mariaBD 
	 Depuis une console :
		-lancez votre serveur 
		-tapez "CREATE database" (vous pouvez changer database pour un autre nom pour votre base )
		-tapez "USE database" (vous etes maintenant dans la base de donnée )
		-ouvrer le document "bd.sql" dans la dossier du projets5
		-coller son contenu dans votre serveur pour obtenir toutes les tables
	 Depuis un navigateur :
		- assurez vous d'avoir installée adminer
		- lancez votre bd 
		- sur votre navigateur tapez 'localhost' ou 127.0.0.1 
		- rentrez vos identifiant 
		- puis cliquez sur importer selectionner "bd.sql" et faites executer
		- vos tables son maintenant créer vous pouvez fermer le navigateur 
	
Lancer le serveur :
    Compilez le code avec comme main MainServer ou téléchargez server.jar
    Le serveur prend comme arguments (dans l'ordre) : 
        <gui/nogui> avec ou sans interface
        <port> le numero de port d'écoute
        <url_jdbc> l'url au format jdbc (ex : jdbc:mysql://localhost:3306/db_name) pour se connecter à la base de donnée
        <user> l'utilisateur de la base de donnée
        <passwd> le mot de passe pour l'utilisateur de la base de donnée (non vide)
    exemple d'éxécution : java -jar server.jar gui 3636 jdbc:mysql://eralyon.net:3306/projets5 projets5_server projets5_psswd
    
Lancer un client :
    Compilez le code avec comme main MainClient ou téléchargez client.jar
    Le client prend comme arguments (dans l'ordre) :
        <server> L'adresse du serveur
        <port> Le port de connexion du serveur
    Exemple d'éxécution : java -jar client.jar eralyon.net 3636