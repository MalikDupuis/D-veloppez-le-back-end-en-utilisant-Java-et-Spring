Description
ChaTop Backend est une application RESTful développée avec Spring Boot. Elle permet la gestion des utilisateurs, des locations (rentals), l'authentification avec JWT (JSON Web Token), et le téléversement d'images. L'API est documentée via Swagger/OpenAPI.

Fonctionnalités principales
Gestion des utilisateurs avec authentification JWT.
CRUD complet pour les rentals (locations).
Téléversement d'images pour les rentals.
Documentation interactive des API avec Swagger/OpenAPI.
Prérequis
Java 17 ou plus récent.
Maven 3.6 ou plus récent.
Un serveur de base de données compatible (MySQL, PostgreSQL, etc.).
Installation
Cloner le projet :

bash
Copier le code
git clone https://github.com/votre-utilisateur/chatop-backend.git
cd chatop-backend
Configurer la base de données : Dans le fichier application.properties, configurez les détails de connexion à la base de données.

properties
Copier le code
spring.datasource.url=jdbc:mysql://localhost:3306/chatop
spring.datasource.username=root
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
Installer les dépendances :

bash
Copier le code
mvn clean install
Lancer l'application :

bash
Copier le code
mvn spring-boot:run
Fonctionnement de l'application
Authentification JWT
L'API utilise un mécanisme d'authentification par JWT. Pour obtenir un jeton JWT valide :

Effectuez une requête POST sur /api/auth/login avec les paramètres email et password.
Un jeton vous sera retourné et devra être inclus dans chaque requête sous l'entête Authorization : Bearer {token}.
Gestion des Rentals (Locations)
Les endpoints principaux pour gérer les rentals sont :

GET /api/rentals : Récupère toutes les locations.
POST /api/rentals : Crée une nouvelle location. Inclut l'image à téléverser dans la requête.
GET /api/rentals/{id} : Récupère les détails d'une location.
PUT /api/rentals/{id} : Met à jour une location.
DELETE /api/rentals/{id} : Supprime une location.
