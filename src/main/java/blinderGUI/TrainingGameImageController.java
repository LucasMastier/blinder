package blinderGUI;

import blinderBackEnd.model.*;
import blinderBackEnd.model.PlaylistService;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static blinderBackEnd.model.PicturelistService.getRandomAuthorFromPicturelist;
import static blinderBackEnd.model.PlaylistService.getRandomAuthorFromPlaylist;
import static blinderGUI.Main.switchTo;

public class TrainingGameImageController {

    @FXML
    private TilePane buttonsContainer;

    @FXML
    private Label answerLabel;

    @FXML
    ImageView imageView;

    @FXML
    private Button nextButton;

    private boolean nextButtonIsVisible = false;

    private List<Button> buttonList = new ArrayList<>();


    @FXML
    public void initialize() throws MalformedURLException {

        int compt = 0;
        if(GameService.getRound_cpt()==0){
            PicturelistService.initializeRandomValues();
        }
        ArrayList<String> author = getRandomAuthorFromPicturelist(PicturelistService.getCurrentPicturelist());
        while(compt != 4){

            Button button = new Button(author.get(compt));
            button.setOnAction(new EventHandler() {

                @Override
                public void handle(Event event) {
                    if(PicturelistService.checkAuthor(button.getText()) && !nextButtonIsVisible) {
                        button.setStyle("-fx-background-color: green; ");
                        answerLabel.setText("Bonne réponse !");
                        GameService.setScore_cpt(GameService.getScore_cpt() + 1);
                        GameService.setRound_cpt(GameService.getRound_cpt() + 1);

                    }
                    if(!PicturelistService.checkAuthor(button.getText()) && !nextButtonIsVisible){
                        answerLabel.setText("Mauvaise réponse");
                        button.setStyle("-fx-background-color: red; ");
                        GameService.setRound_cpt(GameService.getRound_cpt()+1);
                    }
                    nextButton.setVisible(true);
                    nextButtonIsVisible = true;
                }

            });
            buttonList.add(button);

            compt++;
        }
        buttonsContainer.getChildren().clear();
        buttonsContainer.getChildren().addAll(buttonList);
        PicturelistService.printRandomPictureFromPicturelist(PicturelistService.getCurrentPicturelist());

        Image image = new Image(PicturelistService.getCurrentPicture().getPathToFile());

        imageView.setImage(image);

        boolean goodAnswerIsAvailable = false;
        for(int i = 0; i < buttonList.size(); i++){
            if(buttonList.get(i).getText().equals(PicturelistService.getCurrentPicture().getAuteur())){
                goodAnswerIsAvailable = true;
            }
        }
        if(!goodAnswerIsAvailable){
            int random = (int) (Math.random() * buttonList.size());
            buttonList.get(random).setText(PicturelistService.getCurrentPicture().getAuteur());
        }

    }

    public void printPicture(ActionEvent event){
        //PicturelistService.printPictureFromPicturelist();
    }

    public void nextRound(ActionEvent event) throws IOException {
        if(GameService.getRound_cpt()==5){
            switchTo(event, "TrainingScore.fxml");
            GameService.setRound_cpt(0);
        } else {
            switchTo(event,"TrainingGameImage.fxml");
        }

    }

}
