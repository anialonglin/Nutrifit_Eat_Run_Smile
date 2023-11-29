package MainUI;

import application.foodManager;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;

public class DietAlignmentPanel extends JPanel {
    public DietAlignmentPanel(String username) {
        setLayout(new BorderLayout());

        // Create a comparison chart
        JFreeChart chart = createRecommendedCart();

        //create chart from user data
        JFreeChart chart2 = createUserChart(username);

        // Create a chart panel and add it to the panel
        ChartPanel chartPanel = new ChartPanel(chart);
        ChartPanel chartPanel2 = new ChartPanel(chart2);
        add(chartPanel, BorderLayout.WEST);
        add(chartPanel2, BorderLayout.EAST);
        // resize chart panel to fit the window
        chartPanel.setPreferredSize(new Dimension(400, 400));
        chartPanel2.setPreferredSize(new Dimension(550, 400));
    }

    private JFreeChart createUserChart(String username) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        double[] percentages = foodManager.getAlignment(username);
        dataset.setValue("Protein", percentages[0]);
        dataset.setValue("Carbs", percentages[1]);
        dataset.setValue("Fruits and Veg", percentages[2]);
        dataset.setValue("Other/Unspecified", percentages[3]);
        System.out.println(percentages[0] + " " + percentages[1] + " " + percentages[2] + " " + percentages[3] + " hit");
        return ChartFactory.createPieChart("Your Nutrient Intake", dataset, true, true, false);
    }

    private JFreeChart createRecommendedCart() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Protein", 25);
        dataset.setValue("Carbs", 25);
        dataset.setValue("Fruits and Veg", 50);
        return ChartFactory.createPieChart("Recommended Nutrient Intake", dataset, true, true, false);
    }
}
