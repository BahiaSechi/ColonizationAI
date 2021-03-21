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
import simulation.Game;
import simulation.planet.Planet;
import simulation.planet.tiles.Tile;
import simulation.robots.Robot;

import java.util.Map;

public class PlanetController extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    /**
     * @param stage
     */
    @Override
    public void start(Stage stage) {

        stage.setTitle("Colonisation");
        stage.setResizable(false);
        stage.getIcons().add(new Image("/sprites/robot/icon.jpg"));

        GridPane gridpane = new GridPane();
        Game game = new Game();
        Planet planet = game.getPlanet();
        Map<Integer, Robot> robotMap = game.getRobotController().getRobots();

        stage.show();
        Scene scene = new Scene(gridpane, 24 * planet.getSIZE_X(), 24 * planet.getSIZE_Y());
        stage.setScene(scene);

        new AnimationTimer() {
            private int dayDecount = 2922;
            public void handle(long currentNanoTime) {
                if (this.dayDecount >= 0) {
                    game.runDay();
                    generateView(planet, gridpane, robotMap);
                    dayDecount--;
                } else {
                    this.stop();
                }
            }
        }.start();
    }

    /**
     * @param planet
     * @param gridpane
     * @param robotMap
     */
    private void generateView(Planet planet, GridPane gridpane, Map<Integer, Robot> robotMap) {

        Tile[][] tile = planet.getMap();

        // PRINTING THE PLANET
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

        // PRINTING ROBOTS
        for (Map.Entry<Integer, Robot> entry : robotMap.entrySet()) {

            Robot rob = entry.getValue();
            String fileName = rob.getType().getNameFile();
            Image img = new Image(fileName);

            int posX = rob.getState().getPos().getX();
            int posY = rob.getState().getPos().getY();

            ImageView imgV = new ImageView();

            imgV.setImage(img);
            imgV.toFront();

            GridPane.setConstraints(imgV, posY, posX);
            gridpane.getChildren().add(imgV);
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
