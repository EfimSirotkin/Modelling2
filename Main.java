package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static HistogramDataRetriever histogramDataRetriever;
    public static NumberGenerator numberGenerator;

    private double a = 3;
    private double m = 10000;
    private double R = 7;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("МОД, лаб №2");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.setMaximized(true);
        histogramDataRetriever = new HistogramDataRetriever();
        Main.numberGenerator = new NumberGenerator();
        double tempR = R;
        for(int i = 0; i < NumberGenerator.n; i++)
            tempR = Main.numberGenerator.Lemer(a, m, tempR);

//        System.out.println("111111111" + numberGenerator.getRandomNumbers());
//        numberGenerator.generateUniformDistribution(0.0, 5.0);
//        System.out.println("2222222" + numberGenerator.getGeneratedNumbers());
//        numberGenerator.generateExponentialDistribution(1.56);
//        System.out.println("333333333" + numberGenerator.getGeneratedNumbers());
//        numberGenerator.generateGammaDistribution(0.89, 1.43);
//        System.out.println("444444444" + numberGenerator.getGeneratedNumbers());
//        numberGenerator.generateGaussianDistribution(0.89, 0.23);
//        System.out.println("555555555" + numberGenerator.getGeneratedNumbers());
//        numberGenerator.generateTriangleDistribution(2.3, 4.9);
//        System.out.println("666666666" + numberGenerator.getGeneratedNumbers());
//        numberGenerator.generateSimpsonDistribution();
//        System.out.println("777777777" + numberGenerator.getGeneratedNumbers());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
