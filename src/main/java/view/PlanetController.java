package view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import simulation.planet.Planet;
import simulation.Game;
import simulation.planet.tiles.Tile;

public class PlanetController extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        stage.setTitle("Colonisation");
        stage.setResizable(false);
        stage.getIcons().add(new Image("/sprites/robot/icon.jpg"));

        GridPane gridpane = new GridPane();
        Game game = new Game();
        Planet planet = game.getPlanet();
        Tile[][] tile = planet.getMap();

        for (int i = 0; i < planet.getSIZE_Y(); i++) {
            for (int j = 0; j < planet.getSIZE_X(); j++) {

                Image img = new Image(tile[j][i].getType().getNameFile());

                ImageView imgView = new ImageView();
                imgView.setFitHeight(24);
                imgView.setFitWidth(24);
                imgView.setImage(img);
                GridPane.setConstraints(imgView, i, j);
                gridpane.getChildren().addAll(imgView);
            }
        }

        // width / height
        stage.setScene(new Scene(gridpane, 24 * planet.getSIZE_X(), 24 * planet.getSIZE_Y()));

        stage.show();
    }

    // Close properly the GUI.
    public static class ExitButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent arg0) {
            Platform.exit();
        }
    }
}
