import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
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
		int i = 0;
		ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>(	
				Arrays.asList(
					new Etudiant(++i, "", "", "", "", "", 12, 11, 13, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 10, 10, 9, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 16, 17, 15, 0, false, true), 
					new Etudiant(++i, "", "", "", "", "", 11, 12, 10, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 13, 14, 12, 0, false, true), 
					new Etudiant(++i, "", "", "", "", "", 9, 8, 10, 0, true, false), 
					new Etudiant(++i, "", "", "", "", "", 15, 15, 16, 0, false, true), 
					new Etudiant(++i, "", "", "", "", "", 10, 9, 11, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 17, 18, 16, 0, false, true), 

					new Etudiant(++i, "", "", "", "", "", 12, 13, 11, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 14, 13, 15, 0, false, true), 
					new Etudiant(++i, "", "", "", "", "", 9, 8, 10, 0, true, false), 
					new Etudiant(++i, "", "", "", "", "", 16, 16, 17, 0, false, true), 
					new Etudiant(++i, "", "", "", "", "", 10, 11, 9, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 13, 12, 14, 0, false, true), 
					new Etudiant(++i, "", "", "", "", "", 11, 10, 12, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 15, 14, 16, 0, false, true), 
					new Etudiant(++i, "", "", "", "", "", 12, 11, 13, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 14, 15, 13, 0, false, true), 

					new Etudiant(++i, "", "", "", "", "", 8, 9, 7, 0, true, false), 
					new Etudiant(++i, "", "", "", "", "", 13, 14, 12, 0, false, true), 
					new Etudiant(++i, "", "", "", "", "", 15, 16, 15, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 11, 10, 12, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 14, 15, 13, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 9, 9, 8, 0, true, false), 
					new Etudiant(++i, "", "", "", "", "", 16, 17, 15, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 12, 11, 13, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 10, 10, 10, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 17, 16, 18, 0, false, false), 

					new Etudiant(++i, "", "", "", "", "", 13, 12, 14, 1, false, false), 
					new Etudiant(++i, "", "", "", "", "", 15, 16, 14, 1, false, false), 
					new Etudiant(++i, "", "", "", "", "", 12, 13, 11, 1, false, false), 
					new Etudiant(++i, "", "", "", "", "", 16, 17, 16, 1, false, false), 

					new Etudiant(++i, "", "", "", "", "", 11, 12, 10, 2, false, false), 
					new Etudiant(++i, "", "", "", "", "", 14, 13, 15, 2, false, false), 
					new Etudiant(++i, "", "", "", "", "", 10, 9, 11, 2, false, false), 

					new Etudiant(++i, "", "", "", "", "", 15, 15, 16, 3, false, false), 
					new Etudiant(++i, "", "", "", "", "", 12, 11, 13, 3, false, false), 

					new Etudiant(++i, "", "", "", "", "", 13, 14, 12, 4, false, false), 
					new Etudiant(++i, "", "", "", "", "", 11, 10, 12, 4, false, false), 

					new Etudiant(++i, "", "", "", "", "", 16, 17, 15, 5, false, false), 
					new Etudiant(++i, "", "", "", "", "", 10, 11, 9, 5, false, false), 

					new Etudiant(++i, "", "", "", "", "", 14, 15, 13, 6, false, false), 
					new Etudiant(++i, "", "", "", "", "", 9, 8, 10, 6, true, false), 

					new Etudiant(++i, "", "", "", "", "", 13, 12, 14, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 11, 12, 10, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 15, 14, 16, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 12, 13, 11, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 17, 18, 16, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 13, 14, 12, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 11, 10, 12, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 15, 15, 16, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 12, 11, 13, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 14, 15, 13, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 10, 9, 11, 0, true, false), 
					new Etudiant(++i, "", "", "", "", "", 16, 17, 15, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 9, 8, 10, 0, true, false), 
					new Etudiant(++i, "", "", "", "", "", 17, 18, 16, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 12, 12, 11, 0, false, false), 

					new Etudiant(++i, "", "", "", "", "", 14, 14, 15, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 10, 10, 9, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 13, 12, 14, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 11, 12, 10, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 15, 16, 14, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 12, 11, 13, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 9, 9, 8, 0, true, false), 
					new Etudiant(++i, "", "", "", "", "", 16, 17, 16, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 10, 11, 9, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 14, 13, 15, 0, false, false), 

					new Etudiant(++i, "", "", "", "", "", 15, 14, 16, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 11, 10, 12, 13, false, false), 
					new Etudiant(++i, "", "", "", "", "", 12, 13, 11, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 13, 12, 14, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 10, 9, 11, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 16, 16, 17, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 13, 14, 12, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 15, 15, 16, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 11, 12, 10, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 17, 18, 16, 0, false, false), 

					new Etudiant(++i, "", "", "", "", "", 9, 8, 10, 0, true, false), 
					new Etudiant(++i, "", "", "", "", "", 12, 12, 13, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 14, 15, 13, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 11, 10, 12, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 13, 14, 12, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 10, 10, 9, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 15, 16, 15, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 12, 13, 11, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 16, 17, 15, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 14, 13, 15, 0, false, false), 

					new Etudiant(++i, "", "", "", "", "", 11, 10, 12, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 12, 12, 13, 12, false, false), 
					new Etudiant(++i, "", "", "", "", "", 13, 14, 12, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 10, 9, 11, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 16, 17, 16, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 11, 12, 10, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 14, 14, 15, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 13, 12, 14, 10, false, false), 
					new Etudiant(++i, "", "", "", "", "", 17, 16, 18, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 12, 11, 13, 0, false, false), 

					new Etudiant(++i, "", "", "", "", "", 13, 12, 14, 7, false, false), 
					new Etudiant(++i, "", "", "", "", "", 14, 15, 13, 7, false, false), 
					new Etudiant(++i, "", "", "", "", "", 12, 11, 13, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 15, 14, 16, 0, false, false), 

					new Etudiant(++i, "", "", "", "", "", 11, 12, 10, 8, false, false), 
					new Etudiant(++i, "", "", "", "", "", 14, 13, 15, 8, false, false), 
					new Etudiant(++i, "", "", "", "", "", 10, 9, 11, 8, false, false), 

					new Etudiant(++i, "", "", "", "", "", 16, 16, 17, 9, false, false), 
					new Etudiant(++i, "", "", "", "", "", 12, 13, 11, 9, false, false), 

					new Etudiant(++i, "", "", "", "", "", 15, 15, 16, 10, false, false), 
					new Etudiant(++i, "", "", "", "", "", 11, 10, 12, 0, false, false), 

					new Etudiant(++i, "", "", "", "", "", 13, 12, 14, 11, false, false), 
					new Etudiant(++i, "", "", "", "", "", 9, 8, 10, 11, true, false), 

					new Etudiant(++i, "", "", "", "", "", 14, 13, 15, 12, false, false), 
					new Etudiant(++i, "", "", "", "", "", 12, 12, 13, 0, false, false), 

					new Etudiant(++i, "", "", "", "", "", 10, 10, 9, 13, false, false), 
					new Etudiant(++i, "", "", "", "", "", 15, 16, 14, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 9, 14, 12, 0, false, false), 

					new Etudiant(++i, "", "", "", "", "", 13, 14, 12, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 11, 12, 10, 0, false, false), 
					new Etudiant(++i, "", "", "", "", "", 16, 17, 15, 0, false, false))); 

		Semestre s1 = new Semestre(1, 4, false);
		CreationGroupesControleur.CreerGroupesGlouton(7, s1, etudiants);
		System.out.println(s1);
	}
}
