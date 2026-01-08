package fr.iut.sae.app.algo.s3;

import fr.iut.sae.app.algo.commun.modeleAlgo;
import fr.iut.sae.app.model.dto.EtudiantDTO;

import java.util.ArrayList;
import java.util.List;

public class BruteForceS3 implements modeleAlgo.AlgorithmeGroupes {

    private static final int LIMITE_ETUDIANTS = 18;

    private final int nombreGroupes;

    private List<Integer> meilleureAffectation;
    private int meilleurScore;

    public BruteForceS3(int nombreGroupes) {
        this.nombreGroupes = nombreGroupes;
    }

    @Override
    public String nom() {
        return "Brute force S3";
    }

    @Override
    public List<List<EtudiantDTO>> construireGroupes(List<EtudiantDTO> etudiants) {
        if (etudiants == null || etudiants.isEmpty()) {
            throw new IllegalArgumentException("Liste d'étudiants vide.");
        }
        if (nombreGroupes <= 0) {
            throw new IllegalArgumentException("Nombre de groupes invalide : " + nombreGroupes);
        }
        if (etudiants.size() > LIMITE_ETUDIANTS) {
            throw new IllegalArgumentException(
                    "Brute force limité à " + LIMITE_ETUDIANTS + " étudiants. nombre d'etudiant " + etudiants.size()
            );
        }

        int nbGroupesReels = calculerNombreGroupesReel(etudiants.size(), nombreGroupes);

        int n = etudiants.size();

        int[] affectationCourante = new int[n];
        int i = 0;
        while (i < n) {
            affectationCourante[i] = -1;
            i++;
        }

        int[] taillesGroupes = new int[nbGroupesReels];

        meilleureAffectation = null;
        meilleurScore = -1;

        backtrack(etudiants, 0, affectationCourante, taillesGroupes, nbGroupesReels);

        if (meilleureAffectation == null) {
            throw new IllegalStateException("Aucune solution trouvée.");
        }

        return construireDepuisAffectation(etudiants, meilleureAffectation, nbGroupesReels);
    }

    private void backtrack(List<EtudiantDTO> etudiants,
                           int positionEtudiant,
                           int[] affectationCourante,
                           int[] taillesGroupes,
                           int nbGroupes) {

        if (positionEtudiant == etudiants.size()) {

            List<Integer> affectationListe = new ArrayList<>();
            int k = 0;
            while (k < affectationCourante.length) {
                affectationListe.add(affectationCourante[k]);
                k++;
            }

            List<List<EtudiantDTO>> solution = construireDepuisAffectation(etudiants, affectationListe, nbGroupes);

            int score;
            try {
                score = scoreS3.scoreSolution(solution);
            } catch (Exception ex) {
                score = 0;
            }

            if (meilleureAffectation == null || score > meilleurScore) {
                meilleurScore = score;
                meilleureAffectation = affectationListe;
            }
            return;
        }

        affectationCourante[positionEtudiant] = -1;
        backtrack(etudiants, positionEtudiant + 1, affectationCourante, taillesGroupes, nbGroupes);

        int numeroGroupe = 0;
        while (numeroGroupe < nbGroupes) {
            if (taillesGroupes[numeroGroupe] < scoreS3.TAILLE_MAX) {
                affectationCourante[positionEtudiant] = numeroGroupe;
                taillesGroupes[numeroGroupe]++;

                backtrack(etudiants, positionEtudiant + 1, affectationCourante, taillesGroupes, nbGroupes);

                taillesGroupes[numeroGroupe]--;
            }
            numeroGroupe++;
        }

        affectationCourante[positionEtudiant] = -1;
    }

    private List<List<EtudiantDTO>> construireDepuisAffectation(List<EtudiantDTO> etudiants,
                                                               List<Integer> affectation,
                                                               int nbGroupes) {
        List<List<EtudiantDTO>> groupes = new ArrayList<>();

        int i = 0;
        while (i < nbGroupes) {
            groupes.add(new ArrayList<>());
            i++;
        }

        int j = 0;
        while (j < etudiants.size()) {
            int groupe = affectation.get(j);
            if (groupe >= 0 && groupe < nbGroupes) {
                groupes.get(groupe).add(etudiants.get(j));
            }
            j++;
        }

        return groupes;
    }

    private int calculerNombreGroupesReel(int nbEtudiants, int nbDemande) {
        int nbMaxAvecMin = nbEtudiants / scoreS3.TAILLE_MIN;
        if (nbMaxAvecMin <= 0) nbMaxAvecMin = 1;

        if (nbDemande > nbMaxAvecMin) {
            return nbMaxAvecMin;
        }
        return nbDemande;
    }

    public int getMeilleurScore() {
        return meilleurScore;
    }
}
