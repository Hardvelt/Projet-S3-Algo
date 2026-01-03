import java.util.ArrayList;
public class Matiere {
    public int idMatiere;
    public String nom;
    public int ue;
    public float coefficient;
    public Enseignant enseignantResponsable;
    public ArrayList<Enseignant> enseignants;

    public Matiere (int idMatiere, String nom, int ue, float coefficient, Enseignant enseignantResponsable, ArrayList<Enseignant> enseignants) {
        this.idMatiere = idMatiere;
        this.nom = nom;
        this.ue = ue;
        this.coefficient = coefficient;
        this.enseignantResponsable = enseignantResponsable;
    }
}
