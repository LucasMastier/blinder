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
import java.util.concurrent.TimeUnit;

import static blinderBackEnd.model.PlaylistService.getRandomAuthorFromPlaylist;

public class TrainingGameController {

    @FXML
    private TilePane buttonsContainer;

    @FXML
    private Label answerLabel;

    private List<Button> buttonList = new ArrayList<>();

    @FXML
    public void initialize(){
        int compt = 0;
        int myscore=0;
        int length_playlist = PlaylistService.getCurrentPlaylist().getPlaylist().size();
        String score=myscore+"/"+length_playlist;
        ArrayList<String> author = getRandomAuthorFromPlaylist(PlaylistService.getCurrentPlaylist());
        while(compt != length_playlist){

            Button button = new Button(author.get(compt));
            button.setOnAction(new EventHandler() {

                @Override
                public void handle(Event event) {
                    if(PlaylistService.checkAuthor(button.getText())){
                        answerLabel.setText("Bonne réponse !");

                        try {
                            TimeUnit.SECONDS.sleep(3);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Stage stage = (Stage) button.getScene().getWindow();
                        stage.close();
                        PlaylistService.mediaPlayer.stop();

                        FXMLLoader fxmlLoader = new FXMLLoader(MainMenuController.class.getResource("TrainingGame.fxml"));
                        Stage newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene scene = null;
                        try {
                            scene = new Scene(fxmlLoader.load(),800,500);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        newStage.setScene(scene);
                        newStage.show();

                    } else {
                        answerLabel.setText("Mauvaise réponse");


                        try {
                            TimeUnit.SECONDS.sleep(3);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Stage stage = (Stage) button.getScene().getWindow();
                        stage.close();
                        PlaylistService.mediaPlayer.stop();

                        FXMLLoader fxmlLoader = new FXMLLoader(MainMenuController.class.getResource("TrainingGame.fxml"));
                        Stage newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene scene = null;
                        try {
                            scene = new Scene(fxmlLoader.load(),800,500);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        newStage.setScene(scene);
                        newStage.show();
                    }
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
}
