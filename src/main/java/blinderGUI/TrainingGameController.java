package blinderGUI;

import blinderBackEnd.model.PlaylistService;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class TrainingGameController {

    @FXML
    private TilePane buttonsContainer;

    public void addButtons(ActionEvent event){

    }

    public void playSong(ActionEvent event){
        PlaylistService.playRandomSongFromPlaylist();
    }
    
}
