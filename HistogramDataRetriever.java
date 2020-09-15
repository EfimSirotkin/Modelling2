package sample;

import java.util.ArrayList;
import java.util.Collections;

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
        frequenciesList.clear();

        double minValue = Collections.min(sourceList);
        double maxValue = Collections.max(sourceList);

        double startInterval = NumberGenerator.getMinScaledValue(minValue,1);
        double endInterval =  NumberGenerator.getMaxScaledValue(maxValue, 1);

        double step = (maxValue - minValue) / k;

        int tempFrequence = 0;
        for(double start = startInterval; start < endInterval; start+=step) {
            tempFrequence = 0;
            for (Double number : sourceList) {
                if (number > start && number < (start + step))
                    tempFrequence++;
            }
            frequenciesList.add(tempFrequence);
            tempFrequence = 0;
        }
    }
}
