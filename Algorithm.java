package group18;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class Algorithm {
    public static double getPrediction(ArrayList<Integer> preferredData) {
        double[] dailyChanges = new double[29];
        DecimalFormat percentageFormat = new DecimalFormat("#.##");

        for (int e = 0; e < dailyChanges.length; e++) {
            if (preferredData.get(e) > preferredData.get(e + 1)) {
                dailyChanges[e] = -1 * ((double)(preferredData.get(e) - preferredData.get(e + 1))
                        / preferredData.get(e + 1)) * 100;
            } else {
                dailyChanges[e] = (double)(preferredData.get(e + 1) - preferredData.get(e)) /
                        preferredData.get(e) * 100;
            }
        }

        double totalChange = 0;
        for (double dailyChange : dailyChanges) {
            totalChange += dailyChange;
        }

        return Double.parseDouble(percentageFormat.format(totalChange / dailyChanges.length));
    }
}
