package MainUI;

import application.foodManager;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class NutrientIntakeChart extends JPanel {
    ;
    private static String username;

    public NutrientIntakeChart(String username) {
            setLayout(new BorderLayout());

            // Create a comparison chart
        JFreeChart chart = null;
        try {
            chart = create5DayCart(username);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        //create chart from user data
        JFreeChart chart2 = null;
        try {
            chart2 = create10DayCart(username);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        // Create a chart panel and add it to the panel
            ChartPanel chartPanel = new ChartPanel(chart);
            ChartPanel chartPanel2 = new ChartPanel(chart2);
            add(chartPanel, BorderLayout.WEST);
            add(chartPanel2, BorderLayout.EAST);
            // resize chart panel to fit the window
            chartPanel.setPreferredSize(new Dimension(400, 400));
            chartPanel2.setPreferredSize(new Dimension(400, 400));
    }

    private JFreeChart create10DayCart(String username) throws ParseException {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Protein", foodManager.getAlignment(username, 10)[0]);
        dataset.setValue("Carbs", foodManager.getAlignment(username, 10)[1]);
        dataset.setValue("Fruits and Veg", foodManager.getAlignment(username, 10)[2]);
        dataset.setValue("Other/Unspecified", foodManager.getAlignment(username, 10)[3]);
        return ChartFactory.createPieChart("Your Nutrient Intake", dataset, true, true, false);
    }

    private JFreeChart create5DayCart(String username) throws ParseException {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Protein", foodManager.getAlignment(username, 5)[0]);
        dataset.setValue("Carbs", foodManager.getAlignment(username, 5)[1]);
        dataset.setValue("Fruits and Veg", foodManager.getAlignment(username, 5)[2]);
        dataset.setValue("Other/Unspecified", foodManager.getAlignment(username, 5)[3]);
        return ChartFactory.createPieChart("Your Nutrient Intake", dataset, true, true, false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Nutrient Intake Chart Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 300);
            frame.add(new NutrientIntakeChart(username));
            frame.setVisible(true);
        });
    }


}
