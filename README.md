# Workshop FranceConnect 20/01/2016

Ce workshop (http://www.meetup.com/fr-FR/FranceConnect/events/227568100/) a été organisé par Octo et la DINSIC. J'en profite pour remercier Octo pour leur accueil et l'encadrement pendant cette journée.



## Pourquoi ce POC ?

L'objectif de la journée était d'appréhender l'API FranceConnect et de savoir comment l'intégrer dans nos applications.
Pour cela, nous avons créé ce POC afin de valider l'intégration dans le langage Java.
 - la connexion
 - la déconnexion
 - l'usage des scopes
 - utilisation du kit FranceConnect
 
Ce POC se positionne en tant que Fournisseur de services.


## Pré-requis

Nous devez créer un compte FranceConnect. Pour cela, il faut aller à l'url suivante : 
https://doc.integ01.dev-franceconnect.fr

Après avoir créer le compte, il est nécessaire de configurer le compte (https://doc.integ01.dev-franceconnect.fr/updateClient). Pour cela, il y a notamment les callbacks à configurer

 - Url de Callcack : http://localhost:8080/franceconnect-poc/oidc_callback
 - Url de Logout (après déconnexion) : http://localhost:8080/franceconnect-poc

Il est possible de définir plusieurs urls callbacks. Dans ce cas, il faut une url par ligne.

## Initialisation

Pour faire fonctionner l'application :

- cloner le dépôt git 
- lancer tomcat :

	mvn tomcat:run


## Scénario

### Connexion simple
- accéder avec votre navigateur à l'adressse :

	http://localhost:8080/franceconnect-poc/

- cliquer sur le lien securisé 
- Vous êtes redirigé vers FranceConnect
- sélectionner les impots
- utiliser les credentials (disponibles sur le site FranceConnect) 
- Vous arrivez sur la page sécurisé.

### déconnexion
- cliquer sur le logo FranceConnect
- sélectionner le bouton déconnecter
- Confirmer ou pas la déconnexion FranceConnect
- Vous arrivez sur la page d'accueil de l'application.

### Connexion simple avec données supplémentaire 
- accéder avec votre navigateur à l'adressse :

	http://localhost:8080/franceconnect-poc/

- cliquer sur le lien securisé (nécessitant le revenu fiscal de référence)
- Vous êtes redirigé vers FranceConnect
- sélectionner les impots
- utiliser les credentials (disponibles sur le site FranceConnect) 
- Vous obtenez une page supplémentaire pour demander l'autorisation d'accèder au revenu fiscal de référence.
- Vous arrivez sur la page sécurisé.


## Kit FranceConnect

Après authentification, le kit (https://doc.integ01.dev-franceconnect.fr/ressources-graphiques) est visible  par l'affichage de l'icone FranceConnect. 

	<script src="http://fcp.integ01.dev-franceconnect.fr/js/franceconnect.js"></script>

	<div id="fconnect-profile" data-fc-logout-url="/franceconnect-poc/logout">

	
En cliquant sur l'icone, vous pouvez 
 - déconnexion (à gérer par votre application)
 - information franceconnect (à gérer par votre application)
 - statistiques (services offerts par FranceConnect)
    


## Ressources

Ce projet utilise la librarie apache oltu (https://oltu.apache.org/) version oauth2

Le code du projet suivant a été réutilisé :

	https://github.com/florent-andre/franceconnecthelper

Il a été nécessaire de le modifier afin de récupérer <b>accessToken</b> différemment. Ainsi, nous pouvions obtenir ID_TOKEN. Ce dernier est nécessaire pour la phase de déconnexion.
