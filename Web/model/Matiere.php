<?php

public class Matiere {
    public idMatiere;
    public nom;
    public ue;
    public coefficient;
    public idEnseignantResponsable;
    public idEnseignants;

    public Matiere (idMatiere, nom, ue, coefficient, Enseignant enseignantResponsable, enseignants) {
        $this->idMatiere = idMatiere;
        $this->nom = nom;
        $this->ue = ue;
        $this->coefficient = coefficient;
        $this->enseignantResponsable = enseignantResponsable;
    }
}
?>
