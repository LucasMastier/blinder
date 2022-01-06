package blinderBackEnd.model;

public class Song {
    String auteur;
    String titre;

    public Song(String auteur, String titre){
        this.auteur = auteur;
        this.titre = titre;
    }
    public String getAuteur() {
        return auteur;
    }
    public String getTitre() {
        return titre;
    }
    public String toString(){
        return getTitre()+" "+getAuteur();
    }
}
