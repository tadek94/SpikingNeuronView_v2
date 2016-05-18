package cos;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Tadzik on 2016-05-16.
 */

////////////////
    /////////
    
public class Controller implements Initializable {

    @FXML
    LineChart<String,Number> input1LineChart;

    public void initialize(URL location, ResourceBundle resources) {
        input1LineChart.setTitle("");
    }
}
