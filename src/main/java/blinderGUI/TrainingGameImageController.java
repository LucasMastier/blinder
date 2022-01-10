package blinderGUI;

import blinderBackEnd.model.*;
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
import static blinderGUI.Main.switchTo;

public class TrainingGameImageController {

    @FXML
    private TilePane buttonsContainer;

    @FXML
    private Label answerLabel;

    @FXML
    ImageView imageView;

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
                    if(PicturelistService.checkAuthor(button.getText())){
                        answerLabel.setText("Bonne réponse !");
                        GameService.setScore_cpt(GameService.getScore_cpt()+1);
                        GameService.setRound_cpt(GameService.getRound_cpt()+1);


                        if(GameService.getRound_cpt()==5){
                            //switchTo tableau des scores
                            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("TrainingScore.fxml"));

                            //Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            Scene scene = null;
                            try {
                                scene = new Scene(fxmlLoader.load(),800,500);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            stage.setScene(scene);
                            stage.show();
                            GameService.setRound_cpt(0);

                        } else {
                            try {
                                TimeUnit.SECONDS.sleep(3);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            Stage stage = (Stage) button.getScene().getWindow();
                            stage.close();

                            FXMLLoader fxmlLoader = new FXMLLoader(MainMenuController.class.getResource("TrainingGameImage.fxml"));
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



                    } else {
                        answerLabel.setText("Mauvaise réponse");
                        GameService.setRound_cpt(GameService.getRound_cpt()+1);

                        if(GameService.getRound_cpt()==5){
                            //switchTo tableau des scores
                            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("TrainingScore.fxml"));

                            //Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            Scene scene = null;
                            try {
                                scene = new Scene(fxmlLoader.load(),800,500);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            stage.setScene(scene);
                            stage.show();
                            GameService.setRound_cpt(0);

                        } else {
                            try {
                                TimeUnit.SECONDS.sleep(3);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            Stage stage = (Stage) button.getScene().getWindow();
                            stage.close();

                            FXMLLoader fxmlLoader = new FXMLLoader(MainMenuController.class.getResource("TrainingGameImage.fxml"));
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

}
