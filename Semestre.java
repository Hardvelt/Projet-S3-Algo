import java.util.ArrayList;

public abstract class Semestre {
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

	public int nombreMaxCovoiturage(){
                return this.numSemestre;
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
		affichage += " " + this.responsable.toString() + " autorise " + this.nombreMaxCovoiturage + " etudiants a etre dans le meme groupe de covoiturage.";
		for(Groupe g : groupes)
			affichage += "\n\t" + g.toString();
		return affichage;
	}
}
