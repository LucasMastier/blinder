package blinderBackEnd.model;

import javafx.scene.image.Image;
import javafx.scene.media.MediaPlayer;

import java.util.ArrayList;
import java.util.Collections;

public class PicturelistService {
    private static Picturelist currentPicturelist;
    private static Picture currentPicture;
    private static ArrayList<Integer> randomValues = new ArrayList<>();
    //public static MediaPlayer mediaPlayer;
    private static ArrayList<Picturelist> picturelists = new ArrayList<>();

    public static void printRandomPictureFromPicturelist(Picturelist picturelist){
        int randomNumber = (int)(Math.random() * ((picturelist.getPicturelist().size())));
        Picture randomPicture = picturelist.getPicturelist().get(randomNumber);
        currentPicture = randomPicture;
        printPictureFromPicturelist(randomPicture.getPathToFile());
    }
    public static Image printPictureFromPicturelist(String path){
        Image image = new Image("file:JavaFXModule/src/main/java/com/example/javafxmodule/images/icon_slack_hash_colored.png");
        return image;
    }
    public static void initializeRandomValues(){
        randomValues.clear();

        int picturelistSize = PicturelistService.getCurrentPicturelist().getPicturelist().size();
        for (int i = 0; i < picturelistSize; i++) {
            randomValues.add(i);
            //System.out.println(i);
        }
        Collections.shuffle(randomValues);

    }
    public static ArrayList<String> getRandomAuthorFromPicturelist(Picturelist picturelist){
        ArrayList<String> randomAuthors = new ArrayList<>();

        initializeRandomValues();

        for(int i = 0; i < 4; i++){
            randomAuthors.add(picturelist.getPicturelist().get(randomValues.get(i)).getAuteur());

        }
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
