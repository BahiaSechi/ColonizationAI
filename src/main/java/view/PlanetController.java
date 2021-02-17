package view;

import javafx.application.Application;
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

        GridPane gridpane = new GridPane();
        Game game = new Game();
        Planet planet = game.getPlanet();
        Tile[][] tile = planet.getMap();

        for(int i = 0 ; i < planet.getSIZE_Y() ; i++) {
            for (int j = 0; j < planet.getSIZE_X(); j++) {

                Image img = new Image(tile[j][i].getType().getNameFile());

                ImageView imgView = new ImageView();
                imgView.setFitHeight(20);
                imgView.setFitWidth(20);
                imgView.setImage(img);
                GridPane.setConstraints(imgView, i, j);
                gridpane.getChildren().addAll(imgView);
            }
        }

        // width / height
        stage.setScene(new Scene(gridpane, gridpane.getVgap(), gridpane.getHgap()));

        stage.show();
    }
}
