<?php

public class Etudiant extends Utilisateur{
	private $idUtilisateur;
	public $nom;
	public $prenom;
	public $passwordHash;
	public $emailPro;
	public $emailPerso;
	public $actif;
	public $moyenne;
	public $infoMoyenne;
	public $mathsMoyenne;
	public $groupeCovoit;
	public $estRedoublant;
	public $estAlternant;
	public $estSecAnglo;
	public $estFille;
	public static 

	public Etudiant($idUtilisateur, $nom, $prenom, $passwordHash, $emailPro, $emailPerso, $moyenne, $infoMoyenne, $mathsMoyenne, $groupeCovoit, $estRedoublant, $estSecAnglo){
		super($idUtilisateur, $nom, $prenom, $passwordHash, $emailPro);
		$this->emailPerso = emailPerso;
		$this->moyenne = moyenne;
		$this->infoMoyenne = infoMoyenne;
		$this->mathsMoyenne = mathsMoyenne;
		$this->groupeCovoit = groupeCovoit;
		$this->estRedoublant = estRedoublant;
		$this->estSecAnglo = estSecAnglo;

	}
	public idEtudiant(){ return $this->idUtilisateur;}

	public __toString(){
		return $this->nom +" " + $this->prenom + " (" + $this->idUtilisateur + ")";
	}
}
?>
