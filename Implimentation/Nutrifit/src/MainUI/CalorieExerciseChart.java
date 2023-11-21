package MainUI;

import dataAccess.HC_Old_user_data.profile;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.xy.*;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalorieExerciseChart extends JPanel {
    private profile user;
    private XYSeries calorieSeries;
    private XYSeries exerciseSeries;

    public CalorieExerciseChart(String title, profile user) {
        this.user = user;

        calorieSeries = new XYSeries("Calorie Intake");
        exerciseSeries = new XYSeries("Exercise");

        XYDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset, title);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        add(chartPanel);

        // Add sample data for testing
        addSampleData();
    }

    private XYDataset createDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(calorieSeries);
        dataset.addSeries(exerciseSeries);
        return dataset;
    }

    private JFreeChart createChart(XYDataset dataset, String title) {
        JFreeChart chart = ChartFactory.createXYBarChart(
                title,
                "Date",
                false,
                "Value",
                (IntervalXYDataset) dataset
        );

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);

        DateAxis domainAxis = new DateAxis("Date");
        domainAxis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM-dd"));
        domainAxis.setTickUnit(new DateTickUnit(DateTickUnitType.DAY, 1));
        plot.setDomainAxis(domainAxis);

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setTickUnit(new NumberTickUnit(500));

        XYBarRenderer renderer = new XYBarRenderer();
        plot.setRenderer(renderer);

        return chart;
    }

    public void addDataPoint(Date date, double calorieIntake, double exercise) {
        calorieSeries.add(date.getTime(), calorieIntake);
        exerciseSeries.add(date.getTime(), exercise);
    }

    // Method to add sample data for testing
    private void addSampleData() {
        // Add data for five consecutive days
        addDataPoint(new Date(1635763200000L), 2000, 30);
        addDataPoint(new Date(1635849600000L), 1800, 40);
        addDataPoint(new Date(1635936000000L), 2200, 20);
        addDataPoint(new Date(1636022400000L), 2100, 35);
        addDataPoint(new Date(1636108800000L), 1900, 25);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            profile testProfile = new profile("Test User", 25, true, 175.0, 70.0);

            CalorieExerciseChart chart = new CalorieExerciseChart("Calorie and Exercise Chart", testProfile);

            JFrame frame = new JFrame("CalorieExerciseChart Management");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.add(chart);
            frame.setVisible(true);
        });
    }
}
