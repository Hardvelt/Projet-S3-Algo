import java.util.ArrayList;
public class Sondage {
    public int idSondage;
    public String questionSondage;
    public ArrayList<Reponse> reponses;

    public Sondage (int idSondage, String questionSondage, ArrayList<Reponse> reponses) {
        this.idSondage = idSondage;
        this.questionSondage = questionSondage;
        this.reponses = reponses;
    }
}
