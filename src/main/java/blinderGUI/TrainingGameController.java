package blinderGUI;

import blinderBackEnd.model.GameService;
import blinderBackEnd.model.Playlist;
import blinderBackEnd.model.PlaylistService;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static blinderBackEnd.model.PlaylistService.getRandomAuthorFromPlaylist;
import static blinderGUI.Main.switchTo;

public class TrainingGameController {

    @FXML
    private TilePane buttonsContainer;

    @FXML
    private Label answerLabel;

    @FXML
    private Button nextButton;

    private boolean nextButtonIsVisible = false;

    private List<Button> buttonList = new ArrayList<>();

    @FXML
    public void initialize(){
        int compt = 0;
        if(GameService.getRound_cpt()==0){
            PlaylistService.initializeRandomValues();
        }
        ArrayList<String> author = getRandomAuthorFromPlaylist(PlaylistService.getCurrentPlaylist());
        while(compt != 4){

            Button button = new Button(author.get(compt));
            button.setOnAction(new EventHandler() {

                @Override
                public void handle(Event event) {
                    if(PlaylistService.checkAuthor(button.getText()) && !nextButtonIsVisible) {
                        button.setStyle("-fx-background-color: green; ");
                        answerLabel.setText("Bonne réponse !");
                        GameService.setScore_cpt(GameService.getScore_cpt() + 1);
                        GameService.setRound_cpt(GameService.getRound_cpt() + 1);

                    }
                    if(!PlaylistService.checkAuthor(button.getText()) && !nextButtonIsVisible){
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
        PlaylistService.playRandomSongFromPlaylist(PlaylistService.getCurrentPlaylist());
        boolean goodAnswerIsAvailable = false;
        for(int i = 0; i < buttonList.size(); i++){
            if(buttonList.get(i).getText().equals(PlaylistService.getCurrentSong().getAuteur())){
                goodAnswerIsAvailable = true;
            }
        }
        if(!goodAnswerIsAvailable){
            int random = (int) (Math.random() * buttonList.size());
            buttonList.get(random).setText(PlaylistService.getCurrentSong().getAuteur());
        }

    }

    public void playSong(ActionEvent event){
        //PlaylistService.playSongFromPlaylist();
    }

    public void nextRound(ActionEvent event) throws IOException {
        if(GameService.getRound_cpt()==5){
            PlaylistService.mediaPlayer.stop();
            switchTo(event, "TrainingScore.fxml");
            GameService.setRound_cpt(0);
        } else {
            PlaylistService.mediaPlayer.stop();
            switchTo(event,"TrainingGame.fxml");
        }

    }
    
}
