package fr.iut.sae.app.algo.s1;

import fr.iut.sae.app.algo.commun.modeleAlgo;
import fr.iut.sae.app.model.dto.EtudiantDTO;
import java.util.List;

// J'ai renommé la classe en "ScoreS1"
public class ScoreS1 implements modeleAlgo.FonctionScore {

    @Override
    public String nom() {
        return "Score S1";
    }

    @Override
    public int scoreGroupeSur100(List<EtudiantDTO> groupe) {
        if (groupe == null || groupe.isEmpty()) return 0;

        int penalite = 0;

        // 1. Règle Taille (14-17)
        if (groupe.size() < 14) penalite += 50;
        if (groupe.size() > 17) penalite += 50;

        // 2. Règle Filles (min 3)
        int nbFilles = 0;
        for (EtudiantDTO e : groupe) {
            if (e.getGenreEtu() == 1) nbFilles++;
        }
        if (nbFilles < 3) penalite += 30;

        // 3. Règle Techno (cible 8)
        int nbTechno = 0;
        for (EtudiantDTO e : groupe) {
            if (e.getTypeBac() != null && e.getTypeBac().toLowerCase().contains("techno")) nbTechno++;
        }
        // Si écart > 3 par rapport à 8 (donc moins de 5 ou plus de 11), pénalité
        if (Math.abs(nbTechno - 8) > 3) penalite += 10;

        return Math.max(0, 100 - penalite);
    }

    @Override
    public int scoreSolutionSur100(List<List<EtudiantDTO>> solution) {
        if (solution == null || solution.isEmpty()) return 0;

        double total = 0;
        for (List<EtudiantDTO> groupe : solution) {
            total += scoreGroupeSur100(groupe);
        }
        
        return (int) Math.round(total / solution.size());
    }
    
    public int calculer(List<List<EtudiantDTO>> solution) {
        return scoreSolutionSur100(solution);
    }
}