<?php
public abstract class Utilisateur{
    protected idUtilisateur;
    protected nom;
    protected prenom;
    protected passwordHash;
    protected emailPro;
    protected actif;
    
   public Utilisateur(idUtilisateur, nom, prenom, passwordHash, emailPro){
        $this->idUtilisateur = idUtilisateur;
        $this->nom = nom;
        $this->prenom = prenom;
        $this->passwordHash = passwordHash;
        $this->emailPro = emailPro;
        $this->actif = false;
    }
}
?>
