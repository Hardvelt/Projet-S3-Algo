<?php
public class Reponse {

    public idReponse;
    public textReponse;
    public typeReponse;
    public idSondage;

    public Reponse(id, text, type, Sondage sondage) {
        $this->idReponse = id;
        $this->textReponse = text;
        $this->typeReponse = type;
        $this->sondage = sondage;
    }
}
?>
