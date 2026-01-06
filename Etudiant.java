import java.util.ArrayList;
public class Etudiant extends Utilisateur{
	public String emailPerso;
	public boolean actif;
	public float moyenne;
	public float infoMoyenne;
	public float mathsMoyenne;
	public int groupeCovoit;
	public boolean estRedoublant;
	public boolean estAlternant;
	public boolean estSecAnglo;
	public boolean estFille;
	
	public Etudiant(int idUtilisateur, String nom, String prenom, String passwordHash, String emailPro, String emailPerso, float moyenne, float infoMoyenne, float mathsMoyenne, int groupeCovoit, boolean estRedoublant, boolean estSecAnglo){
		super(idUtilisateur, nom, prenom, passwordHash, emailPro);
		this.emailPerso = emailPerso;
		this.moyenne = moyenne;
		this.infoMoyenne = infoMoyenne;
		this.mathsMoyenne = mathsMoyenne;
		this.groupeCovoit = groupeCovoit;
		this.estRedoublant = estRedoublant;
		this.estSecAnglo = estSecAnglo;
		this.estAlternant = false;
		this.estFille = false;
	}

	public int idEtudiant(){ return super.idUtilisateur;}

	public String toString(){
		return this.nom +" " + this.prenom + " (" + this.idEtudiant() + ") ";
	}

	public float Score(){
		return (4*this.moyenne+3*this.mathsMoyenne+2*this.infoMoyenne)/9;
	}	
}
