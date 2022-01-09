package blinderGUI;

import blinderBackEnd.Server.Client;
import blinderBackEnd.model.Game;
import blinderBackEnd.model.Player;
import blinderBackEnd.model.PlayerService;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;

import java.io.*;
import java.net.Socket;

import static blinderGUI.Main.switchTo;

public class GameConnectionController {

    @FXML
    private TextField usernameInput;

    @FXML
    private Button confirmButton;

    @FXML
    private static Game currentGame;

    @FXML
    private TilePane playersContainer;

    @FXML
    private boolean isConnected = false;

    private boolean allPlayersAreConnected = false;

    private static Player currentPlayer;

    @FXML
    private Button launchGameButton;

    @FXML
    private Label warning;

    //Socket
    private Socket socket;

    private BufferedReader in;
    private PrintWriter out;

    public void storeSocket(Socket socket, BufferedReader in, PrintWriter out) throws IOException {
        this.socket = socket;
        this.in = in;
        this.out = out;
    }


    @FXML
    public void storeGameInstance(Game game){
        currentGame = game;
    }

    @FXML
    public void initialize(){
        //System.out.println(currentObjectOutputStream);
        Service<Void> backgroundThread = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {

                        while(true){
                            if(allPlayersAreConnected){
                                launchGameButton.setVisible(true);
                                break;
                            }
                        }


                        return null;
                    }
                };
            }
        };

        backgroundThread.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                System.out.println("CA MARCHE");
            }
        });

        backgroundThread.setOnCancelled(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                System.out.println("Erreur");
            }
        });

        backgroundThread.restart();
    }



    @FXML
    public void confirmUsername(ActionEvent event) throws IOException {
        if(!isConnected){

            //out.write("AddPlayerToGame "+currentGame.getName()+" "+usernameInput.getText());

            Player player = new Player(usernameInput.getText());

            PlayerService.addPlayerToPlayersList(player);
            currentGame.addPlayer(player);
            currentPlayer = player;
            playersContainer.getChildren().add(new Label(usernameInput.getText()));

            isConnected = true;
        } else {
            warning.setText("Vous êtes déjà connecté en tant que "+currentPlayer.getUsername()+" !");
        }

    }

    public void switchToMultiplayerGame(ActionEvent event) throws IOException {
        FXMLLoader loader = switchTo(event, "MultiplayerGame.fxml");

        MultiplayerGameController multiplayerGameController = loader.getController();
        multiplayerGameController.storeSocket(socket, in, out);

    }


}
