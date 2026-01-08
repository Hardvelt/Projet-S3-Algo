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


3. Conclusion

    Ce projet compare une approche gloutonne efficace à une approche optimale mais coûteuse, permettant de mieux comprendre les compromis entre performance et optimalité dans les algorithmes de création de groupes.








Algorithmes de constitution de groupes (S3)

Ce dossier contient trois algorithmes de constitution automatique de groupes TD/TP.
Ils prennent en entrée une liste d’étudiants (List<EtudiantDTO>) et renvoient une solution sous forme de liste de groupes (List<List<EtudiantDTO>>).
Les contraintes de tailles sont celles définie dans scoreS3 : TAILLE_MIN = 14 et TAILLE_MAX = 18.

1) GloutonS3_1 — Répartition simple avec objectifs filles/garçons

Nom : Glouton S3_1
Idée générale : construire les groupes un par un, en essayant d’atteindre une taille équilibrée, avec des priorités simples (filles/garçons), tout en limitant les redoublants.

Règles / priorités : 

Chaque groupe a une taille cible calculée dynamiquement :
tailleCible = taille(nbRestants / groupesRestants), puis bornée entre 14 et 18.

Pendant le remplissage d’un groupe :

- Si on n’a pas encore 5 filles, on ajoute en priorité une fille.
- Sinon, si on n’a pas encore 5 garçons, on ajoute en priorité un garçon.
- Sinon, on ajoute normalement un étudiant.

Contrainte stricte : maximum 3 redoublants par groupe.

Sécurité : si on n’arrive pas à remplir assez (moins de 14), l’algorithme force l’ajout d’étudiants restants pour atteindre au moins 14 (sans dépasser 18).

Résultat attendu :

- Des groupes de taille entre 14 et 18 (en fonction du nombre d’étudiants restants).
- Les 5 filles / 5 garçons sont des objectifs (priorité) : si c’est impossible, l’algorithme continue quand même.

2) GloutonS3_2 — Glouton avec homogénéité apprentissage + covoiturage

Nom : Glouton S3_2
Idée générale : même principe que le glouton 1, mais ajoute deux règles importantes :

homogénéité apprentis / initiaux ET gestion du covoiturage (pack)

Règle 1 : apprentissage (homogénéité)

On regarde le premier étudiant de la liste :

- s’il est apprenti ⇒ on ne garde que les apprentis
- sinon ⇒ on ne garde que les initiaux

Donc la solution calculée ne mélange pas les statuts (apprentis/initiaux) dans la répartition produite.

Règle 2 : covoiturage (pack)

Si un étudiant a indiceCovoiturage > 0 :

- on cherche tous les étudiants ayant le même indice
- si au moins 2 étudiants partagent cet indice, on tente d’ajouter tout le pack dans le groupe

l’ajout du pack est accepté seulement si :

- on ne dépasse pas 18
- on respecte la limite redoublants ≤ 3 et que cela rentre dans la tailleCible (ou qu’on n’a pas encore 14)

Règles communes avec GloutonS3_1

Même logique de tailleCible (entre 14 et 18).

Même objectifs :

priorité filles si < 5
puis priorité garçons si < 5

Même contrainte stricte :

- max 3 redoublants par groupe
- Même comportement “secours” :

si on est encore < 14, on force des ajouts pour atteindre 14.

Résultat attendu :

- Des groupes entre 14 et 18.
- Des étudiants covoiturant regroupés autant que possible.

Une répartition homogène (apprentis ou initiaux selon le premier étudiant).

3) BruteForceS3 — Recherche exhaustive (limitée)

Nom : Brute force S3
Idée générale : explorer récursivement toutes les affectations possibles des étudiants dans les groupes (ou pas placés), et garder la meilleure selon la fonction scoreS3.scoreSolution(...).

Limite : LIMITE_ETUDIANTS = 18 : au-delà, le nombre de possibilités explose (temps trop long).

Le nombre de groupes réellement utilisés est ajusté :

nbGroupesReels = min(nombreGroupesDemandés, nbEtudiants / TAILLE_MIN) pour éviter de demander plus de groupes que possible.

Principe du backtracking

Pour chaque étudiant (dans l’ordre de la liste) :

choix A : ne pas l’affecter (-1)

choix B : l’affecter au groupe 0, 1, 2, … si le groupe n’a pas dépassé 18

Quand tous les étudiants ont été traités :

- on construit les groupes à partir de l’affectation
- on calcule scoreS3.scoreSolution(solution)

on garde la meilleure solution rencontrée.

Résultat attendu : Sur ≤ 18 étudiants, il peut trouver une solution meilleure qu’un glouton.

Le résultat dépend entièrement de la fonction de score scoreS3.



