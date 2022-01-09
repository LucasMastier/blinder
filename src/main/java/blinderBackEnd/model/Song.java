package blinderBackEnd.model;

import java.io.Serializable;

public class Song implements Serializable {
    String auteur;
    String titre;
    String pathToFile;

    public Song(String auteur, String titre,String pathToFile){
        this.auteur = auteur;
        this.titre = titre;
        this.pathToFile = pathToFile;
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

    public String getPathToFile() {
        return pathToFile;
    }
}
