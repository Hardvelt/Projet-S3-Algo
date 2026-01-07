import java.util.ArrayList;
import java.util.Scanner;
public class CreationGroupesControleur{

	public static void CreerGroupesGlouton (int nbrGroupes, Semestre semestre, ArrayList<Etudiant> etudiantsCpy) throws IllegalArgumentException{

		ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>(etudiantsCpy);

		int tailleGroupe = (int)(etudiants.size()/nbrGroupes);

		if(!ValideCreationGroupes(nbrGroupes, etudiants))
			throw new IllegalArgumentException("La quantite de groupes devant etre creer n'est pas valide");

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
			semestre.GroupeScoreMinimal(etudiantsCpy, tailleGroupe, false, groupesCovoit.get(i)).addEtudiants(groupesCovoit.get(i));
		}
		for(int i = 0; i < etudiantsAnglo.size(); i++){
			semestre.GroupeScoreMinimal(etudiantsCpy, tailleGroupe, false, etudiantsAnglo.get(i)).addEtudiant(etudiantsAnglo.get(i));
		}
		for(int i = 0; i < etudiants.size(); i++){
			semestre.GroupeScoreMinimal(etudiantsCpy, tailleGroupe, true, etudiants.get(i)).addEtudiant(etudiants.get(i));
		}
	}

	public static void CreerGroupesGloutonContraintesAllegees (int nbrGroupes, int nbrGroupesFilles, Semestre semestre, ArrayList<Etudiant> etudiantsCpy) throws IllegalArgumentException{

		ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>(etudiantsCpy);

		int tailleGroupe = (int)(etudiants.size()/nbrGroupes);

		if(!ValideCreationGroupes(nbrGroupes, etudiants))
			throw new IllegalArgumentException("La quantite de groupes devant etre creer n'est pas valide");

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
			semestre.GroupeScoreMinimal(etudiantsCpy, tailleGroupe, false, groupesCovoit.get(i)).addEtudiants(groupesCovoit.get(i));
		}
		for(int i = 0; i < etudiantsAnglo.size(); i++){
			semestre.GroupeScoreMinimal(etudiantsCpy, tailleGroupe, nbrGroupesFilles, false, etudiantsAnglo.get(i)).addEtudiant(etudiantsAnglo.get(i));
		}
		for(int i = 0; i < etudiants.size(); i++){
			semestre.GroupeScoreMinimal(etudiantsCpy, tailleGroupe, true, etudiants.get(i)).addEtudiant(etudiants.get(i));
		}
	}
	
	public ArrayList<Groupe> bruteForceGroupes(ArrayList<Etudiant> etudiants, int nbrGroupes, Semestre semestre) {
		if(!ValideCreationGroupes(nbrGroupes, etudiants))
			throw new IllegalArgumentException("La quantite de groupes devant etre creer n'est pas valide");

		int tailleGroupe = etudiants.size() / nbrGroupes+1;

		ArrayList<ArrayList<ArrayList<Etudiant>>> toutesPartitions = generatePartitions(etudiants, nbrGroupes);

		ArrayList<ArrayList<Etudiant>> meilleurePartition = null;
		int meilleurScore = Integer.MAX_VALUE;

		for (ArrayList<ArrayList<Etudiant>> partition : toutesPartitions) {
			int score = 0;
			boolean groupeTropPetit = false;
			boolean respectCovoiturage = true;
			for (ArrayList<Etudiant> groupeEtudiants : partition) {
				groupeTropPetit = groupeEtudiants.size() < tailleGroupe-2 ? true : false;
				if(groupeTropPetit)
					break;
				
				int nbrFilles = 0;
				for(Etudiant e : groupeEtudiants)
					nbrFilles += e.estFille ? 1 : 0;
				if(nbrFilles > 0 && nbrFilles < 4)
					break;
				
				respectCovoiturage = RespectCovoiturage(partition, groupeEtudiants);
				if(!respectCovoiturage)
					break;
					score += semestre.ScoreMinimal(etudiants, tailleGroupe, nbrGroupes, groupeEtudiants);
			}
			if(groupeTropPetit)
				continue;

			if (score < meilleurScore) {
				meilleurScore = score;
				meilleurePartition = partition;
			}
		}

		ArrayList<Groupe> groupes = new ArrayList<>();
		for (int i = 0; i < nbrGroupes; i++) {
			String nomGroupe = Integer.toString(semestre.numSemestre()) + (char)(i + 65); // A, B, C...
			groupes.add(new Groupe(nomGroupe, meilleurePartition.get(i)));
		}

		semestre.groupes = groupes;
		return groupes;
	}

	public boolean RespectCovoiturage(ArrayList<ArrayList<Etudiant>> groupes, ArrayList<Etudiant> groupe) {

		for (int i = 0; i < groupe.size(); i++) {//pour chaque étudiant regarde dans chaque groupe si il y a un etudiant avec le meme indice de groupe
			if(groupe.get(i).groupeCovoit == 0)
				continue;
			for(int j = 0; j < groupes.size(); j++){
				if(groupes.get(i) == groupe)
					continue;
				for(int x = 0; x < groupes.get(j).size(); x++){
					if(groupe.get(i).groupeCovoit == groupes.get(j).get(x).groupeCovoit)
						return false;
				}
			}
		}
		return true;
	}

	private ArrayList<ArrayList<ArrayList<Etudiant>>> generatePartitions(ArrayList<Etudiant> etudiants, int nbrGroupes) {
		ArrayList<ArrayList<ArrayList<Etudiant>>> result = new ArrayList<>();

		if (etudiants.isEmpty()) {
			ArrayList<ArrayList<Etudiant>> emptyPartition = new ArrayList<>();
			for (int i = 0; i < nbrGroupes; i++) emptyPartition.add(new ArrayList<>());
			result.add(emptyPartition);
			return result;
		}

		Etudiant first = etudiants.get(0);
		ArrayList<Etudiant> rest = new ArrayList<>(etudiants);
		rest.remove(0);

		ArrayList<ArrayList<ArrayList<Etudiant>>> subPartitions = generatePartitions(rest, nbrGroupes);

		for (ArrayList<ArrayList<Etudiant>> subPartition : subPartitions) {
			for (int i = 0; i < nbrGroupes; i++) {
				ArrayList<ArrayList<Etudiant>> newPartition = new ArrayList<>();
				for (int j = 0; j < nbrGroupes; j++) {
					newPartition.add(new ArrayList<>(subPartition.get(j)));
				}
				newPartition.get(i).add(first);
				result.add(newPartition);
			}
		}
		return result;	
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
