package sample;

import javax.naming.ldap.Control;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class NumberGenerator {


    public static int n = 100000;
    public static ArrayList<Double> randomNumbers;
    public static ArrayList<Double> generatedNumbers;


    public static double mathExpectation;
    public static double seqDispersion;
    public static double seqDeviation;

    public NumberGenerator() {
        randomNumbers = new ArrayList<>(n);
        generatedNumbers = new ArrayList<>(n);
    }

    public double Lemer(double a, double m, double R) {
        double step1 = a * R;
        double step2 = step1 % m;
        randomNumbers.add(new Double(step2 / m));
        return step2;
    }

    public void generateUniformDistribution(double start, double end) {
        generatedNumbers.clear();
        for (int i = 0; i < n; i++) {
            double randomNumber = 0.0 + Math.random()* 1.0;
            generatedNumbers.add(start + (end - start) * randomNumber);
        }
        calculateAverage();
        calсulateDispersion();
        calculateDeviation();


        System.out.println("Uniform: " + generatedNumbers);
    }

    public void generateGaussianDistribution(double mathExpectation, double meanSquare) {

        double MAX_RAND = 10.0;
        double MIN_RAND = 0.0;

        generatedNumbers.clear();
        int accuracy = 6;
        int tempCounter = 0;

        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = 1; j < accuracy; ++j) {
                double randomNumber = 0.0 + Math.random() * 1.0;
                sum = sum + randomNumber;
                tempCounter++;
            }
            generatedNumbers.add(i, Math.abs(mathExpectation + meanSquare * Math.sqrt(2) * (sum - 3)));
        }

        calculateAverage();
        calсulateDispersion();
        calculateDeviation();

        System.out.println("Gaussian: " + generatedNumbers);
    }

    public void generateExponentialDistribution(double lambda) {
        generatedNumbers.clear();
        for (int i = 0; i < n; ++i) {
            double randomNumber = 0.0 + Math.random() * 1.0;
            generatedNumbers.add(i, (-1 / lambda) * Math.log(randomNumber));
        }

        calculateAverage();
        calсulateDispersion();
        calculateDeviation();


    }

    public void generateGammaDistribution(double lambda, double tetta) {

        generatedNumbers.clear();
        double tempCounter = 0;
        for (int i = 0; i < n; ++i) {
            double randMultiplication = 1;
            for (int j = 0; j <= tetta; ++j) {
                double randomNumber = 0.0 + Math.random() * 1.0;
                randMultiplication *= randomNumber;
                tempCounter++;
            }
            generatedNumbers.add(i, (-1 / lambda) * Math.log(randMultiplication));
        }
        calculateAverage();
        calсulateDispersion();
        calculateDeviation();

    }

    public void generateTriangleDistribution(double a, double b) {
        generatedNumbers.clear();
        for (int i = 0; i < n; ++i) {
            double firstNumber = 0.0 + Math.random() * 1.0;
            double secondNumber = 0.0 + Math.random() * 1.0;
            generatedNumbers.add(i, a + (b - a) * Math.min(firstNumber, secondNumber));
        }
        calculateAverage();
        calсulateDispersion();
        calculateDeviation();
    }

    public void generateSimpsonDistribution(double a, double b) {
        generatedNumbers.clear();
        double start = a / 2;
        double end = b / 2;
        for (int i = 0; i < n; ++i) {
            double firstNumber = start + ((end - start) * (0. + Math.random() * 1.0));
            double secondNumber = start + ((end - start) * (0 + Math.random() * 1.0));
            generatedNumbers.add(i, firstNumber + secondNumber);
        }
        calculateAverage();
        calсulateDispersion();
        calculateDeviation();
    }

    public static ArrayList<Double> getGeneratedNumbers() {
        return generatedNumbers;
    }

    public static ArrayList<Double> getRandomNumbers() {
        return randomNumbers;
    }

    public void calculateAverage() {
        double sum = 0;
        for (Double number : generatedNumbers)
            sum += number;
        mathExpectation = getMaxScaledValue(sum / n, 4);
    }

    public void calсulateDispersion() {
        double sum = 0;
        for (Double number : generatedNumbers)
            sum += Math.pow(number - mathExpectation, 2);

        seqDispersion = getMaxScaledValue(sum / n, 4);
    }


    public void calculateDeviation() {
        seqDeviation = getMaxScaledValue(Math.sqrt(seqDispersion), 4);
    }

    public static double getMinScaledValue(Double value, int scale)
    {
        BigDecimal bigDecimal = new BigDecimal(value);
        bigDecimal = bigDecimal.setScale(scale, RoundingMode.DOWN);
        return bigDecimal.doubleValue();
    }

    public static double getMaxScaledValue(Double value, int scale) {
        BigDecimal bigDecimal = new BigDecimal(value);
        bigDecimal = bigDecimal.setScale(scale, RoundingMode.UP);
        return bigDecimal.doubleValue();
    }


}
