package blinderGUI;

import blinderBackEnd.Server.Client;
import blinderBackEnd.Server.Server;
import blinderBackEnd.Server.ServerConnection;
import blinderBackEnd.model.*;
import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainMenu.fxml"));


        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle("Blinder");
        stage.setScene(scene);
        stage.show();


        Socket socket = new Socket("localhost", Server.PORT);


        //BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //PrintWriter out = new PrintWriter(socket.getOutputStream(),true);

        OutputStream outputStream = socket.getOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(outputStream);

        Service<Void> backgroundThread = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {


                        InputStream inputStream = socket.getInputStream();
                        ObjectInputStream in = new ObjectInputStream(inputStream);



                        MainMenuController mainMenuController = fxmlLoader.getController();
                        mainMenuController.storeSocket(socket, in, out);


                        return null;
                    }
                };
            }
        };

        backgroundThread.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                System.out.println("Socket créé et transféré");
            }
        });

        backgroundThread.setOnCancelled(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                System.out.println("Erreur lors de la création du socket");
            }
        });

        backgroundThread.restart();






    }

    @FXML
    public static FXMLLoader switchTo(ActionEvent event, String page) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(page));

        //Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(),800,500);

        stage.setScene(scene);
        stage.show();
        return fxmlLoader;
    }

    public static void main(String[] args) throws IOException {

        Song vvs = new Song("Ninho","VVS", "src/main/resources/mp3files/VVS.mp3");
        Song tr = new Song("Leto","Tout recommencer", "src/main/resources/mp3files/Tout_recommencer.mp3");
        Song fr = new Song("Freeze Corleone","Freeze raël", "src/main/resources/mp3files/Freeze_Rael.mp3");
        Song hs = new Song("Hamza","HS", "src/main/resources/mp3files/HS.mp3");
        Song em = new Song("Eminem","Real Slim Shady","src/main/resources/mp3files/eminem.mp3");
        Song dk = new Song("Drake","God's Plan","src/main/resources/mp3files/drake.mp3");
        Song aw = new Song("Alpha Wann","Le Piège","src/main/resources/mp3files/Alpha_Wann.mp3");
        Song da = new Song("Damso","Morose","src/main/resources/mp3files/Damso.mp3");
        Song ni = new Song("Niska","Salé","src/main/resources/mp3files/Niska.mp3");
        Song pnl = new Song("PNL","Au DD","src/main/resources/mp3files/PNL.mp3");


        Playlist rap = new Playlist("Rap");
        rap.addToPlaylist(vvs);
        rap.addToPlaylist(tr);
        rap.addToPlaylist(fr);
        rap.addToPlaylist(hs);
        rap.addToPlaylist(em);
        rap.addToPlaylist(dk);
        rap.addToPlaylist(aw);
        rap.addToPlaylist(da);
        rap.addToPlaylist(ni);
        rap.addToPlaylist(pnl);

        PlaylistService.setCurrentPlaylist(rap);
        PlaylistService.addToPlaylists(rap);

        Game game = new Game(rap, "Partie 1");
        GameService.addGameToList(game);

        //Lancement du client


        launch();




    }
}