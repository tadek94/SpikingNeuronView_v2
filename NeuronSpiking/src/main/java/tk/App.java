package tk;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.applet.Applet;

/**
 * Created by Tadzik on 2016-05-18.
 */
public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Widok.fxml"));
            Scene scene = new Scene(root, 1000, 600);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Neuron");
            primaryStage.show();


//        primaryStage.setScene(new Scene(createContent()));
//        primaryStage.setTitle("SpikingNeuron");
//        primaryStage.show();


        }

    public static void main(String[] args) {
        launch(args);
    }
    }

