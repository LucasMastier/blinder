package blinderGUI;

import blinderBackEnd.model.Playlist;
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
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static blinderBackEnd.model.PlaylistService.getRandomAuthorFromPlaylist;

public class TrainingGameController {

    @FXML
    private TilePane buttonsContainer;

    @FXML
    private Label answerLabel;

    private List<Button> buttonList = new ArrayList<>();

    public void initialize(){
        int compt = 0;
        while(compt != 4){

            String author = getRandomAuthorFromPlaylist(PlaylistService.getCurrentPlaylist());
            Button button = new Button(author);
            button.setOnAction(new EventHandler() {

                @Override
                public void handle(Event event) {
                    if(PlaylistService.checkAuthor(author)){
                        answerLabel.setText("Bonne réponse !");
                    } else {
                        answerLabel.setText("Mauvaise réponse");
                    }
                }

            });
            buttonList.add(button);
            compt++;
        }
        buttonsContainer.getChildren().clear();
        buttonsContainer.getChildren().addAll(buttonList);
        PlaylistService.playRandomSongFromPlaylist(PlaylistService.getCurrentPlaylist());
    }

    public void playSong(ActionEvent event){
        //PlaylistService.playSongFromPlaylist();
    }
    
}
