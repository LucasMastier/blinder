package blinderBackEnd.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;

public class PicturelistService {
    private static Picturelist currentPicturelist;
    private static Picture currentPicture;
    private static ArrayList<Integer> randomValues = new ArrayList<>();
    private static ArrayList<Picturelist> picturelists = new ArrayList<>();

    public static void printRandomPictureFromPicturelist(Picturelist picturelist) throws MalformedURLException {
        int i = randomValues.get(GameService.getRound_cpt());
        Picture randomPicture = picturelist.getPicturelist().get(i);
        currentPicture = randomPicture;
        //printPictureFromPicturelist(randomPicture.getPathToFile());
    }

    public static void addToPicturelists(Picturelist picturelist){
        picturelists.add(picturelist);
    }

    /*public static Image printPictureFromPicturelist(String path) throws MalformedURLException {
        Image image = new Image(new File(path).toURI().toURL().toString());
        return image;
    }*/

    public static void initializeRandomValues(){
        randomValues.clear();

        for (int i = 0; i < currentPicturelist.getPicturelist().size(); i++) {
            randomValues.add(i);
        }
        Collections.shuffle(randomValues);

    }

    public static ArrayList<String> getRandomAuthorFromPicturelist(Picturelist picturelist){
        ArrayList<String> randomAuthors = new ArrayList<>();

        for(int i = 0; i < currentPicturelist.getPicturelist().size(); i++){
            randomAuthors.add(picturelist.getPicturelist().get(i).getAuteur());

        }
        Collections.shuffle(randomAuthors);
        return randomAuthors;
    }

    public static void setCurrentPicturelist(Picturelist picturelist){
        currentPicturelist = picturelist;
    }

    public static Picturelist getCurrentPicturelist(){
        return currentPicturelist;
    }

    public static ArrayList<Picturelist> getPicturelists(){
        return picturelists;
    }

    public static void setCurrentPicture(Picture picture){
        currentPicture = picture;
    }

    public static Picture getCurrentPicture(){
        return currentPicture;
    }

    public static boolean checkAuthor(String author){
        if(author.equals(currentPicture.getAuteur())){
            return true;
        } else {
            return false;
        }
    }




}
