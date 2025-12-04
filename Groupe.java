import java.util.ArrayList;
public class Groupe{
	private ArrayList<Etudiant> etudiants;
	public float mathsMoyenne;
	public float infoMoyenne;
	public int nbrFilles;
	public static int nbrMaxEtu = 18, nbrMinEtu = 16;

	public void addEtudiant(Etudiant etudiant){
		if(etudiants.size()<nbrMaxEtu){
			etudiants.add(etudiant);
			mathsMoyenne = MathsMoyenne();
			infoMoyenne = InfoMoyenne();
			nbrFilles = NbrFilles();
			etudiant.setGroupe(this);
		}
		else
			System.out.println("Erreur, taille du groupe maximale");
	}

	public Groupe(ArrayList<Etudiant> etudiants){
		this.etudiants = etudiants;
		this.mathsMoyenne = MathsMoyenne();
                this.infoMoyenne = InfoMoyenne();
                this.nbrFilles = NbrFilles();
		for(Etudiant e : etudiants)
			e.setGroupe(this);

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

}
