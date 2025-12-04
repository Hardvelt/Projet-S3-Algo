public class Etudiant {
	public float moyenne;
	public float infoMoyenne;
	public float mathsMoyenne;
	public int groupeCovoit;
	public boolean estRedoublant;
	public boolean estSecAnglo;
	boolean estFille;
	Groupe groupe;

	public Etudiant(float moyenne, float infoMoyenne, float mathsMoyenne, int groupeCovoit, boolean estRedoublant, boolean estSecAnglo){
		this.moyenne = moyenne;
		this.infoMoyenne = infoMoyenne;
		this.mathsMoyenne = mathsMoyenne;
		this.groupeCovoit = groupeCovoit;
		this.estRedoublant = estRedoublant;
		this.estSecAnglo = estSecAnglo;
		this.groupe = null;
	}

	public setGroupe(Groupe groupe){
		this.groupe = groupe;
	}
}
