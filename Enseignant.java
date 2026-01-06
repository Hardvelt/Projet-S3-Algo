import java.util.ArrayList;
public class Enseignant extends Utilisateur {
	public ArrayList<Matiere> matieres;

	public Enseignant(int idUtilisateur, String nom, String prenom, String passwordHash, String emailPro, ArrayList<Matiere> matieres) {
		super(idUtilisateur, nom, prenom, passwordHash, emailPro);
		this.matieres = matieres;
	}

	public String toString(){
		return this.nom +" " + this.prenom + " (" + this.idUtilisateur + ")";
	}
}
