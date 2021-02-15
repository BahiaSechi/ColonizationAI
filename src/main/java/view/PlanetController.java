package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PlanetController extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        stage.setTitle("Colonisation");
        stage.setResizable(false);

        GridPane gridpane = new GridPane();

        //Image img = new Image("/sprites/world/grasstest.png");
        Image img = new Image("/sprites/world/grass.png");

        for(int i = 0 ; i < 21 ; i++) {
            for (int j = 0; j < 21; j++) {
                ImageView imgView = new ImageView();
                imgView.setFitHeight(20);
                imgView.setFitWidth(20);
                imgView.setImage(img);
                gridpane.setConstraints(imgView, i, j);
                gridpane.getChildren().addAll(imgView);
            }
        }

        // width / height
        stage.setScene(new Scene(gridpane, gridpane.getVgap(), gridpane.getHgap()));

        stage.show();

    }
}
