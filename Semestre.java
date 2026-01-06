import java.util.ArrayList;
import java.lang.Math;

public class Semestre {
	public ArrayList<Groupe> groupes;
	private int numSemestre;
	private int nombreMaxCovoiturage;
	public boolean estApprentissage;
	public Enseignant responsable;

	public Semestre(ArrayList<Groupe> groupes, int numSemestre, int nombreMaxCovoiturage, boolean estApprentissage, Enseignant responsable){
		this.groupes = groupes;
		this.numSemestre = numSemestre;
		this.nombreMaxCovoiturage = nombreMaxCovoiturage;
		this.estApprentissage = estApprentissage;
		this.responsable = responsable;
	}

	public Semestre(Semestre semestreCpy){
		this.groupes = new ArrayList<Groupe>();
		for(Groupe g : semestreCpy.groupes)
			this.groupes.add(new Groupe(g));
		this.numSemestre = semestreCpy.numSemestre();
		this.nombreMaxCovoiturage = semestreCpy.nombreMaxCovoiturage();
		this.estApprentissage = semestreCpy.estApprentissage;
		this.responsable = semestreCpy.responsable;
	}

	public Semestre(int numSemestre, int nombreMaxCovoiturage, boolean estApprentissage){
		this.groupes = null;
		this.numSemestre = numSemestre;
		this.nombreMaxCovoiturage = nombreMaxCovoiturage;
		this.estApprentissage = estApprentissage;
	}

	public int numSemestre(){
		return this.numSemestre;
	}

	public boolean numSemestre(int numSemestre){
		if(numSemestre >= 0 && numSemestre <= 6){
			this.numSemestre = numSemestre;
			return true;
		}
		else
			return false;
	}

	public Groupe BestScore(Etudiant etu, ArrayList<Etudiant> etudiantsCpy, int tailleGroupe) throws IllegalArgumentException{
		if(etu.estSecAnglo || etu.estFille){
			
			int bestGroupe = -1;
			int bestScore =	Integer.MAX_VALUE;
			boolean full = false;
			for(int i = 0; i < 2; i++){
				Semestre test = new Semestre(this);

				if(test.groupes.get(i).etudiants().size() + 1 > tailleGroupe)
					continue;
				test.groupes.get(i).addEtudiant(etu);
				int testScore = test.Score(etudiantsCpy);
				if(testScore < bestScore){
					bestScore = testScore;
					bestGroupe = i;
				}
			}
			if(bestScore == Integer.MAX_VALUE)
				return BestScore(etu, etudiantsCpy, tailleGroupe+1);
			
			return this.groupes.get(bestGroupe);
		}
		else{
			int bestGroupe = -1;
			int bestScore =	Integer.MAX_VALUE;
			boolean full = false;
			for(int i = 0; i < groupes.size(); i++){
				Semestre test = new Semestre(this);

				if(test.groupes.get(i).etudiants().size() + 1 > tailleGroupe)
					continue;
				test.groupes.get(i).addEtudiant(etu);
				int testScore = test.Score(etudiantsCpy);
				if(testScore < bestScore){
					bestScore = testScore;
					bestGroupe = i;
				}
			}
			if(bestScore == Integer.MAX_VALUE)
				return BestScore(etu, etudiantsCpy, tailleGroupe+1);
			
			return this.groupes.get(bestGroupe);
		}
	}


	public int Score(ArrayList<Etudiant> etudiants){
		int scoreEtus = 0;
		int scoreSem = 0;

		for(int i = 0; i < etudiants.size(); i++)
			scoreEtus += etudiants.get(i).Score();	
		scoreEtus /= etudiants.size();

		for(int i = 0; i < this.groupes.size(); i++)
			scoreSem += Math.abs(this.groupes.get(i).Score() - scoreEtus);	
		scoreSem /= this.groupes.size();

		return scoreSem;
	}

	public int nombreMaxCovoiturage(){
		return this.nombreMaxCovoiturage;
	}

	public boolean nombreMaxCovoiturage(int nombreMaxCovoiturage){
		if(nombreMaxCovoiturage >= 2 && nombreMaxCovoiturage <= 4){
			this.nombreMaxCovoiturage = nombreMaxCovoiturage;
			return true;
		}
		else
			return false;
	}

	public String toString(){
		String affichage = "Semestre n°" + this.numSemestre;
		if(this.estApprentissage)
			affichage += " en apprentissage";
		else
			affichage += " en initial.";
		affichage +=/* " " + this.responsable.toString() + " autorise " +*/ this.nombreMaxCovoiturage + " etudiants a etre dans le meme groupe de covoiturage.";
		for(Groupe g : groupes)
			affichage += "\n\t" + g.toString();
		return affichage;
	}
}
