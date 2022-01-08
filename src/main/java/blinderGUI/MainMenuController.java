package blinderGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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

    private BufferedReader in;
    private PrintWriter out;

    public void storeSocket(Socket socket, BufferedReader in, PrintWriter out) throws IOException {
        this.socket = socket;
        this.in = in;
        this.out = out;
    }


    @FXML
    public void switchToTrainingMode(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(MainMenuController.class.getResource("TrainingGame.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(),800,500);

        stage.setScene(scene);
        stage.show();

        out.println("TEST");
        String serverResponse = in.readLine();
        switch(serverResponse){
            case "Menu":
                System.out.println("Ok menu");
        }


    }

    @FXML
    public void switchToMultiplayerMode(ActionEvent event) throws IOException {
        System.out.printf("TEST");
        switchTo(event, "MultiplayerGamesList.fxml");
    }

}
