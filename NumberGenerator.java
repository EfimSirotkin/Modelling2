package sample;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class NumberGenerator {


    public static int n = 100000;
    private ArrayList<Double> randomNumbers;

    public NumberGenerator() {
        randomNumbers = new ArrayList<>(n);
    }

    public void generateUniformDistribution(double start, double end) {
        for (int i = 0; i < n; i++)
            randomNumbers.add(i, start + (end - start) * randomNumbers.get(i));
    }

    public void generateGaussianDistribution(double mathExpectation, double meanSquare) {
        ArrayList<Double> tempNumbers = new ArrayList<>(n);
        tempNumbers.addAll(randomNumbers);
        int accuracy = 6;
        int tempCounter = 0;

        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = 1; j < accuracy; ++j) {
                sum = sum + (tempNumbers.get(tempCounter % n));
                tempCounter++;
            }
            randomNumbers.add(i, Math.abs(mathExpectation + meanSquare * Math.sqrt(2) * (sum - 3)));
        }
    }

    public void generateExponentialDistribution(double lambda) {
        for (int i = 0; i < n; ++i) {
            randomNumbers.add(i, (-1 / lambda) * Math.log(randomNumbers.get(i)));
        }
    }

    public void generateGammaDistribution(double lambda, double tetta) {

        ArrayList<Double> tempNumbers = new ArrayList<>(n);
        tempNumbers.addAll(randomNumbers);

        double tempCounter = 0;
        for (int i = 0; i < n; ++i) {
            double randMultiplication = 1;
            for (int j = 0; j <= tetta; ++j) {
                randMultiplication *= tempNumbers.get((int) (tempCounter % n));
                tempCounter++;
            }
            randomNumbers.add(i, (-1 / lambda) * Math.log(randMultiplication));
        }
    }

    public void generateSimpsonDistribution() {
        ArrayList<Double> tempNumbers = new ArrayList<>(n);
        tempNumbers.addAll(randomNumbers);

        for (int i = 0; i < n; ++i) {
            randomNumbers.add(i, tempNumbers.get((i * 2) % n) + tempNumbers.get((i * 2 + 1) % n));
        }

    }
}
