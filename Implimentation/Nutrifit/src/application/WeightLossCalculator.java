package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WeightLossCalculator {
    public double calculateWeightLoss(double calorieIntake, double exerciseBurn, Date futureDate) {
        // Calculate the net calorie deficit
        double netCalorieDeficit = calorieIntake - exerciseBurn;

        // Calculate the weight loss in kg (1kg of fat=7,700kcal)
        double weightLoss = netCalorieDeficit / 7700;

        // Calculate the number of days until the future date
        long daysUntilFutureDate = daysBetween(new Date(), futureDate);

        // Adjust weight loss based on the number of days
        return weightLoss * daysUntilFutureDate;
    }

    private long daysBetween(Date startDate, Date endDate) {
        long diff = endDate.getTime() - startDate.getTime();
        return diff / (24 * 60 * 60 * 1000);
    }
}

