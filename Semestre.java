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
	
	public Groupe scoreMinimal(ArrayList<Etudiant> etudiantsCpy, int tailleGroupe, boolean plusUnAutoriser, ArrayList<Etudiant> etus) throws IllegalArgumentException{
		return scoreMinimal(etudiantsCpy, tailleGroupe, plusUnAutoriser, etus.toArray(new Etudiant[0]));
	}

	public Groupe scoreMinimal(ArrayList<Etudiant> etudiantsCpy, int tailleGroupe, boolean plusUnAutoriser, Etudiant... etus) throws IllegalArgumentException{

		boolean contientAnglo = false;
		for(Etudiant e : etus){
			contientAnglo = e.estSecAnglo || e.estFille; 
			if(contientAnglo)
				break;
		}	
		
		int indiceGroupeOptimal = -1;

		int nbrGroupes = contientAnglo ? 2 : this.groupes.size();
		indiceGroupeOptimal = indiceGroupeOptimal(etudiantsCpy, tailleGroupe, nbrGroupes, etus); 
		
		if(indiceGroupeOptimal == -1 && plusUnAutoriser)
			indiceGroupeOptimal = indiceGroupeOptimal(etudiantsCpy, tailleGroupe+1, nbrGroupes, etus);
		if(indiceGroupeOptimal == -1)
			throw new IllegalArgumentException("Impossibilité de respecter les contraintes");

		return this.groupes.get(indiceGroupeOptimal);
	}

	private int indiceGroupeOptimal(ArrayList<Etudiant> etudiantsCpy, int tailleGroupe, int nbrGroupes, Etudiant... etus){
		int indiceGroupeOptimal = -1;
		int scoreMinimal = Integer.MAX_VALUE;
		for(int i = 0; i < nbrGroupes; i++){
			Semestre test = new Semestre(this);

			if(test.groupes.get(i).etudiants().size() + etus.length > tailleGroupe)
				continue;
			test.groupes.get(i).addEtudiants(etus);
			int testScore = test.Score(etudiantsCpy);
			if(testScore < scoreMinimal){
				scoreMinimal = testScore;
				indiceGroupeOptimal = i;
			}
		}
		return indiceGroupeOptimal;
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
