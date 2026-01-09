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

		ArrayList<Groupe> groupes = new ArrayList<Groupe>();
		for(int i = 0; i < nbrGroupes; i++){
			String nomGroupe = Integer.toString(semestre.numSemestre()) + (char)(i+65);//transforme 0, 1, 2... en A, B, C...
			groupes.add(new Groupe(nomGroupe, new ArrayList<Etudiant>()));
		}

		semestre.groupes = groupes;

		for(int i = 0; groupesCovoit.size() > 0; i++){
			boolean contientAnglo = false;
			for(Etudiant e : groupesCovoit.get(0)){
				contientAnglo = e.estSecAnglo || e.estFille; 
				if(contientAnglo)
					break;
			}
			if(contientAnglo){
				int plein = groupes.get(i%2).etudiants().size() + groupesCovoit.get(0).size() > tailleGroupe ? 1 : 0;
				if(plein == 1){
					if(groupes.get((i+1)%2).etudiants().size() + groupesCovoit.get(0).size() > tailleGroupe)
						throw new IllegalArgumentException("Les contraintes de covoiturage dans les groupes anglo n'ont pas pus etre respecter");
				}
				groupes.get((i+plein)%2).addEtudiants(groupesCovoit.get(0));
			}
			else{
				int plein = groupes.get((i%(nbrGroupes-2))+2).etudiants().size() + groupesCovoit.get(0).size() > tailleGroupe ? 1 : 0;
				boolean trouver = false;
				if(plein == 1){
					for(int j = 0; j<nbrGroupes; j++){
						if(groupes.get(j).etudiants().size() + groupesCovoit.get(0).size() <=tailleGroupe){
							groupes.get(j).addEtudiants(groupesCovoit.get(0));
							trouver = true;
							break;
						}
					}
					if(!trouver)
						throw new IllegalArgumentException("Les contraintes dans les groupes anglo n'ont pas pus etre respecter");
				}
				else
					groupes.get((i%(nbrGroupes-2))+2).addEtudiants(groupesCovoit.get(0));
			}
			groupesCovoit.remove(0);
		}
		while(etudiants.size() > 0){
			semestre.BestScore(etudiants.get(0), etudiantsCpy, tailleGroupe).addEtudiant(etudiants.get(0));
			etudiants.remove(0);
		}
	}

	private static ArrayList<Etudiant> Etudiants(Semestre semestre){
		ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>();
		for(Groupe g : semestre.groupes){
			etudiants.addAll(g.etudiants());
		}
		return etudiants;
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

	private static void merge(ArrayList<Etudiant> arr, int left, int mid, int right) {

		int n1 = mid - left + 1;
		int n2 = right - mid;

		// Listes temporaires
		ArrayList<Etudiant> L = new ArrayList<>(n1);
		ArrayList<Etudiant> R = new ArrayList<>(n2);

		// Copier les donnes dans les listes temporaires
		for (int i = 0; i < n1; i++)
			L.add(arr.get(left + i));
		for (int j = 0; j < n2; j++)
			R.add(arr.get(mid + 1 + j));

		int i = 0, j = 0;
		int k = left;

		// Fusion
		while (i < n1 && j < n2) {
			if (L.get(i).moyenne <= R.get(j).moyenne) {
				arr.set(k, L.get(i));
				i++;
			} else {
				arr.set(k, R.get(j));
				j++;
			}
			k++;
		}

		while (i < n1) {
			arr.set(k, L.get(i));
			i++;
			k++;
		}

		while (j < n2) {
			arr.set(k, R.get(j));
			j++;
			k++;
		}
	}

	private static void triEtudiantMoyenne(ArrayList<Etudiant> arr, int left, int right) {
		if (left >= right)
			return;

		int mid = left + (right - left) / 2;

		triEtudiantMoyenne(arr, left, mid);
		triEtudiantMoyenne(arr, mid + 1, right);

		merge(arr, left, mid, right);
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
