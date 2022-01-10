package blinderGUI;

import blinderBackEnd.Server.Server;
import blinderBackEnd.model.Game;
import blinderBackEnd.model.Player;
import blinderBackEnd.model.Playlist;
import blinderBackEnd.model.Request;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

import static blinderGUI.Main.switchTo;

public class MainMenuController {
    @FXML
    private Button trainingModeButton;


    private Socket socket;

    private ObjectInputStream in;
    private ObjectOutputStream out;

    public void storeSocket(Socket socket, ObjectInputStream in, ObjectOutputStream out) throws IOException {
        this.socket = socket;
        this.in = in;
        this.out = out;
    }


    @FXML
    public void switchToTrainingMode(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainMenuController.class.getResource("TrainingGame.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(),1920,1080);

        stage.setScene(scene);
        stage.show();





    }

    @FXML
    public void switchToMultiplayerMode(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MultiplayerGamesList.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(),1920,1080);


        stage.setScene(scene);
        stage.show();
        System.out.println(socket);
        MultiplayerGamesListController multiplayerGamesListController = fxmlLoader.getController();
        multiplayerGamesListController.storeSocket(socket, in, out);


    }

    @FXML
    public void switchToTrainingGameImage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = switchTo(event, "TrainingGameImage.fxml");


    }


    public Socket getSocket(){
        return socket;
    }



}
