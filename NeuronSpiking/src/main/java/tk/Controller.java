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
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.util.Duration;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

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
    TextField textFieldWaga1;
    @FXML
    TextField textFieldWaga2;
    @FXML
    TextField textFieldWaga3;
    @FXML
    TextField textFieldWaga4;

    @FXML
    RadioButton radioNormalnie;

    @FXML
    RadioButton radioCzesto;

    @FXML
    RadioButton radioRzadko;


    @FXML
    LineChart<Number, Number> wyjscieWykres;


    @FXML
    LineChart<Number, Number> input1LineChart;
    @FXML
    LineChart<Number, Number> input2LineChart;
    @FXML
    LineChart<Number, Number> input3LineChart;
    @FXML
    LineChart<Number, Number> input4LineChart;
    @FXML
    LineChart<Number, Number> membrana;

    @FXML
    Button przyciskStart;
    @FXML
    Button przyciskStop;

    public void initialize(URL location, ResourceBundle resources) {
        input1LineChart.setTitle("");
        animation = new Timeline();
        animation.getKeyFrames()
                .add(new KeyFrame(Duration.millis(1000),
                        (ActionEvent actionEvent) -> plotTime()));
        animation.setCycleCount(Animation.INDEFINITE);
        createContent();

        przyciskStart.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                play();
            }
        });

        przyciskStop.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                stop();
            }
        });


    }

    Los los = new Los();


    @FXML
    private NumberAxis xAxisLC1;

    @FXML
    private NumberAxis xAxisLC2;
    @FXML
    private NumberAxis xAxisLC3;
    @FXML
    private NumberAxis xAxisLC4;
    @FXML
    private NumberAxis xAxisMembrana;
    @FXML
    private NumberAxis xAxisWyjscie;

    float sumaWejsc = 0;// dla wejsc
    float wyjscie = 0;

    XYChart.Series s1 = new XYChart.Series<Number, Number>();

    XYChart.Series s2 = new XYChart.Series<Number, Number>();
    XYChart.Series s3 = new XYChart.Series<Number, Number>();
    XYChart.Series s4 = new XYChart.Series<Number, Number>();
    XYChart.Series sMembrana = new XYChart.Series<Number, Number>();
    XYChart.Series sImpulsWyjscie = new XYChart.Series<Number, Number>();
    private Timeline animation;

    private double sequence = 0;

    private double y = 0;

    private final int MAX_DATA_POINTS = 600, MAX = 1, MIN = 0;


    int[] impulsTab = new int[4]; // tablica z wylosowanymi impulsami 0 lub 1
    float[] waga = new float[4];

    float sumaWagMembrana = 1.7f;

    public Controller() {

        // create timeline to add new data every 60th of second
    }

    public void createContent() {

        xAxisLC1 = new NumberAxis(0, MAX_DATA_POINTS + 1, 2);
        xAxisLC2 = new NumberAxis(0, MAX_DATA_POINTS + 1, 2);
        xAxisLC3 = new NumberAxis(0, MAX_DATA_POINTS + 1, 2);
        xAxisLC4 = new NumberAxis(0, MAX_DATA_POINTS + 1, 2);
        xAxisMembrana = new NumberAxis(0, MAX_DATA_POINTS + 1, 2);
        xAxisWyjscie = new NumberAxis(0, MAX_DATA_POINTS + 1, 2);
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
        membrana.setAnimated(true);
        membrana.setLegendVisible(false);

        wyjscieWykres.setAnimated(true);
        wyjscieWykres.setLegendVisible(false);

        // xAxisLC1.setLabel("X Axis");
        xAxisLC1.setForceZeroInRange(false);
        xAxisLC2.setForceZeroInRange(false);
        xAxisLC3.setForceZeroInRange(false);
        xAxisLC4.setForceZeroInRange(false);
        xAxisMembrana.setForceZeroInRange(false);
        xAxisWyjscie.setForceZeroInRange(false);


        yAxis.setLabel("Y Axis");
        yAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(yAxis, "$", null));


        s1.getData().add(new XYChart.Data<Number, Number>(sequence, y));
        s2.getData().add(new XYChart.Data<Number, Number>(sequence, y));
        s3.getData().add(new XYChart.Data<Number, Number>(sequence, y));
        s4.getData().add(new XYChart.Data<Number, Number>(sequence, y));
        sMembrana.getData().add(new XYChart.Data<Number, Number>(sequence, y));
        sImpulsWyjscie.getData().add(new XYChart.Data<Number, Number>(sequence, y));

        // create some starting data
        input1LineChart.getData()
                .add(s1);
        input2LineChart.getData()
                .add(s2);
        input3LineChart.getData()
                .add(s3);
        input4LineChart.getData()
                .add(s4);

        membrana.getData()
                .add(sMembrana);

        wyjscieWykres.getData().add(sImpulsWyjscie);


    }

    private void wypiszWagi() {
        String wagaString1, wagaString2, wagaString3, wagaString4;

        wagaString1 = Float.toString(waga[0]);
        wagaString2 = Float.toString(waga[1]);
        wagaString3 = Float.toString(waga[2]);
        wagaString4 = Float.toString(waga[3]);


        textFieldWaga1.setText(wagaString1);
        textFieldWaga2.setText(wagaString2);
        textFieldWaga3.setText(wagaString3);
        textFieldWaga4.setText(wagaString4);


    }


    private void plotTime() {
        for (int i = 0; i < 4; i++)
            impulsTab[i] = getWartImpulsu();


        waga = los.losujTabWag();


        sumaWagMembrana += (los.sumaNaMembranie(waga)) / 6;

        if (sumaWagMembrana >= 3.8) {
            sumaWagMembrana = 3.85f;
        }


        wyjscie = 0;

        sumaWejsc = impulsTab[0] + impulsTab[1] + impulsTab[2] + impulsTab[3];//moze byc razy waga
        if (sumaWagMembrana <= sumaWejsc) {
            sumaWagMembrana = 1.75f;
            wyjscie = 1;
        }

        wypiszWagi();

        s1.getData().add(new XYChart.Data<Number, Number>(sequence, (impulsTab[0])));

        s2.getData().add(new XYChart.Data<Number, Number>(sequence, (impulsTab[1])));
        s3.getData().add(new XYChart.Data<Number, Number>(sequence, (impulsTab[2])));
        s4.getData().add(new XYChart.Data<Number, Number>(sequence, (impulsTab[3])));
        sMembrana.getData().add(new XYChart.Data<Number, Number>(sequence, sumaWagMembrana));
        sImpulsWyjscie.getData().add(new XYChart.Data<Number, Number>(sequence, wyjscie));

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

            xAxisMembrana.setLowerBound(xAxisMembrana.getLowerBound() + 1);
            xAxisMembrana.setUpperBound(xAxisMembrana.getUpperBound() + 1);
        }
    }


    private int getWartImpulsu() {
        int wynik;
        final ToggleGroup group = new ToggleGroup();
        radioCzesto.setToggleGroup(group);
        radioNormalnie.setToggleGroup(group);
        radioRzadko.setToggleGroup(group);
        if (radioRzadko.isSelected()) {

            sumaWagMembrana = sumaWagMembrana - 0.09f;
            wynik = los.getWartImpulsuRzadko();
            return wynik;
        }
        if (radioNormalnie.isSelected()) {

            wynik = los.getWartImpulsuNorm();
            return wynik;
        } else {
            radioCzesto.setSelected(true);
            wynik = los.getWartImpulsuCzesto();
            return wynik;
        }
    }


    public void play() {


        animation.play();
    }


    public void stop() {
        animation.pause();
    }

}
