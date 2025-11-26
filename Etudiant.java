public class Etudiant {
	public int moyenne;
	public int infoMoyenne;
	public int mathsMoyenne;
	public int groupeCovoit;
	public boolean estRedoublant;
	public boolean estSecAnglo;
	boolean estFille;

	public Etudiant(int moyenne, int infoMoyenne, int mathsMoyenne, int groupeCovoit, boolean estRedoublant, boolean estSecAnglo){
		this.moyenne = moyenne;
		this.infoMoyenne = infoMoyenne;
		this.mathsMoyenne = mathsMoyenne;
		this.groupeCovoit = groupeCovoit;
		this.estRedoublant = estRedoublant;
		this.estSecAnglo = estSecAnglo;
	}
}
