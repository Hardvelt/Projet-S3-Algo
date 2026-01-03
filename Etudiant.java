import java.util.ArrayList;
public class Etudiant extends Utilisateur{
	private int idUtilisateur;
	public String nom;
	public String prenom;
	public String passwordHash;
	public String emailPro;
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
	public ArrayList<Reponse> reponses;

	public Etudiant(int idUtilisateur, String nom, String prenom, String passwordHash, String emailPro, String emailPerso, float moyenne, float infoMoyenne, float mathsMoyenne, int groupeCovoit, boolean estRedoublant, boolean estSecAnglo, ArrayList<Reponse> reponses){
		super(idUtilisateur, nom, prenom, passwordHash, emailPro);
		this.emailPerso = emailPerso;
		this.moyenne = moyenne;
		this.infoMoyenne = infoMoyenne;
		this.mathsMoyenne = mathsMoyenne;
		this.groupeCovoit = groupeCovoit;
		this.estRedoublant = estRedoublant;
		this.estSecAnglo = estSecAnglo;
		this.reponses = reponses;
	}

	public int idEtudiant(){ return this.idUtilisateur;}

	public String toString(){
		return this.nom +" " + this.prenom + " (" + this.idUtilisateur + ")";
	}
}
