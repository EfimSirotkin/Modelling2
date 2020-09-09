package sample;

import java.util.ArrayList;

public class HistogramDataRetriever {
    public static int k = 20;

    private ArrayList<Integer> frequenciesList;

    public HistogramDataRetriever()
    {
        frequenciesList = new ArrayList<>(k);
    }

    public ArrayList<Integer> getFrequenciesList() {
        return frequenciesList;
    }

    public void calculateFrequencies(ArrayList<Double> sourceList)
    {
        int tempFrequence = 0;
        double step = (double) 1 / k;
        for(double startInterval = 0; startInterval < 1; startInterval+=step) {
            for (Double number : sourceList) {
                if (number > startInterval && number < (startInterval + step))
                    tempFrequence++;
            }
            frequenciesList.add(tempFrequence);
            tempFrequence = 0;
        }
    }
}
