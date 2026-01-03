import java.util.ArrayList;
import java.util.Scanner;

public class Formation{
	public String nom;
	public Enseignant directeur;
	public ArrayList<Semestre> semestres;
	public ArrayList<Etudiant> etudiants;

	public Formation(String nom, Enseignant directeur, ArrayList<Semestre> semestres, ArrayList<Etudiant> etudiants){
		this.nom = nom;
		this.directeur = directeur;
		this.semestres = semestres;
		this.etudiants = etudiants;
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

	public static void main(String[] args){
		int a = 2+2;
	}
}
