<?php

public class Groupe extends Connection{
	public nom;
	private idEetudiants;

	public void addEtudiant(Etudiant etudiant) throws IndexOutOfBoundsException {  
		if(etudiants.size()<nbrMaxEtu){
			$this->etudiants.add(etudiant);
		}
		else
			throw new IndexOutOfBoundsException();
	}

	public etudiants(){ return $this->etudiants;}

	public void addEtudiants(etudiants){
		for(Etudiant e : etudiants)
			addEtudiant(e);
	}

	public Groupe(nom, etudiants){
		$this->nom = nom;
		$this->etudiants = etudiants;
		$this->moyenne = Moyenne();
		$this->mathsMoyenne = MathsMoyenne();
		$this->infoMoyenne = InfoMoyenne();
		$this->nbrFilles = NbrFilles();
		$this->score = (4/9)*$this->moyenne+(3/9)*$this->mathsMoyenne+(2/9)*$this->infoMoyenne;

	}

	private Score(){
		return (4/9)*$this->moyenne+(3/9)*$this->mathsMoyenne+(2/9)*$this->infoMoyenne;
	}

	private Moyenne(){
		moyenne = 0;
		for(Etudiant etudiant : etudiants)
			moyenne += etudiant.moyenne;
		moyenne /= etudiants.size();
		return moyenne;
	}

	public toString(){
		affichage = "Le groupe " + $this->nom + "sont score est de " + $this->score + ", il contient " + $this->etudiants.size() + " etudiants :\n";
		for(Etudiant e : $this->etudiants){
			affichage += e.toString() + ", ";
		}
		return affichage;
	}
}
?>
