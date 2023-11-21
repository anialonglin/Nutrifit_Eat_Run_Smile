package MainUI;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class NutrientIntakeChart extends JPanel {
    private final ChartPanel chartPanel;
    private final DefaultPieDataset dataset;

    public NutrientIntakeChart(String title) {
        this.dataset = createDataset();  // Initialize the dataset

        JFreeChart chart = createChart(title);
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        add(chartPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Nutrient Intake Chart Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 300);

            NutrientIntakeChart nutrientIntakeChart = new NutrientIntakeChart("Nutrient Intake Chart");

            // Dummy data for testing
            Map<String, Double> dummyData = new HashMap<>();
            dummyData.put("Proteins", 50.0);
            dummyData.put("Carbs", 120.0);
            dummyData.put("Fats", 30.0);

            nutrientIntakeChart.updateNutrientData(dummyData);

            frame.add(nutrientIntakeChart);
            frame.setVisible(true);
        });
    }

    private JFreeChart createChart(String title) {
        return ChartFactory.createPieChart(title, dataset, true, true, false);
    }

    private DefaultPieDataset createDataset() {
        return new DefaultPieDataset();
    }

    public void updateNutrientData(Map<String, Double> nutrientData) {
        // Clear existing data
        dataset.clear();

        // Add new data
        for (Map.Entry<String, Double> entry : nutrientData.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());
        }

        // Notify the chart that the dataset has changed
        ((PiePlot) chartPanel.getChart().getPlot()).setDataset(dataset);
    }
}
