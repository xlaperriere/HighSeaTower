Xavier Laperriere


Utilisation de javafx 19.0.2. N'est pas inclu dans les fichiers.
Pour le download, visiter : https://gluonhq.com/products/javafx/


Si n'importe quel probleme survient, me laisser savoir. Tout feedback
est grandement apprécié!






** POUR EXECUTER, EXECUTER LE MAIN DANS LA CALSSE HIGHSEATOWER

** Les plateformes :

    JAUNES ET ORNAGE : Possible de les traverser du bas, aucun autre effet.
    ROUGES : Impossible à traverser du bas, aucun autre effet.
    BLEU : Possible de les traverser du bas ET rebondissent.

---------------------------------------------------------------------
Classe HighSeaTower :
- C'est la classe vue de l'architecture MVC
- On y retourve le stage, pane, timer, etc...
- Pour toutes les touches possibles du jeux :
scene.onKeyPressed ou KeyReleased.

---------------------------------------------------------------------
Classe Controleur :
- Controleur de l'architecture MVC
-Fait le lien entre notre classe Jeu (qui d�fini la logique et plus)
et la classe HighSeaTower

----------------------------------------------------------------------

**Toutes les classes suivantes font partie du MODELE de l'architecture**

Classe Jeu : 
- Contient un constructeur Jeu qui initialise les param�tres du jeu 
(m�duse, plateformes, bulles, score, ...)
-classe dans laquelle on d�fini le plus la logique du mode d�bug


Classe Entity :
-classe abstraite qui sera parent aux classes M�duse, Bulles et Plateformes.
-On y d�fini les attributs x et y (position d'une entit�e), vitesseFenetre,
largeur, hauteur, etc...


Classe M�duse :
-Constructeur de la m�duse et de ses fonctions qui lui permettent de se 
d�placer et interragir avec les plateformes


Classe Plateformes : 
-Classe abstraite, puisque on sous-classera avec les quatres types de 
plateformes possibles


Classe Bulles : 
-Contient constructeur pur les bulles, permet de g�n�rer al�atoirement.


Classe Score : 
-Permet d'avoir une score en tant qu'objet (surement pas absolument n�ss�saire)
-Noter que le score est un Text javafx qui s'update � chaque dt en fonction
de la position en y de la m�duse.




