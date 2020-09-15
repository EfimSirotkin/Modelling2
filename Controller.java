package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class Controller implements Initializable {

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
    private Button startButton;
    @FXML
    private Button uniformStartButton;
    @FXML
    private Button gaussianStartButton;
    @FXML
    private Button exponentialStartButton;
    @FXML
    private Button gammaStartButton;
    @FXML
    private Button triangleStartButton;
    @FXML
    private Button simpsonStartButton;




    @FXML
    public XYChart.Series<String, Number> getHistogramBarChartSeries() {

        ArrayList<Double> sourceList= Main.numberGenerator.getGeneratedNumbers();
        double minValue = Collections.min(sourceList);
        double maxValue = Collections.max(sourceList);

        double startInterval = NumberGenerator.getMinScaledValue(minValue,1);
        double endInterval =  NumberGenerator.getMaxScaledValue(maxValue, 1);

        double step = (maxValue - minValue) / HistogramDataRetriever.k;


        int listSize = Main.histogramDataRetriever.getFrequenciesList().size();
        ArrayList<Integer> sourceFrequencyList = Main.histogramDataRetriever.getFrequenciesList();
        XYChart.Series<String, Number> tempSeries = new XYChart.Series<>();

        for(int i = 0; i < listSize; i++){
            tempSeries.getData().add(new XYChart.Data<>(String.valueOf(NumberGenerator.getMaxScaledValue(startInterval,3)), sourceFrequencyList.get(i)));
            startInterval+= step;

        }
        return tempSeries;
    }



    public int toInt(String value) {
        return Integer.parseInt(value);
    }

    public void onUniformStartButtonClicked() {

        UniformDistributionHistogram.getData().clear();
        double start = Double.parseDouble(uniformStartField.getText());
        double end = Double.parseDouble(uniformEndField.getText());
        Main.numberGenerator.generateUniformDistribution(start, end);
        Main.histogramDataRetriever.calculateFrequencies(Main.numberGenerator.getGeneratedNumbers());
        UniformDistributionHistogram.getData().add(getHistogramBarChartSeries());

    }

    public void onGaussianStartButtonClicked()
    {
        GaussianDistributionHistogram.getData().clear();
        double mathExpectation = Double.parseDouble(gaussianMathExpectationField.getText());
        double stdDeviation = Double.parseDouble(gaussianMeanSquareField.getText());
        Main.numberGenerator.generateGaussianDistribution(mathExpectation, stdDeviation);
        Main.histogramDataRetriever.calculateFrequencies(Main.numberGenerator.getGeneratedNumbers());
        GaussianDistributionHistogram.getData().add(getHistogramBarChartSeries());
    }

    public void onExponentialStartButtonClicked()
    {
        ExponentialDistributionHistogram.getData().clear();
        double lambda = Double.parseDouble(exponentialLambdaField.getText());
        Main.numberGenerator.generateExponentialDistribution(lambda);
        Main.histogramDataRetriever.calculateFrequencies(Main.numberGenerator.getGeneratedNumbers());
        ExponentialDistributionHistogram.getData().add(getHistogramBarChartSeries());
    }

    public void onGammaStartButtonClicked()
    {
        GammaDistributionHistogram.getData().clear();
        double lambda = Double.parseDouble(gammaLambdaField.getText());
        double tetta = Double.parseDouble(gammaLambdaField.getText());
        Main.numberGenerator.generateGammaDistribution(lambda, tetta);
        Main.histogramDataRetriever.calculateFrequencies(Main.numberGenerator.getGeneratedNumbers());
        GammaDistributionHistogram.getData().add(getHistogramBarChartSeries());
    }

    public void onTriangleStartButtonClicked()
    {
        TriangleDistributionHistogram.getData().clear();
        double a = Double.parseDouble(triangleAField.getText());
        double b = Double.parseDouble(triangleBField.getText());
        Main.numberGenerator.generateTriangleDistribution(a,b);
        Main.histogramDataRetriever.calculateFrequencies(Main.numberGenerator.getGeneratedNumbers());
        TriangleDistributionHistogram.getData().add(getHistogramBarChartSeries());
    }

    public void onSimpsonStartButtonClicked()
    {
        SimpsonDistributionHistogram.getData().clear();
        Main.numberGenerator.generateSimpsonDistribution();
        Main.histogramDataRetriever.calculateFrequencies(Main.numberGenerator.getGeneratedNumbers());
        SimpsonDistributionHistogram.getData().add(getHistogramBarChartSeries());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) { }
}

