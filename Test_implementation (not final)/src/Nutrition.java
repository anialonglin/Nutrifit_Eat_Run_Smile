import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Nutrient {
    private double proteins;
    private double carbs;
    private double vitamins;

    public Nutrient(double proteins, double carbs, double vitamins) {
        this.proteins = proteins;
        this.carbs = carbs;
        this.vitamins = vitamins;
    }

    public double getProteins() {
        return proteins;
    }

    public double getCarbs() {
        return carbs;
    }

    public double getVitamins() {
        return vitamins;
    }
}

public class Nutrition {
    private String mealType;
    private Map<String, Nutrient> ingredients;

    public Nutrition(String mealType) {
        this.mealType = mealType;
        this.ingredients = new HashMap<>();
    }

    public void addIngredient(String name, Nutrient nutrient) {
        ingredients.put(name, nutrient);
    }

    public Nutrient getTotalNutrients() {
        double totalProteins = 0;
        double totalCarbs = 0;
        double totalVitamins = 0;

        for (Nutrient nutrient : ingredients.values()) {
            totalProteins += nutrient.getProteins();
            totalCarbs += nutrient.getCarbs();
            totalVitamins += nutrient.getVitamins();
        }

        return new Nutrient(totalProteins, totalCarbs, totalVitamins);
    }

    public String getMealType() {
        return mealType;
    }
}

 class NutritionDataPlotter {

    public static void plotNutritionData(ArrayList<Nutrition> nutritionEntries) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Nutrition nutrition : nutritionEntries) {
            Nutrient totalNutrients = nutrition.getTotalNutrients();
            dataset.addValue(totalNutrients.getProteins(), "Proteins", nutrition.getMealType());
            dataset.addValue(totalNutrients.getCarbs(), "Carbs", nutrition.getMealType());
            dataset.addValue(totalNutrients.getVitamins(), "Vitamins", nutrition.getMealType());
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Nutrition Data",
                "Meal Type",
                "Amount",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 400));

        JFrame frame = new JFrame("Nutrition Data Plot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Create a list of nutrition entries
        ArrayList<Nutrition> nutritionEntries = new ArrayList<>();

        // Create and add nutrition entries
        Nutrition breakfast = new Nutrition("Breakfast");
        breakfast.addIngredient("Eggs", new Nutrient(10.5, 25.3, 7.2));
        breakfast.addIngredient("Bread", new Nutrient(5.2, 15.1, 3.0));
        nutritionEntries.add(breakfast);

        Nutrition lunch = new Nutrition("Lunch");
        lunch.addIngredient("Chicken", new Nutrient(7.0, 20.5, 5.5));
        nutritionEntries.add(lunch);

        // Plot bar graph
        plotNutritionData(nutritionEntries);
    }
}
