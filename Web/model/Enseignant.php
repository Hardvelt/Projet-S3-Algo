<?php
public class Enseignant {
	private $idUtilisateur;
	public $nom;
	public $prenom;
	private $passwordHash;
	public $emailPro;
	public $actif;
	public $idMatieres;

	public Enseignant(idUtilisateur, nom, prenom, passwordHash, emailPro, matieres) {
		$this->idUtilisateur = idUtilisateur;
		$this->nom = nom;
		$this->prenom = prenom;
		$this->passwordHash = passwordHash;
		$this->emailPro = emailPro;
		$this->actif = false;
		$this->matieres = matieres;
	}

}
?>
