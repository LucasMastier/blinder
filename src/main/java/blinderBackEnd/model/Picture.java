package blinderBackEnd.model;

public class Picture {
    String auteur;
    String pathToFile;

    public Picture(String auteur,String pathToFile){
        this.auteur = auteur;
        this.pathToFile = pathToFile;
    }

    public String getAuteur() {
        return auteur;
    }
    public String toString(){
        return getAuteur();
    }

    public String getPathToFile() {
        return pathToFile;
    }
}
