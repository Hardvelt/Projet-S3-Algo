package fr.iut.sae.app.algo.s3;

import fr.iut.sae.app.algo.commun.modeleAlgo;
import fr.iut.sae.app.model.dto.EtudiantDTO;

import java.util.ArrayList;
import java.util.List;

public class GloutonS3_2 implements modeleAlgo.AlgorithmeGroupes {

    private final int nombreGroupes;

    private static final int MIN_FILLES = 5;
    private static final int MIN_GARCONS = 5;
    private static final int MAX_REDOUBLANTS_PAR_GROUPE = 3;

    public GloutonS3_2(int nombreGroupes) {
        this.nombreGroupes = nombreGroupes;
    }

    @Override
    public String nom() {
        return "Glouton S3_2";
    }

    @Override
    public List<List<EtudiantDTO>> construireGroupes(List<EtudiantDTO> etudiants) {
        List<List<EtudiantDTO>> solution = new ArrayList<>();
        if (etudiants == null) return solution;
        if (etudiants.isEmpty()) return solution;
        if (nombreGroupes <= 0) return solution;

        boolean modeApprentis = false;
        if (etudiants.get(0) != null && etudiants.get(0).getEstApprenti() == 1) modeApprentis = true;

        List<EtudiantDTO> restants = new ArrayList<>();
        for (EtudiantDTO e : etudiants) {
            if (e == null) continue;

            boolean estApprenti = (e.getEstApprenti() == 1);

            if (modeApprentis) {
                if (estApprenti) restants.add(e);
            } else {
                if (!estApprenti) restants.add(e);
            }
        }

        int g = 0;
        while (g < nombreGroupes) {
            if (restants.isEmpty()) return solution;

            int groupesRestants = nombreGroupes - g;
            int tailleCible = tailleCible(restants.size(), groupesRestants);

            List<EtudiantDTO> groupe = new ArrayList<>();
            remplirRec(restants, 0, groupe, tailleCible);

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

    private void remplirRec(List<EtudiantDTO> restants, int pos, List<EtudiantDTO> groupe, int tailleCible) {
        if (groupe.size() >= tailleCible) return;
        if (groupe.size() >= scoreS3.TAILLE_MAX) return;
        if (restants.isEmpty()) return;

        if (pos >= restants.size()) {
            if (groupe.size() >= scoreS3.TAILLE_MIN) return;

            EtudiantDTO e = restants.get(0);
            groupe.add(e);
            restants.remove(0);
            remplirRec(restants, 0, groupe, tailleCible);
            return;
        }

        EtudiantDTO e = restants.get(pos);
        if (e == null) {
            remplirRec(restants, pos + 1, groupe, tailleCible);
            return;
        }

        boolean ajoute = false;

        if (!ajoute) {
            if (e.getIndiceCovoiturage() > 0) {
                int indice = e.getIndiceCovoiturage();

                List<EtudiantDTO> pack = new ArrayList<>();
                List<Integer> positions = new ArrayList<>();

                int i = 0;
                while (i < restants.size()) {
                    EtudiantDTO x = restants.get(i);
                    if (x != null && x.getIndiceCovoiturage() == indice) {
                        pack.add(x);
                        positions.add(i);
                    }
                    i++;
                }

                if (pack.size() >= 2) {
                    if (packRentre(groupe, pack, tailleCible)) {
                        int k = 0;
                        while (k < pack.size()) {
                            groupe.add(pack.get(k));
                            k++;
                        }

                        int r = positions.size() - 1;
                        while (r >= 0) {
                            restants.remove((int) positions.get(r));
                            r--;
                        }

                        ajoute = true;
                    }
                }
            }
        }

        if (!ajoute) {
            int nbFilles = scoreS3.compterFilles(groupe);
            int nbGarcons = scoreS3.compterGarcons(groupe);

            boolean estFille = (e.getGenreEtu() == 1);
            boolean estGarcon = !estFille;

            if (estFille && nbFilles < MIN_FILLES) {
                if (peutAjouterStrict(groupe, e)) {
                    groupe.add(e);
                    restants.remove(pos);
                    ajoute = true;
                }
            } else if (estGarcon && nbGarcons < MIN_GARCONS) {
                if (peutAjouterStrict(groupe, e)) {
                    groupe.add(e);
                    restants.remove(pos);
                    ajoute = true;
                }
            }
        }

        if (!ajoute) {
            if (peutAjouterStrict(groupe, e)) {
                groupe.add(e);
                restants.remove(pos);
                ajoute = true;
            }
        }

        if (!ajoute) {
            if (groupe.size() < scoreS3.TAILLE_MIN) {
                if (groupe.size() < scoreS3.TAILLE_MAX) {
                    groupe.add(e);
                    restants.remove(pos);
                    ajoute = true;
                }
            }
        }

        if (ajoute) remplirRec(restants, pos, groupe, tailleCible);
        else remplirRec(restants, pos + 1, groupe, tailleCible);
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

    private boolean packRentre(List<EtudiantDTO> groupe, List<EtudiantDTO> pack, int tailleCible) {
        if (groupe.size() + pack.size() > scoreS3.TAILLE_MAX) return false;

        boolean okTaille = (groupe.size() + pack.size() <= tailleCible) || (groupe.size() < scoreS3.TAILLE_MIN);

        if (!okTaille) return false;

        int nbRedGroupe = scoreS3.compterRedoublants(groupe);
        int nbRedPack = 0;

        int i = 0;
        while (i < pack.size()) {
            EtudiantDTO e = pack.get(i);
            if (e != null && e.getEstRedoublant() == 1) nbRedPack++;
            i++;
        }

        return nbRedGroupe + nbRedPack <= MAX_REDOUBLANTS_PAR_GROUPE;
    }
}

