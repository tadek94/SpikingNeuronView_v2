package tk;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.util.Duration;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * Created by Tadzik on 2016-05-16.
 */

////////////////
/////////

public class Controller implements Initializable {


    @FXML
    LineChart<Number, Number> input1LineChart;
    @FXML
    LineChart<Number, Number> input2LineChart;
    @FXML
    LineChart<Number, Number> input3LineChart;
    @FXML
    LineChart<Number, Number> input4LineChart;
    @FXML
    LineChart<Number, Number> outputLineChart;

    public void initialize(URL location, ResourceBundle resources) {
        input1LineChart.setTitle("");
        animation = new Timeline();
        animation.getKeyFrames()
                .add(new KeyFrame(Duration.millis(1000),
                        (ActionEvent actionEvent) -> plotTime()));
        animation.setCycleCount(Animation.INDEFINITE);
        createContent();
        play();

    }

    @FXML
    private NumberAxis xAxisLC1;

    @FXML
    private NumberAxis xAxisLC2;
    @FXML
    private NumberAxis xAxisLC3;
    @FXML
    private NumberAxis xAxisLC4;
    @FXML
    private NumberAxis xAxisLCOut;

    XYChart.Series s1 = new XYChart.Series<Number, Number>();

    XYChart.Series s2 = new XYChart.Series<Number, Number>();
    XYChart.Series s3 = new XYChart.Series<Number, Number>();
    XYChart.Series s4 = new XYChart.Series<Number, Number>();
    XYChart.Series sOut = new XYChart.Series<Number, Number>();
    private Timeline animation;

    private double sequence = 0;

    private double y = 10;

    private final int MAX_DATA_POINTS = 30, MAX = 1, MIN = 0;

    int[] tab = new int[4];

    public Controller() {

        // create timeline to add new data every 60th of second
    }

    public void createContent() {

        xAxisLC1 = new NumberAxis(0, MAX_DATA_POINTS + 1, 2);
        xAxisLC2 = new NumberAxis(0, MAX_DATA_POINTS + 1, 2);
        xAxisLC3 = new NumberAxis(0, MAX_DATA_POINTS + 1, 2);
        xAxisLC4 = new NumberAxis(0, MAX_DATA_POINTS + 1, 2);
        xAxisLCOut = new NumberAxis(0, MAX_DATA_POINTS + 1, 2);
        final NumberAxis yAxis = new NumberAxis(MIN - 1, MAX + 1, 1);

        // setup chart
        input1LineChart.setAnimated(true);
        input1LineChart.setLegendVisible(false);
        // setup chart
        input2LineChart.setAnimated(true);
        input2LineChart.setLegendVisible(false);
        // setup chart
        input3LineChart.setAnimated(true);
        input3LineChart.setLegendVisible(false);
        // setup chart
        input4LineChart.setAnimated(true);
        input4LineChart.setLegendVisible(false);

        // setup chart
        outputLineChart.setAnimated(true);
        outputLineChart.setLegendVisible(false);

        // xAxisLC1.setLabel("X Axis");
        xAxisLC1.setForceZeroInRange(false);
        xAxisLC2.setForceZeroInRange(false);
        xAxisLC3.setForceZeroInRange(false);
        xAxisLC4.setForceZeroInRange(false);
        xAxisLCOut.setForceZeroInRange(false);

        yAxis.setLabel("Y Axis");
        yAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(yAxis, "$", null));


        s1.getData().add(new XYChart.Data<Number, Number>(sequence, y));
        s2.getData().add(new XYChart.Data<Number, Number>(sequence, y));
        s3.getData().add(new XYChart.Data<Number, Number>(sequence, y));
        s4.getData().add(new XYChart.Data<Number, Number>(sequence, y));
        sOut.getData().add(new XYChart.Data<Number, Number>(sequence, y));

        // create some starting data
        input1LineChart.getData()
                .add(s1);
        input2LineChart.getData()
                .add(s2);
        input3LineChart.getData()
                .add(s3);
        input4LineChart.getData()
                .add(s4);

        outputLineChart.getData()
                .add(sOut);


    }


    private void plotTime() {
        tab[0] = getNextValue();
        tab[1] = getNextValue();
        tab[2] = getNextValue();
        tab[3] = getNextValue();
        s1.getData().add(new XYChart.Data<Number, Number>(sequence, tab[0]));

        s2.getData().add(new XYChart.Data<Number, Number>(sequence, tab[1]));
        s3.getData().add(new XYChart.Data<Number, Number>(sequence, tab[2]));
        s4.getData().add(new XYChart.Data<Number, Number>(sequence, tab[3]));
        sOut.getData().add(new XYChart.Data<Number, Number>(sequence, tab[3] + tab[0] + tab[1] + tab[2]));

        sequence++;

        // after 25hours delete old data
        if (sequence > MAX_DATA_POINTS) {
            s1.getData().remove(0);
        }

        // every hour after 24 move range 1 hour
        if (sequence > MAX_DATA_POINTS - 1) {
            xAxisLC1.setLowerBound(xAxisLC1.getLowerBound() + 1);
            xAxisLC1.setUpperBound(xAxisLC1.getUpperBound() + 1);

            xAxisLC2.setLowerBound(xAxisLC2.getLowerBound() + 1);
            xAxisLC2.setUpperBound(xAxisLC2.getUpperBound() + 1);

            xAxisLC3.setLowerBound(xAxisLC3.getLowerBound() + 1);
            xAxisLC3.setUpperBound(xAxisLC3.getUpperBound() + 1);

            xAxisLC4.setLowerBound(xAxisLC4.getLowerBound() + 1);
            xAxisLC4.setUpperBound(xAxisLC4.getUpperBound() + 1);

            xAxisLCOut.setLowerBound(xAxisLCOut.getLowerBound() + 1);
            xAxisLCOut.setUpperBound(xAxisLCOut.getUpperBound() + 1);
        }
    }

    private int getNextValue() {
        Random rand = new Random();
        return rand.nextInt(2) * 10;
    }

    public void play() {
        animation.play();
    }


    public void stop() {
        animation.pause();
    }

}
