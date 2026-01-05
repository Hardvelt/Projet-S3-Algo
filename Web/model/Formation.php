<?php
public class Formation extends Connection{
	public $nom;
	public $Enseignant directeur;
	public $idSemestres;
	public $idEtudiants;

	public Formation($nom, Enseignant directeur, etudiants){
		$this->nom = nom;
		$this->directeur = directeur;
		$this->semestres = semestres;
		$this->etudiants = etudiants;
	}
}
?>
