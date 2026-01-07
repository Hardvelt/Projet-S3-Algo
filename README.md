README – Création de groupes d’étudiants en Java

1. Modes de création de groupes
1.1 Création gloutonne avec contraintes complètes

Méthode :
    Glouton1(int nbrGroupes, Semestre semestre, ArrayList<Etudiant> etudiantsCpy) par Damien Braconnier :

Principe :
    Cet algorithme utilise une approche gloutonne, minimisant la somme des différences de score entre chaque groupe et les étudiants.

Étapes principales :

    Vérification de la validité du nombre de groupes

    Calcul de la taille cible des groupes

    Identification des groupes de covoiturage (qui doivent rester ensemble)
    
    Séparation des covoiturages possedant au moins un étudiant anglophone

    Ajout des coivoiturages anglophone dans les deux premiers groupes et les autres dans tout les groupes. La façon dont le score est calculé oblige les groupes non anglophone a allé dans les groupes vide.

    Ajout des etudiants restant de la meme façon.

    Retourne les groupes créer

Placement :

    des groupes de covoiturage en priorité,

    des étudiants anglophones,

    puis des autres étudiants

    Le choix du groupe se fait à chaque fois via la méthode Semestre.GroupeScoreMinimal(...) qui sélectionne le groupe optimisant les critères définis.

Avantages :

    Rapide

    Respecte les contraintes

    Bon équilibrage global

Limites :

    Ne garantit pas une solution optimale globale
    
    contrainte élevée pour les groupes anglo et peut ne pas trouver de solution car les filles sont aussi mises dedans
    
1.2 Création gloutonne avec contraintes allégées par Damien Braconnier :

Méthode :
    Glouton2(int nbrGroupes, int nbrGroupesFilles, Semestre semestre, ArrayList<Etudiant> etudiantsCpy)

Différence avec la version précédente :
    Cette version introduit une contrainte plus souple sur la répartition des filles, en autorisant un nombre maximal de groupes contenant des filles.

Fonctionnement :

    Identique à la version gloutonne classique

    La méthode GroupeScoreMinimal prend en compte le nombre de groupes autorisés avec des filles

Objectif :

    Améliorer la faisabilité dans des cas où les contraintes strictes rendent la création impossible ou une baisse de score.

1.3 Création par force brute (brute force) par Damien Braconnier 

Méthode :
    bruteForce(ArrayList<Etudiant> etudiants, int nbrGroupes, Semestre semestre)

Principe :
    Cet algorithme teste toutes les partitions possibles des étudiants en groupes, puis sélectionne celle ayant le meilleur score global.

Étapes :

    Génération de toutes les partitions possibles (generatePartitions)

    Vérification des contraintes pour chaque partition :

        taille minimale des groupes,

        répartition des filles au moins 4 ou aucune,

        respect du covoiturage (RespectCovoiturage)

        Calcul du score global

Sélection de la meilleure partition

Avantages :

Garantit la solution optimale

Limites majeures :

Inutilisable

2. Algorithmes importants expliqués
2.1 Respect du covoiturage par Damien Braconnier :

    La méthode RespectCovoiturage vérifie que deux étudiants appartenant au même groupe de covoiturage ne se retrouvent jamais dans des groupes différents.

    Elle parcourt chaque étudiant et compare son identifiant de covoiturage avec ceux des autres groupes.

2.2 Génération des partitions (force brute) par Damien Braconnier :

    La méthode generatePartitions utilise une approche récursive :

    on place récursivement chaque étudiant dans chacun des groupes possibles

    cela génère toutes les combinaisons possibles

    Cette méthode est très coûteuse mais correcte sur le plan algorithmique.

2. Améliorations possibles

    Optimisation du brute force en le tranformant en un algorithme de branch and bound

    Ajout de poids configurables pour les critères du score

    Parallélisation du calcul des partitions

3. Conclusion

    Ce projet compare une approche gloutonne efficace à une approche optimale mais coûteuse, permettant de mieux comprendre les compromis entre performance et optimalité dans les algorithmes de création de groupes.
