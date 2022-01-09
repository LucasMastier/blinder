package blinderGUI;

import blinderBackEnd.Server.Client;
import blinderBackEnd.model.*;
import javafx.application.Platform;
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
import java.util.ArrayList;
import java.util.Collections;

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

    private ObjectInputStream in;
    private ObjectOutputStream out;

    public void storeSocketAndGame(Socket socket, ObjectInputStream in, ObjectOutputStream out, Game game)throws IOException {
        this.socket = socket;
        this.in = in;
        this.out = out;
        currentGame = game;
    }



    @FXML
    public void initialize(){
        System.out.println(socket);
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

        backgroundThread.restart();
        /*
        Service<Void> backgroundThreadUpdatePlayersList = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        System.out.println("A lecoute du serveur");
                        while(true){
                            System.out.println(in);
                            ArrayList<Player> updatedPlayersList = (ArrayList<Player>) in.readObject();
                            System.out.println("apres in");
                            System.out.println("Received updated players list "+updatedPlayersList+" from server");
                            if(updatedPlayersList.size()!=currentGame.getPlayersList().size()){
                                currentGame.setPlayersList(updatedPlayersList);
                                updateConnectedPlayers();
                            }
                        }

                    }
                };
            }
        };

        backgroundThreadUpdatePlayersList.restart();*/
    }



    @FXML
    public void confirmUsername(ActionEvent event) throws IOException {
        if(!isConnected){

            //out.write("AddPlayerToGame "+currentGame.getName()+" "+usernameInput.getText());

            System.out.println(socket);
            Player player = new Player(usernameInput.getText(), currentGame);
            Request request = new Request("AddPlayerToGame", player, currentGame);
            out.writeObject(request);

            PlayerService.addPlayerToPlayersList(player);
            currentGame.addPlayer(player);
            currentPlayer = player;

            updateConnectedPlayers();


            isConnected = true;
        } else {
            warning.setText("Vous êtes déjà connecté en tant que "+currentPlayer.getUsername()+" !");
        }

    }

    public void switchToMultiplayerGame(ActionEvent event) throws IOException {
        FXMLLoader loader = switchTo(event, "MultiplayerGame.fxml");

        //MultiplayerGameController multiplayerGameController = loader.getController();
        //multiplayerGameController.storeSocket(socket, in, out);

    }


    public void updateConnectedPlayers(){
        playersContainer.getChildren().clear();
        for(Player player : currentGame.getPlayersList()){
            playersContainer.getChildren().add(new Label(player.getUsername()));
        }
    }

    @FXML
    public void updateConnectedPlayersOnClick(ActionEvent event) throws IOException, ClassNotFoundException {


            Request request = new Request("UpdatePlayersList", currentGame);

            out.writeObject(request);
            ArrayList<Player> updatedPlayersList = (ArrayList<Player>) in.readObject();


            currentGame.setPlayersList(updatedPlayersList);
            for(Player players : updatedPlayersList){
                System.out.println(players.getUsername()+" est connecté a la partie");
            }

                    /*
                    playersContainer.getChildren().clear();
                    for (Player player : updatedPlayersList) {
                        System.out.println(player.getUsername() + " est présent");
                        playersContainer.getChildren().add(new Label(player.getUsername()));
                    }*/

            System.out.println("Fin du thread");








    }


}
