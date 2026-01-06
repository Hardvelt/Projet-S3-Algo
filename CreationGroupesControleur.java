import java.util.ArrayList;
import java.util.Scanner;
public class CreationGroupesControleur{

	public static void CreerGroupesGlouton (int nbrGroupes, Semestre semestre, ArrayList<Etudiant> etudiantsCpy) throws IllegalArgumentException{

		ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>(etudiantsCpy);

		int tailleGroupe = (int)(etudiants.size()/nbrGroupes);

		if(!ValideCreationGroupes(nbrGroupes, etudiants))
			throw new IllegalArgumentException("La quantit de groupes devant etre crer n'est pas valide");

		ArrayList<ArrayList<Etudiant>> groupesCovoit = groupesCovoiturage(etudiants, semestre.nombreMaxCovoiturage());
		for(ArrayList<Etudiant> covoit : groupesCovoit){
			etudiants.removeAll(covoit);
		}

		ArrayList<Etudiant> etudiantsAnglo = EtudiantsAnglo(etudiants);
		etudiants.removeAll(etudiantsAnglo);

		ArrayList<Groupe> groupes = new ArrayList<Groupe>();
		for(int i = 0; i < nbrGroupes; i++){
			String nomGroupe = Integer.toString(semestre.numSemestre()) + (char)(i+65);//transforme 0, 1, 2... en A, B, C...
			groupes.add(new Groupe(nomGroupe, new ArrayList<Etudiant>()));
		}

		semestre.groupes = groupes;


		for(int i = 0; i < groupesCovoit.size(); i++){
			semestre.scoreMinimal(etudiantsCpy, tailleGroupe, false, groupesCovoit.get(i)).addEtudiants(groupesCovoit.get(i));
		}
		for(int i = 0; i < etudiantsAnglo.size(); i++){
			semestre.scoreMinimal(etudiantsCpy, tailleGroupe, false, etudiantsAnglo.get(i)).addEtudiant(etudiantsAnglo.get(i));
		}
		for(int i = 0; i < etudiants.size(); i++){
			semestre.scoreMinimal(etudiantsCpy, tailleGroupe, true, etudiants.get(i)).addEtudiant(etudiants.get(i));
		}
	}

	private static ArrayList<Etudiant> Etudiants(Semestre semestre){
		ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>();
		for(Groupe g : semestre.groupes){
			etudiants.addAll(g.etudiants());
		}
		return etudiants;
	}

	private static ArrayList<Etudiant> EtudiantsAnglo(ArrayList<Etudiant> etu){

		ArrayList<Etudiant> anglo = new ArrayList<Etudiant>();
		for(Etudiant e : etu)
			if(e.estSecAnglo)
				anglo.add(e);
		return anglo;
	}

	private static ArrayList<ArrayList<Etudiant>> groupesCovoiturage(ArrayList<Etudiant> etudiants, int maxTailleCovoit) throws IllegalArgumentException{
		int maxCovoit = 0;
		int minCovoit = Integer.MAX_VALUE;
		for(Etudiant e : etudiants){
			if(maxCovoit<e.groupeCovoit)
				maxCovoit = e.groupeCovoit;
			if(minCovoit > e.groupeCovoit && e.groupeCovoit != 0)
				minCovoit = e.groupeCovoit;
		}
		int stop = maxCovoit-minCovoit;

		ArrayList<ArrayList<Etudiant>> groupesCovoit = new ArrayList<ArrayList<Etudiant>>();

		for(int i = 0; i <= stop; i++)
			groupesCovoit.add(new ArrayList<Etudiant>());

		for(Etudiant e : etudiants){
			if(e.groupeCovoit != 0){
				if(groupesCovoit.get(e.groupeCovoit-minCovoit).size()>=maxTailleCovoit){
					throw new IllegalArgumentException("Le covoiturage " + e.groupeCovoit + " a trop de membre par rapport a ce qui est autorise (" + maxTailleCovoit + ")");
				}
				groupesCovoit.get(e.groupeCovoit-minCovoit).add(e);
			}
		}

		return groupesCovoit;
	}


	private static boolean ValideCreationGroupes (int nbrGroupes, ArrayList<Etudiant> etudiants){
		int check = checkNbrGroupes(nbrGroupes, etudiants);
		if(check == 1){
			System.out.println("Veuillez choisir un nombre de groupes plus elevee.");
			return false;
		}
		if(check == 2){
			Scanner sc = new Scanner(System.in);
			System.out.println("La taille moyenne des groupes sera infrieur a " + (int)etudiants.size()/nbrGroupes + " ce qui est en dessous de la taille minimale (" + Groupe.nbrMinEtu + ").\n Voulez vous continuez quand meme ?Y/n");
			String input = sc.next();
			while(!input.equals("Y") && !input.equals("n")){
				System.out.println("Veuillez entrez Y ou n");
				input = sc.next();
			}
			if(input.equals("n"))
				return false;
		}
		return true;
	}

	private static int checkNbrGroupes(int nbrGroupes, ArrayList<Etudiant> etudiants){
		int tailleGroupes = (int)(etudiants.size()/nbrGroupes);
		if(tailleGroupes > Groupe.nbrMaxEtu)
			return 1;
		if(tailleGroupes < Groupe.nbrMinEtu)
			return 2;
		return 0;
	}
}
