public abstract class Utilisateur{
    protected int idUtilisateur;
    protected String nom;
    protected String prenom;
    protected String passwordHash;
    protected String emailPro;
    protected boolean actif;
    
   public Utilisateur(int idUtilisateur, String nom, String prenom, String passwordHash, String emailPro){
        this.idUtilisateur = idUtilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.passwordHash = passwordHash;
        this.emailPro = emailPro;
        this.actif = false;
    }
}
