# TOU-0917-ANDROID-checkpoint5android

Point 1 : Initialisation Git / GitHub
Crée un projet Android nommé WCSTravel. Exécute-le pour vérifier qu’il fonctionne correctement.
Crée un repository sur ton GitHub avec le nom : TOU-0917-ANDROID-checkpoint5android
Point 2 : Initialisation Firebase
Tu as accès au compte Firebase du checkpoint à l’adresse suivante : 
https://console.firebase.google.com/project/checkpoint3-android/database/checkpoint3-android/data/checkpoint5?hl=fr

Configure ton projet Android pour te connecter à cette base de données. Dans le noeud “checkpoint5”, tu as un jeu d’exemple de données “airports” et “travels” que tu peux utiliser, mais ne les modifie/supprime pas !

Si tu souhaites stocker des informations dans la base de données, ne le fait que dans ton dossier personnel : il se trouve dans checkpoint5/students/<tonPseudoGitHub>
Il n’est pas obligatoire de stocker quoi que ce soit sur Firebase pour réussir ce checkpoint.

Point 3 : Configuration de branche
Une fois que tu as configuré Firebase, affiche dans tes logs le contenu de “hasContent”, qui se trouve dans ton répertoire personnel : checkpoint5/students/<tonPseudoGitHub>
Une fois la valeur est bien retournée, envoie toutes toutes tes modifications dans master, puis crée une branche dev sur laquelle tu continueras de travailler pour la suite du checkpoint.

Dans GitHub, protége master et force la review des pull request par les Code Owners avant les merge.
Point 4 : L’application
Le but de ce checkpoint sera de créer une application de recherche et de réservation de vol. Pour t’aider, installe et utilise l’application “SkyScanner”, sur laquelle tu devras te baser. L’idée n’est pas de reproduire l’application “SkyScanner” (tu n’auras pas le temps) mais de répondre à un certains nombres de fonctionnalitées attendues :
1) À faire
L’utilisateur doit pouvoir rechercher un vol (aller-retour uniquement) à partir d’un aéroport d’origine, d’un aéroport de destination, d’une date de départ et d’une date de retour (à sélectionner à partir d’un DatePicker).
L’utilisateur doit se voir afficher la liste des vols correspondants à sa recherche, avec la compagnie aérienne, les dates de départ et de retour, ainsi que le prix.
Crée et utilise une classe TravelModel.
Traduit tous les textes de l’application en français et en anglais.
Une fois ces fonctionnalités réalisées, tu peux ajouter tout le contenu qui te fait envie ! Laisse libre cours à ton imagination, et essai d’utiliser tout ce que tu as appris jusqu’ici.

2) Les jeux de tests sur Firebase
tous les vols sont des aller-retour.
tous les prix sont en dollars.
les dates de départ possibles vont du 2018-01-24 au 2018-01-31 et la durée de séjour peut aller de 1 à 5 jours.
les vols ne sont qu’au départ de NYC (New York City).
les vols ne vont que vers les destinations LAX (Los Angeles International Airport), BOS (Boston - Logan International Airport) et MIA (Miami International Airport).
tu n’es pas obligé d’utiliser les jeux de tests Firebase, tu peux utiliser l’API renseignée dans la partie 4.
3) À ne pas faire
Il est inutile de gérer la création de comptes et la connexion de l’utilisateur.
Pour ce checkpoint, le design a une moindre importance : ne passe du temps dessus que si l’application est fonctionnelle. Cependant, l’ergonomie est importante !
Ne reste pas bloqué trop longtemps sur un même point, demande de l’aide à ton formateur si nécessaire (mais pas aux autres élèves !).
4) Bonus
Tu peux utiliser l’API proposée en lien ci-dessous plutôt que les jeux de tests Firebase, mais uniquement si tu es sûr de t’en sortir avec : https://sandbox.amadeus.com/ . Tu peux créer un compte sur ce site à partir de ton GitHub.
Tu peux utiliser un Singleton pour charger certaines informations utiles dès la SplashScreen.
Point 5 : Algorithme
Ajoute une méthode dans ton projet qui permette de convertir un prix de Dollars vers Euro, ou d’Euro vers Dollars (prends les cours de conversion actuels que tu trouveras sur Google).

La méthode aura en entrée montant à convertir (double), la monnaie d’origine (String) et la monnaie vers laquelle convertir (String). La méthode doit retourner une chaîne formatée contenant le montant converti et le symbole de la monnaie, par exemple :

convertPrice(13d, ”USD”, ”EUR”);
> “10,60 €”

convertPrice(3.99d, ”EUR”, ”USD”);
> “$4.89”

Appelle cette méthode dans l’application pour convertir les prix affichés dans les résultats de la recherche.
Point 6 : Finalisation Git / GitHub
Une fois les points précédents terminés, envoie tes modifications sur GitHub.
Ensuite, fais une demande de pull request vers “master” en mettant bastienwcs en “reviewer”.

Vérifie bien que tout est présent sur GitHub ensuite !
