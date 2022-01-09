package blinderBackEnd.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Picturelist implements Serializable {
    private String titre;
    private List<Picture> picturelist = new ArrayList<>();

    public Picturelist(String titre, List<Picture> picturelist){
        this.titre = titre;
        this.picturelist = picturelist;
    }
    public Picturelist(String titre){
        this.titre = titre;
    }

    public void addPicturelisyToPicturelists() {
        PicturelistService.addToPicturelists(this);
    }


    public String getTitre() {
        return titre;
    }
    public void addToPicturelist(Picture picture){
        picturelist.add(picture);
    }
    public void removetoPicturelist(Picture picture){
        picturelist.remove(picture);
    }
    public List<Picture> getPicturelist(){
        return picturelist;
    }

    public String toString(){
        String res="";
        for (Picture p: picturelist) {
            res+=p.toString()+"\n";
        }
        return res;
    }
}
