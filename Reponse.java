public class Reponse {

    public int idReponse;
    public String textReponse;
    public String typeReponse;
    public Sondage sondage;

    public Reponse(int id, String text, String type, Sondage sondage) {
        this.idReponse = id;
        this.textReponse = text;
        this.typeReponse = type;
        this.sondage = sondage;
    }
}
