import java.util.ArrayList;
public class Enseignant {
	private int idUtilisateur;
	public String nom;
	public String prenom;
	private String passwordHash;
	public String emailPro;
	public boolean actif;
	public ArrayList<Matiere> matieres;

	public Enseignant(int idUtilisateur, String nom, String prenom, String passwordHash, String emailPro, ArrayList<Matiere> matieres) {
		this.idUtilisateur = idUtilisateur;
		this.nom = nom;
		this.prenom = prenom;
		this.passwordHash = passwordHash;
		this.emailPro = emailPro;
		this.actif = false;
		this.matieres = matieres;
	}

	public String toString(){
		return this.nom +" " + this.prenom + " (" + this.idUtilisateur + ")";
	}
}
