/**
 * Created by Tadzik on 2016-05-13.
 */

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.chart.CategoryAxis;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;

import java.io.IOException;


public class Wykres extends Application {


    @Override
    public void start (Stage primaryStage ) throws IOException {
        Parent root  = FXMLLoader.load(getClass().getClassLoader().getResource("Widok.fxml"));
        Scene scene = new Scene(root,700,500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Neuron");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
