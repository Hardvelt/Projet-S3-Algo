package fr.iut.sae.app.algo.s3;

import fr.iut.sae.app.algo.commun.modeleAlgo;
import fr.iut.sae.app.model.dto.EtudiantDTO;

import java.util.List;

public class scoreS3 implements modeleAlgo.FonctionScore {

    public static final int TAILLE_MIN = 14;
    public static final int TAILLE_MAX = 18;

    public static final int FILLES_MIN = 5;
    public static final int GARCONS_MIN = 5;
	private static final int REDOUBLANTS_MAX = 3;

    public scoreS3() {
    }

    @Override
    public String nom() {
        return "Score S3";
    }

    @Override
    public int scoreSolutionSur100(List<List<EtudiantDTO>> solution) {
        return scoreSolution(solution);
    }

    @Override
    public int scoreGroupeSur100(List<EtudiantDTO> groupe) {
        int score = 100;
        score -= penaliteGroupe(groupe);
        if (score < 0) score = 0;
        if (score > 100) score = 100;
        return score;
    }
    
    

    public static int scoreSolution(List<List<EtudiantDTO>> solution) {
        verifierSolutionHomogeneStatut(solution);

        int score = 100;
        if (solution == null || solution.isEmpty()) return 0;

        for (List<EtudiantDTO> g : solution) {
            score -= penaliteGroupe(g);
        }

        if (score < 0) score = 0;
        if (score > 100) score = 100;
        return score;
    }

    private static int penaliteGroupe(List<EtudiantDTO> g) {
        if (g == null) return 30;

        int pen = 0;

        int n = g.size();
        if (n < TAILLE_MIN) pen += (TAILLE_MIN - n) * 6;
        if (n > TAILLE_MAX) pen += (n - TAILLE_MAX) * 6;

        int filles = compterFilles(g);
        if (filles < FILLES_MIN) pen += (FILLES_MIN - filles) * 8;
        
        int red = compterRedoublants(g);
        if (red > REDOUBLANTS_MAX) pen += (red - REDOUBLANTS_MAX) * 10;
        
        int garcons = compterGarcons(g);
        if (garcons < GARCONS_MIN) pen += (GARCONS_MIN - garcons) * 8;

        return pen;
    }

    public static void verifierSolutionHomogeneStatut(List<List<EtudiantDTO>> solution) {
        if (solution == null) return;

        boolean aVuApprentis = false;
        boolean aVuInitiaux = false;

        for (List<EtudiantDTO> g : solution) {
            if (g == null) continue;

            for (EtudiantDTO e : g) {
                if (e == null) continue;

                if (e.getEstApprenti() == 1) aVuApprentis = true;
                else aVuInitiaux = true;
            }
        }

        if (aVuApprentis && aVuInitiaux) {
            throw new IllegalArgumentException("Erreur : mélange alternants et initiaux dans la même classe.");
        }
    }

    public static int compterFilles(List<EtudiantDTO> g) {
        int c = 0;
        if (g == null) return 0;
        for (EtudiantDTO e : g) {
            if (e != null && e.getGenreEtu() == 1) c++;
        }
        return c;
    }

    public static int compterGarcons(List<EtudiantDTO> g) {
        int c = 0;
        if (g == null) return 0;
        for (EtudiantDTO e : g) {
            if (e != null && e.getGenreEtu() != 1) c++;
        }
        return c;
    }

    public static int compterRedoublants(List<EtudiantDTO> g) {
        int c = 0;
        if (g == null) return 0;
        for (EtudiantDTO e : g) {
            if (e != null && e.getEstRedoublant() == 1) c++;
        }
        return c;
    }

    public static int compterAnglo(List<EtudiantDTO> g) {
        int c = 0;
        if (g == null) return 0;
        for (EtudiantDTO e : g) {
            if (e != null && e.getEstAnglophone() == 1) c++;
        }
        return c;
    }
}



