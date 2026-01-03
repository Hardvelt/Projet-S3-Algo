import java.util.ArrayList;
public class Groupe{
	public String nom;
	private ArrayList<Etudiant> etudiants;
	private float moyenne;
	private float mathsMoyenne;
	private float infoMoyenne;
	private int nbrFilles;
	private float score;
	public static int nbrMaxEtu = 18, nbrMinEtu = 16;

	public void addEtudiant(Etudiant etudiant) throws IndexOutOfBoundsException {  
		if(etudiants.size()<nbrMaxEtu){
			this.etudiants.add(etudiant);
			this.moyenne = Moyenne();
			this.mathsMoyenne = MathsMoyenne();
			this.infoMoyenne = InfoMoyenne();
			this.nbrFilles = NbrFilles();
			this.score = Score();
		}
		else
			throw new IndexOutOfBoundsException();
	}

	public float score(){return score;}

	public ArrayList<Etudiant> etudiants(){ return this.etudiants;}

	public void addEtudiants(ArrayList<Etudiant> etudiants){
		for(Etudiant e : etudiants)
			addEtudiant(e);
	}

	public Groupe(String nom, ArrayList<Etudiant> etudiants){
		this.nom = nom;
		this.etudiants = etudiants;
		this.moyenne = Moyenne();
		this.mathsMoyenne = MathsMoyenne();
		this.infoMoyenne = InfoMoyenne();
		this.nbrFilles = NbrFilles();
		this.score = (4/9)*this.moyenne+(3/9)*this.mathsMoyenne+(2/9)*this.infoMoyenne;

	}

	private float Score(){
		return (4/9)*this.moyenne+(3/9)*this.mathsMoyenne+(2/9)*this.infoMoyenne;
	}

	private float Moyenne(){
		float moyenne = 0;
		for(Etudiant etudiant : etudiants)
			moyenne += etudiant.moyenne;
		moyenne /= etudiants.size();
		return moyenne;
	}


	private float MathsMoyenne(){
		float moyenne = 0;
		for(Etudiant etudiant : etudiants)
			moyenne += etudiant.mathsMoyenne;
		moyenne /= etudiants.size();
		return moyenne;
	}

	private float InfoMoyenne(){
		float moyenne = 0;
		for(Etudiant etudiant : etudiants)
			moyenne += etudiant.infoMoyenne;
		moyenne /= etudiants.size();
		return moyenne;
	}

	private int NbrFilles(){
		int filles = 0;
		for(Etudiant etudiant : etudiants)
			if(etudiant.estFille)
				filles++;
		return filles;
	}

	public String toString(){
		String affichage = "Le groupe " + this.nom + "sont score est de " + this.score + ", il contient " + this.etudiants.size() + " etudiants :\n";
		for(Etudiant e : this.etudiants){
			affichage += e.toString() + ", ";
		}
		return affichage;
	}
}
