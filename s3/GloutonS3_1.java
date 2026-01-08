package fr.iut.sae.app.algo.s3;

import fr.iut.sae.app.algo.commun.modeleAlgo;
import fr.iut.sae.app.model.dto.EtudiantDTO;

import java.util.ArrayList;
import java.util.List;

public class GloutonS3_1 implements modeleAlgo.AlgorithmeGroupes {

    private final int nombreGroupes;

    private static final int MIN_FILLES = 5;
    private static final int MIN_GARCONS = 5;
    private static final int MAX_REDOUBLANTS_PAR_GROUPE = 3;

    public GloutonS3_1(int nombreGroupes) {
        this.nombreGroupes = nombreGroupes;
    }

    @Override
    public String nom() {
        return "Glouton S3_1";
    }

    @Override
    public List<List<EtudiantDTO>> construireGroupes(List<EtudiantDTO> etudiants) {
        List<List<EtudiantDTO>> solution = new ArrayList<>();

        if (etudiants == null) return solution;
        if (etudiants.isEmpty()) return solution;
        if (nombreGroupes <= 0) return solution;

        List<EtudiantDTO> restants = new ArrayList<>(etudiants);

        int g = 0;
        while (g < nombreGroupes) {
            if (restants.isEmpty()) return solution;

            int groupesRestants = nombreGroupes - g;
            int tailleCible = tailleCible(restants.size(), groupesRestants);

            List<EtudiantDTO> groupe = new ArrayList<>();
            remplirGroupeRec(restants, 0, groupe, tailleCible);

            if (groupe.isEmpty()) return solution;

            solution.add(groupe);
            g++;
        }

        return solution;
    }

    private int tailleCible(int nbRestants, int groupesRestants) {
        int cible = (int) Math.ceil(nbRestants / (double) groupesRestants);
        if (cible < scoreS3.TAILLE_MIN) cible = scoreS3.TAILLE_MIN;
        if (cible > scoreS3.TAILLE_MAX) cible = scoreS3.TAILLE_MAX;
        return cible;
    }

    private void remplirGroupeRec(List<EtudiantDTO> restants, int index, List<EtudiantDTO> groupe, int tailleCible) {
        if (groupe.size() >= tailleCible) return;
        if (groupe.size() >= scoreS3.TAILLE_MAX) return;
        if (restants.isEmpty()) return;

        if (index >= restants.size()) {
            if (groupe.size() >= scoreS3.TAILLE_MIN) return;

            EtudiantDTO e = restants.get(0);
            groupe.add(e);
            restants.remove(0);
            remplirGroupeRec(restants, 0, groupe, tailleCible);
            return;
        }

        EtudiantDTO e = restants.get(index);
        if (e == null) {
            remplirGroupeRec(restants, index + 1, groupe, tailleCible);
            return;
        }

        int nbFilles = scoreS3.compterFilles(groupe);
        int nbGarcons = scoreS3.compterGarcons(groupe);

        boolean estFille = (e.getGenreEtu() == 1);
        boolean estGarcon = !estFille;

        boolean ajoute = false;

        if (!ajoute) {
            if (estFille && nbFilles < MIN_FILLES) {
                if (peutAjouterStrict(groupe, e)) {
                    groupe.add(e);
                    restants.remove(index);
                    ajoute = true;
                }
            }
        }

        if (!ajoute) {
            if (estGarcon && nbGarcons < MIN_GARCONS) {
                if (peutAjouterStrict(groupe, e)) {
                    groupe.add(e);
                    restants.remove(index);
                    ajoute = true;
                }
            }
        }

        if (!ajoute) {
            if (peutAjouterStrict(groupe, e)) {
                groupe.add(e);
                restants.remove(index);
                ajoute = true;
            }
        }

        if (!ajoute) {
            if (groupe.size() < scoreS3.TAILLE_MIN) {
                if (groupe.size() < scoreS3.TAILLE_MAX) {
                    groupe.add(e);
                    restants.remove(index);
                    ajoute = true;
                }
            }
        }

        if (ajoute) remplirGroupeRec(restants, index, groupe, tailleCible);
        else remplirGroupeRec(restants, index + 1, groupe, tailleCible);
    }

    private boolean peutAjouterStrict(List<EtudiantDTO> groupe, EtudiantDTO e) {
        if (e == null) return false;
        if (groupe.size() >= scoreS3.TAILLE_MAX) return false;

        if (e.getEstRedoublant() == 1) {
            int nbRed = scoreS3.compterRedoublants(groupe);
            if (nbRed >= MAX_REDOUBLANTS_PAR_GROUPE) return false;
        }

        return true;
    }
}



