/**
 * Address :
 * ENSICAEN
 * 6 Boulevard Marechal Juin
 * F-14050 Caen Cedex
 * Note :
 * This file is owned by an ENSICAEN student.  No portion of this
 * document may be reproduced, copied  or revised without written
 * permission of the authors.
 *
 * @author PRUNIER Bastien <bastien.prunier@ecole.ensicaen.fr>
 * @author RABOTIN Mateo <mateo.rabotin@ecole.ensicaen.fr>
 * @author SECHI Bahia <bahia.sechi@ecole.ensicaen.fr>
 * @author SERVAT Clement <clement.servat@ecole.ensicaen.fr>
 *
 * @date February 2021
 * @file PlanetController.java
 * @version 1.0
 */

package view;

import javafx.animation.AnimationTimer;
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
import simulation.planet.tiles.TileType;
import simulation.robots.Pos;

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

        stage.show();
        Scene scene = new Scene(gridpane, 24 * planet.getSIZE_X(), 24 * planet.getSIZE_Y());
        stage.setScene(scene);

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                runLap(planet, gridpane);
            }
        }.start();
    }

    private void runLap(Planet planet, GridPane gridpane) {

        Tile[][] tile = planet.getMap();

        // RUN THE ROBOTS


        // RUN THE PLANET
        planet.update();

        // SHOW THE SCENE
        gridpane.getChildren().clear();
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
    }

    // Close properly the GUI.
    public static class ExitButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent arg0) {
            Platform.exit();
        }
    }
}
