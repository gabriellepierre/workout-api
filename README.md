# Workout-api

Ce projet utilise le framework Quarkus. Leur site : https://quarkus.io/ .

> **_NOTE:_**  Une liste des requêtes HTTP possibles à envoyer à l'API est présente dans le fichier requetesHttp.txt .

## Description

L'objectif de ce projet est de créer une API Java avec le framework Quarkus pour une application de sport.
La base de données utilisée est non-relationnelle (MongoDB), et la librairie utilisée est org.mongodb:mongodb-driver-sync:4.9.1 .

## Lancer l'application en mode dev

1 - Tout d'abord, lancez les conteneurs Docker avec la commande
```shell script
docker-compose up -d
```

Ceci va lancer la BDD MongoDB et son interface MongoExpress, accessible sur http://localhost:8888/ .

2 - Ensuite pour lancer l'application (avec live-reload) avec Maven, entrez depuis un terminal :
```shell script
./mvnw compile quarkus:dev
```

PS : il est aussi possible d'utiliser la CLI Quarkus. Pour cela, utilisez un gestionnaire de packages comme SDKMan (https://sdkman.io/) ou Chocolatey pour installer la CLI Quarkus. Puis entrez :
```shell script
quakus dev
```

## Lancer les tests unitaires

```shell script
./mvnw compile quarkus:test
```

ou

```shell script
quarkus test
```

> **_NB:_**  Une IU dev de quarkus est disponible sur http://localhost:8080/q/dev/. Cette UI possède la liste des endpoints HTTP, leurs scores (performances), etc...

## Fonctionnalités réalisées dans la Notation (https://clientserveur-courses.clubinfo-clermont.fr/Notation.html)

### Documentation

1 - Schéma complet de la base de données (MCD) : voir le fichier AppliDeSportMCD.svg

2 - un README : ce fichier

3 - UML des entités façon NoSQL : voir le fichier 

### Projet

4 - Suivi de la structure demandée : oui, Utilisation d'un Modèl (package model), de Controlleurs (package Controllers) et de DTO servant au transfert de données dans le body, soit à la réception de la requête, 
l'envoi de la réponse HTTP ou l'envoi de données à MongoDB.

5 - Document imbriqués/dénormalisés : oui, liste de documents Exercice dans Workout et liste de documents Set dans Exercice.

6 - Documents liés par référence : oui, référence à un Program dans User

7 - CRUD complet : oui, pour chaque document principal possibel d'en ajouter 1, d'en éditer 1, d'en supprimer 1, de tous les récupérer ou d'en récupérer 1 par _id.

8 - Requêtes de recherche : oui, possibilité de rechercher un Program par un champ, n'importe lequel

9 - Définition d'index cohérents : oui, voir le constructeur de ProgramMongoClient, on utilise des Single-field Index et des Compound index dans la cas d'un filtre sur un programme,
soit par objectif, soit par level, soit par les 2.

### Bonus

10 - Aggrégation avancée

11 - Tests : oui, dans le répertoire test. Lancez les tests avec 

```shell script
./mvnw compile quarkus:test
```

ou

```shell script
quarkus test
```
