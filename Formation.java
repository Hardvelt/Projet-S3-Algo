import java.util.ArrayList;
import java.util.Scanner;

public class Formation{
	public ArrayList<Groupe> groupes;
	public ArrayList<Etudiant> etudiants;

	public Formation(ArrayList<Groupe> groupes, ArrayList<Etudiant> etudiants){
		this.groupes = groupes;
		this.etudiants = etudiants;
	}

	public int CreerGroupes (int nbrGroupes){
		if(!ValideCreationGroupes(nbrGroupes))
			return 0;
		
		ArrayList<ArrayList<Etudiant>> groupesCovoit = this.groupesCovoiturage();
		ArrayList<Groupe> GroupeAnglo = new ArrayList<Groupe>();
		for(ArrayList<Etudiant> etudiants){
			for(Etudiant e : etudiants){
				if(e.estSecAnglo)
					

	}

	private ArrayList<ArrayList<Etudiant>> groupesCovoiturage(){
		ArrayList<ArrayList<Etudiant>> groupesCovoit = new ArrayList<ArrayList<Etudiant>>();
		for(Etudiant e : this.etudiants){
			if(e.groupeCovoit.isNull())
				continue;
			groupesCovoit.get(e.groupeCovoit).add(e);
		}
		return groupesCovoit;
	}

	public static void merge(ArrayList<Etudiant> arr, int left, int mid, int right) {

		int n1 = mid - left + 1;
		int n2 = right - mid;

		// Listes temporaires
		ArrayList<Etudiant> L = new ArrayList<>(n1);
		ArrayList<Etudiant> R = new ArrayList<>(n2);

		// Copier les données dans les listes temporaires
		for (int i = 0; i < n1; i++)
			L.add(arr.get(left + i));
		for (int j = 0; j < n2; j++)
			R.add(arr.get(mid + 1 + j));

		int i = 0, j = 0;
		int k = left;

		// Fusion
		while (i < n1 && j < n2) {
			if (L.get(i).moyenne() <= R.get(j).moyenne()) {
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

	public static void triEtudiantMoyenne(ArrayList<Etudiant> arr, int left, int right) {
		if (left >= right)
			return;

		int mid = left + (right - left) / 2;

		triEtudiantMoyenne(arr, left, mid);
		triEtudiantMoyenne(arr, mid + 1, right);

		merge(arr, left, mid, right);
	}

	private boolean ValideCreationGroupes (int nbrGroupes){
		int check = checkNbrGroupes(nbrGroupes);
		if(check == 1){
			System.out.println("Veuillez choisir un nombre de groupes plus élevé.");
			return false;
		}
		if(check == 2){
			Scanner sc = new Scanner(System.in);
			System.out.println("La taille moyenne des groupes sera inférieur a " + (int)etudiants.size()%nbrGroupes + " ce qui est en dessous de la taille minimale (" + Groupe.nbrMinEtu + ").\n Voulez vous continuez quand meme ?Y/n");
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

	private int checkNbrGroupes(int nbrGroupes){
		float tailleGroupes = etudiants.size()%nbrGroupes;
		if(tailleGroupes > Groupe.nbrMaxEtu)
			return 1;
		if(tailleGroupes < Groupe.nbrMinEtu)
			return 2;
		return 0;
	}

	public static void main(String[] args){
		Etudiant e1  = new Etudiant(14, 15, 13, 0, false, true);
		Etudiant e2  = new Etudiant(12, 11, 13, 0, false, false);
		Etudiant e3  = new Etudiant(10, 10, 9, 0, false, false);
		Etudiant e4  = new Etudiant(16, 17, 15, 0, false, true);
		Etudiant e5  = new Etudiant(11, 12, 10, 0, false, false);
		Etudiant e6  = new Etudiant(13, 14, 12, 0, false, true);
		Etudiant e7  = new Etudiant(9, 8, 10, 0, true, false);
		Etudiant e8  = new Etudiant(15, 15, 16, 0, false, true);
		Etudiant e9  = new Etudiant(10, 9, 11, 0, false, false);
		Etudiant e10 = new Etudiant(17, 18, 16, 0, false, true);

		Etudiant e11 = new Etudiant(12, 13, 11, 0, false, false);
		Etudiant e12 = new Etudiant(14, 13, 15, 0, false, true);
		Etudiant e13 = new Etudiant(9, 8, 10, 0, true, false);
		Etudiant e14 = new Etudiant(16, 16, 17, 0, false, true);
		Etudiant e15 = new Etudiant(10, 11, 9, 0, false, false);
		Etudiant e16 = new Etudiant(13, 12, 14, 0, false, true);
		Etudiant e17 = new Etudiant(11, 10, 12, 0, false, false);
		Etudiant e18 = new Etudiant(15, 14, 16, 0, false, true);
		Etudiant e19 = new Etudiant(12, 11, 13, 0, false, false);
		Etudiant e20 = new Etudiant(14, 15, 13, 0, false, true);

		Etudiant e21 = new Etudiant(8, 9, 7, 0, true, false);
		Etudiant e22 = new Etudiant(13, 14, 12, 0, false, true);
		Etudiant e23 = new Etudiant(15, 16, 15, 0, false, true);
		Etudiant e24 = new Etudiant(11, 10, 12, 0, false, false);
		Etudiant e25 = new Etudiant(14, 15, 13, 0, false, true);
		Etudiant e26 = new Etudiant(9, 9, 8, 0, true, false);
		Etudiant e27 = new Etudiant(16, 17, 15, 0, false, true);
		Etudiant e28 = new Etudiant(12, 11, 13, 0, false, false);
		Etudiant e29 = new Etudiant(10, 10, 10, 0, false, false);
		Etudiant e30 = new Etudiant(17, 16, 18, 0, false, true);

		Etudiant e31 = new Etudiant(13, 12, 14, 1, false, false);
		Etudiant e32 = new Etudiant(15, 16, 14, 1, false, true);
		Etudiant e33 = new Etudiant(12, 13, 11, 1, false, false);
		Etudiant e34 = new Etudiant(16, 17, 16, 1, false, true);

		Etudiant e35 = new Etudiant(11, 12, 10, 2, false, false);
		Etudiant e36 = new Etudiant(14, 13, 15, 2, false, true);
		Etudiant e37 = new Etudiant(10, 9, 11, 2, false, false);

		Etudiant e38 = new Etudiant(15, 15, 16, 3, false, true);
		Etudiant e39 = new Etudiant(12, 11, 13, 3, false, false);

		Etudiant e40 = new Etudiant(13, 14, 12, 4, false, true);
		Etudiant e41 = new Etudiant(11, 10, 12, 4, false, false);

		Etudiant e42 = new Etudiant(16, 17, 15, 5, false, true);
		Etudiant e43 = new Etudiant(10, 11, 9, 5, false, false);

		Etudiant e44 = new Etudiant(14, 15, 13, 6, false, true);
		Etudiant e45 = new Etudiant(9, 8, 10, 6, true, false);

		Etudiant e46 = new Etudiant(13, 12, 14, 0, false, true);
		Etudiant e47 = new Etudiant(11, 12, 10, 0, false, false);
		Etudiant e48 = new Etudiant(15, 14, 16, 0, false, true);
		Etudiant e49 = new Etudiant(12, 13, 11, 0, false, false);
		Etudiant e50 = new Etudiant(17, 18, 16, 0, false, true);
		Etudiant e51 = new Etudiant(13, 14, 12, 0, false, false);
		Etudiant e52 = new Etudiant(11, 10, 12, 0, false, false);
		Etudiant e53 = new Etudiant(15, 15, 16, 0, false, false);
		Etudiant e54 = new Etudiant(12, 11, 13, 0, false, false);
		Etudiant e55 = new Etudiant(14, 15, 13, 0, false, false);
		Etudiant e56 = new Etudiant(10, 9, 11, 0, true, false);
		Etudiant e57 = new Etudiant(16, 17, 15, 0, false, false);
		Etudiant e58 = new Etudiant(9, 8, 10, 0, true, false);
		Etudiant e59 = new Etudiant(17, 18, 16, 0, false, false);
		Etudiant e60 = new Etudiant(12, 12, 11, 0, false, false);

		Etudiant e61 = new Etudiant(14, 14, 15, 0, false, false);
		Etudiant e62 = new Etudiant(10, 10, 9, 0, false, false);
		Etudiant e63 = new Etudiant(13, 12, 14, 0, false, false);
		Etudiant e64 = new Etudiant(11, 12, 10, 0, false, false);
		Etudiant e65 = new Etudiant(15, 16, 14, 0, false, false);
		Etudiant e66 = new Etudiant(12, 11, 13, 0, false, false);
		Etudiant e67 = new Etudiant(9, 9, 8, 0, true, false);
		Etudiant e68 = new Etudiant(16, 17, 16, 0, false, false);
		Etudiant e69 = new Etudiant(10, 11, 9, 0, false, false);
		Etudiant e70 = new Etudiant(14, 13, 15, 0, false, false);

		Etudiant e71 = new Etudiant(15, 14, 16, 0, false, false);
		Etudiant e72 = new Etudiant(11, 10, 12, 0, false, false);
		Etudiant e73 = new Etudiant(12, 13, 11, 0, false, false);
		Etudiant e74 = new Etudiant(13, 12, 14, 0, false, false);
		Etudiant e75 = new Etudiant(10, 9, 11, 0, false, false);
		Etudiant e76 = new Etudiant(16, 16, 17, 0, false, false);
		Etudiant e77 = new Etudiant(13, 14, 12, 0, false, false);
		Etudiant e78 = new Etudiant(15, 15, 16, 0, false, true); 
		Etudiant e79 = new Etudiant(11, 12, 10, 0, false, false);
		Etudiant e80 = new Etudiant(17, 18, 16, 0, false, false);

		Etudiant e81 = new Etudiant(9, 8, 10, 0, true, false);
		Etudiant e82 = new Etudiant(12, 12, 13, 0, false, false);
		Etudiant e83 = new Etudiant(14, 15, 13, 0, false, false);
		Etudiant e84 = new Etudiant(11, 10, 12, 0, false, false);
		Etudiant e85 = new Etudiant(13, 14, 12, 0, false, false);
		Etudiant e86 = new Etudiant(10, 10, 9, 0, false, false);
		Etudiant e87 = new Etudiant(15, 16, 15, 0, false, false);
		Etudiant e88 = new Etudiant(12, 13, 11, 0, false, false);
		Etudiant e89 = new Etudiant(16, 17, 15, 0, false, false);
		Etudiant e90 = new Etudiant(14, 13, 15, 0, false, false);

		Etudiant e91 = new Etudiant(11, 10, 12, 0, false, false);
		Etudiant e92 = new Etudiant(12, 12, 13, 0, false, false);
		Etudiant e93 = new Etudiant(13, 14, 12, 0, false, false);
		Etudiant e94 = new Etudiant(10, 9, 11, 0, false, false);
		Etudiant e95 = new Etudiant(16, 17, 16, 0, false, true);
		Etudiant e96 = new Etudiant(11, 12, 10, 0, false, false);
		Etudiant e97 = new Etudiant(14, 14, 15, 0, false, false);
		Etudiant e98 = new Etudiant(13, 12, 14, 0, false, false);
		Etudiant e99 = new Etudiant(17, 16, 18, 0, false, false);
		Etudiant e100 = new Etudiant(12, 11, 13, 0, false, false);

		Etudiant e101 = new Etudiant(13, 12, 14, 1, false, false);
		Etudiant e102 = new Etudiant(14, 15, 13, 1, false, false);
		Etudiant e103 = new Etudiant(12, 11, 13, 1, false, false);
		Etudiant e104 = new Etudiant(15, 14, 16, 1, false, false);

		Etudiant e105 = new Etudiant(11, 12, 10, 2, false, false);
		Etudiant e106 = new Etudiant(14, 13, 15, 2, false, false);
		Etudiant e107 = new Etudiant(10, 9, 11, 2, false, false);

		Etudiant e108 = new Etudiant(16, 16, 17, 3, false, false);
		Etudiant e109 = new Etudiant(12, 13, 11, 3, false, false);

		Etudiant e110 = new Etudiant(15, 15, 16, 4, false, false);
		Etudiant e111 = new Etudiant(11, 10, 12, 4, false, false);

		Etudiant e112 = new Etudiant(13, 12, 14, 5, false, false);
		Etudiant e113 = new Etudiant(9, 8, 10, 5, true, false); 

		Etudiant e114 = new Etudiant(14, 13, 15, 6, false, false);
		Etudiant e115 = new Etudiant(12, 12, 13, 6, false, false);

		Etudiant e116 = new Etudiant(10, 10, 9, 7, false, false);
		Etudiant e117 = new Etudiant(15, 16, 14, 7, false, true);

		Etudiant e118 = new Etudiant(13, 14, 12, 0, false, false);
		Etudiant e119 = new Etudiant(11, 12, 10, 0, false, false);
		Etudiant e120 = new Etudiant(16, 17, 15, 0, false, false);
	}
}

