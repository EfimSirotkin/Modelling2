package sample;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Controller {

    @FXML
    private CategoryAxis uniformXAxis;
    @FXML
    private NumberAxis uniformYAxis;

    @FXML
    private CategoryAxis gaussianXAxis;
    @FXML
    private NumberAxis gaussianYAxis;

    @FXML
    private CategoryAxis exponentialXAxis;
    @FXML
    private NumberAxis exponentialYAxis;

    @FXML
    private CategoryAxis gammamXAxis;
    @FXML
    private NumberAxis gammaYAxis;

    @FXML
    private CategoryAxis simpsonXAxis;
    @FXML
    private NumberAxis simpsonYAxis;

    @FXML
    private CategoryAxis triangleXAxis;
    @FXML
    private NumberAxis triangleYAxis;

    @FXML
    private BarChart<String, Number> UniformDistributionHistogram;
    @FXML
    private BarChart<String, Number> GaussianDistributionHistogram;
    @FXML
    private BarChart<String, Number> ExponentialDistributionHistogram;
    @FXML
    private BarChart<String, Number> GammaDistributionHistogram;
    @FXML
    private BarChart<String, Number> SimpsonDistributionHistogram;
    @FXML
    private BarChart<String, Number> TriangleDistributionHistogram;

    @FXML
    private TextField uniformStartField;
    @FXML
    private TextField uniformEndField;
    @FXML
    private TextField gaussianMathExpectationField;
    @FXML
    private TextField gaussianMeanSquareField;
    @FXML
    private TextField exponentialLambdaField;
    @FXML
    private TextField gammaLambdaField;
    @FXML
    private TextField gammaTettaField;
    @FXML
    private TextField triangleAField;
    @FXML
    private TextField triangleBField;



    @FXML
    public XYChart.Series<String, Number> getHistogramBarChartSeries() {

        int listSize = Main.histogramDataRetriever.getFrequenciesList().size();
        ArrayList<Integer> sourceFrequencyList = Main.histogramDataRetriever.getFrequenciesList();
        XYChart.Series<String, Number> tempSeries = new XYChart.Series<>();

        double range = 0;
        double step = (double) 1 / HistogramDataRetriever.k;
        BigDecimal bigDecimal = new BigDecimal(Double.toString(step));
        bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
        String valueOfStep = String.valueOf(bigDecimal.doubleValue());
        for(int i = 0; i < listSize; i++){
            tempSeries.getData().add(new XYChart.Data<>(valueOfStep, sourceFrequencyList.get(i)));
            range+= step;
            bigDecimal = new BigDecimal(Double.toString(range));
            bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
            valueOfStep = String.valueOf(bigDecimal.doubleValue());
        }
        return tempSeries;
    }

    public int toInt(String value) {
        return Integer.parseInt(value);
    }

}

