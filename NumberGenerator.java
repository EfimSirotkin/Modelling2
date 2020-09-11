package sample;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class NumberGenerator {


    public static int n = 100;
    public static ArrayList<Double> randomNumbers;
    public static ArrayList<Double> generatedNumbers;


    private double mathExpectation;
    private double seqDispersion;
    private double seqDeviation;

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
        for (int i = 0; i < n; i++)
            generatedNumbers.add(start + (end - start) * randomNumbers.get(i));

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
                sum = sum + randomNumbers.get(tempCounter % n);
                tempCounter++;
            }
            generatedNumbers.add(i, Math.abs(mathExpectation + meanSquare * Math.sqrt(2) * (sum - 3)));
        }
        System.out.println("Gaussian: " + generatedNumbers);
    }

    public void generateExponentialDistribution(double lambda) {
        generatedNumbers.clear();
        for (int i = 0; i < n; ++i) {
            generatedNumbers.add(i, (-1 / lambda) * Math.log(randomNumbers.get(i)));
        }
    }

    public void generateGammaDistribution(double lambda, double tetta) {

        generatedNumbers.clear();
        double tempCounter = 0;
        for (int i = 0; i < n; ++i) {
            double randMultiplication = 1;
            for (int j = 0; j <= tetta; ++j) {
                randMultiplication *= randomNumbers.get((int) (tempCounter % n));
                tempCounter++;
            }
            generatedNumbers.add(i, (-1 / lambda) * Math.log(randMultiplication));
        }
    }

    public void generateTriangleDistribution(double a, double b) {
        generatedNumbers.clear();
        for (int i = 0; i < n; ++i) {
            generatedNumbers.add(i, a + (b - a) * Math.min(randomNumbers.get((i * 2) % n), randomNumbers.get((i * 2 + 1) % n)));
        }
    }

    public void generateSimpsonDistribution() {
        generatedNumbers.clear();
        for (int i = 0; i < n; ++i) {
            generatedNumbers.add(i, randomNumbers.get((i * 2) % n) + randomNumbers.get((i * 2 + 1) % n));
        }
    }

    public static ArrayList<Double> getGeneratedNumbers() {
        return generatedNumbers;
    }

    public static ArrayList<Double> getRandomNumbers() {
        return randomNumbers;
    }

    public void calculateAverage() {
        double sum = 0;
        for (Double number : randomNumbers)
            sum += number;
        mathExpectation = getScaledValue(sum / n, 4);
    }

    public void calсulateDispersion() {
        double sum = 0;
        for (Double number : randomNumbers)
            sum += Math.pow(number - mathExpectation, 2);

        seqDispersion = getScaledValue(sum / n, 4);
    }


    public void calculateDeviation() {
        seqDeviation = getScaledValue(Math.sqrt(seqDispersion), 4);
    }

    public static double getScaledValue(Double value, int scale) {
        BigDecimal bigDecimal = new BigDecimal(value);
        bigDecimal = bigDecimal.setScale(scale, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }


}
