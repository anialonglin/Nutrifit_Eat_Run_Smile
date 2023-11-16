package MainUI;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class DietAlignmentPanel extends JPanel {
    public DietAlignmentPanel(Map<String, Double> averagePlate, Map<String, Double> cfgRecommendations) {
        setLayout(new BorderLayout());

        // Create a dataset for the comparison chart
        CategoryDataset dataset = createDataset(averagePlate, cfgRecommendations);

        // Create a comparison chart
        JFreeChart chart = createComparisonChart(dataset);

        // Create a chart panel and add it to the panel
        ChartPanel chartPanel = new ChartPanel(chart);
        add(chartPanel, BorderLayout.CENTER);
    }

    private CategoryDataset createDataset(Map<String, Double> averagePlate, Map<String, Double> cfgRecommendations) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Add data for average plate
        for (Map.Entry<String, Double> entry : averagePlate.entrySet()) {
            dataset.addValue(entry.getValue(), "Average Plate", entry.getKey());
        }

        // Add data for CFG recommendations
        for (Map.Entry<String, Double> entry : cfgRecommendations.entrySet()) {
            dataset.addValue(entry.getValue(), "CFG Recommendations", entry.getKey());
        }

        return dataset;
    }

    private JFreeChart createComparisonChart(CategoryDataset dataset) {
        return ChartFactory.createBarChart(
                "Diet Alignment Comparison",
                "Food Group",
                "Percentage",
                dataset
        );
    }
}
