<?php
public class Semestre {
	private idGroupes;
	private numSemestre;
	private nombreMaxCovoiturage;
	private estApprentissage;
	private idResponsable;

	public Semestre(groupes, numSemestre, nombreMaxCovoiturage, estApprentissage, Enseignant responsable){
		$this->groupes = groupes;
		$this->numSemestre = numSemestre;
		$this->nombreMaxCovoiturage = nombreMaxCovoiturage;
		$this->estApprentissage = estApprentissage;
		$this->responsable = responsable;
	}


	public Semestre(numSemestre, nombreMaxCovoiturage, estApprentissage){
		$this->groupes = null;
		$this->numSemestre = numSemestre;
		$this->nombreMaxCovoiturage = nombreMaxCovoiturage;
		$this->estApprentissage = estApprentissage;
	}

	public numSemestre(){
		return $this->numSemestre;
	}

	public numSemestre(numSemestre){
		if(numSemestre >= 0 && numSemestre <= 6){
			$this->numSemestre = numSemestre;
			return true;
		}
		else
			return false;
	}

	public nombreMaxCovoiturage(){
                return $this->numSemestre;
        }

        public nombreMaxCovoiturage(nombreMaxCovoiturage){
                if(nombreMaxCovoiturage >= 2 && nombreMaxCovoiturage <= 4){
                        $this->nombreMaxCovoiturage = nombreMaxCovoiturage;
                        return true;
                }
                else
                        return false;
        }

	public toString(){
		affichage = "Semestre n°" + $this->numSemestre;
		if($this->estApprentissage)
			affichage += " en apprentissage";
		else
			affichage += " en initial.";
		affichage += " " + $this->responsable.toString() + " autorise " + $this->nombreMaxCovoiturage + " etudiants a etre dans le meme groupe de covoiturage.";
		for(Groupe g : groupes)
			affichage += "\n\t" + g.toString();
		return affichage;
	}
}
?>
