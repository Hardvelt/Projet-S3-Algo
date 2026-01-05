<?php

public class Sondage {
    public $idSondage;
    public $questionSondage;
    public $idReponses;
    public $idEnseignant;

    public Sondage ($idSondage, $questionSondage, $reponses, $idEnseignant) {
        $this->idSondage = $idSondage;
        $this->questionSondage = $questionSondage;
        $this->reponses = $reponses;
        $this->idEnseignant = $idEnseignant;
    }
}
?>
