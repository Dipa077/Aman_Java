package example.com;

import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.statistics.HistogramDataset;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerPerformancePrediction {

    public static void main(String[] args) {
        // Load data from CSV
        List<PlayerData> playerDataList = loadDataFromCSV(
                "/Users/rajraman/Desktop/Codding/Sports/demo/src/main/resources/player_performance.csv");

        // Filter players with average runs scored greater than 200
        List<PlayerData> filteredPlayerDataList = filterPlayersByAverage(playerDataList, 200);

        // Perform linear regression for each player
        Map<String, SimpleRegression> regressionMap = new HashMap<>();
        for (PlayerData playerData : filteredPlayerDataList) {
            SimpleRegression regression = new SimpleRegression();
            for (int i = 0; i < playerData.getYears().size(); i++) {
                int year = playerData.getYears().get(i);
                double runs = playerData.getRunsScored().get(i);
                regression.addData(year, runs);
            }
            regressionMap.put(playerData.getPlayerName(), regression);
        }

        // Predict performance for each player for 2025
        int yearToPredict = 2025;
        Map<String, Double> predictedRunsMap = new HashMap<>();
        for (Map.Entry<String, SimpleRegression> entry : regressionMap.entrySet()) {
            String playerName = entry.getKey();
            SimpleRegression regression = entry.getValue();
            double predictedRun = Math.max(0, regression.predict(yearToPredict)) + 100;
            predictedRunsMap.put(playerName, predictedRun);
        }

        // Calculate R-squared for each player
        Map<String, Double> rSquaredMap = new HashMap<>();
        for (Map.Entry<String, SimpleRegression> entry : regressionMap.entrySet()) {
            String playerName = entry.getKey();
            SimpleRegression regression = entry.getValue();
            double rSquared = regression.getRSquare();
            rSquaredMap.put(playerName, rSquared);
        }

        // Calculate model R-squared
        double modelRSquared = calculateModelRSquared(rSquaredMap);

        // Calculate statistical analysis on predicted runs
        List<Double> predictedRunsList = new ArrayList<>(predictedRunsMap.values());
        double mean = predictedRunsList.stream().mapToDouble(Double::doubleValue).average().orElse(0);
        double median = calculateMedian(predictedRunsList);
        double stdDev = calculateStandardDeviation(predictedRunsList, mean);

        // Print the predicted runs and R-squared values for each player
        System.out.println("Predicted Runs for 2025:");
        for (Map.Entry<String, Double> entry : predictedRunsMap.entrySet()) {
            String playerName = entry.getKey();
            double predictedRuns = entry.getValue();
            System.out.println(playerName + ": " + predictedRuns);
        }
        String playerWithMaxRuns = null;
        String playerWithMinRuns = null;
        double maxRuns = Double.MIN_VALUE;
        double minRuns = Double.MAX_VALUE;
        System.out.println("\nR-squared values for each player's regression model:");
        for (Map.Entry<String, Double> entry : rSquaredMap.entrySet()) {
            String playerName = entry.getKey();
            double rSquared = entry.getValue();
            System.out.println(playerName + ": " + rSquared);
        }

        System.out.println("\nModel R-squared: " + modelRSquared);

        // Create a dataset for previous performance and predicted performance
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (PlayerData playerData : filteredPlayerDataList) {
            List<Integer> years = playerData.getYears();
            List<Double> runsScored = playerData.getRunsScored();
            String playerName = playerData.getPlayerName();
            for (int i = 0; i < years.size(); i++) {
                dataset.addValue(runsScored.get(i), "Previous Performance", playerName + " (" + years.get(i) + ")");
            }
        }

        for (Map.Entry<String, Double> entry : predictedRunsMap.entrySet()) {
            String playerName = entry.getKey();
            double predictedRuns = entry.getValue();
            dataset.addValue(predictedRuns, "Predicted Performance", playerName + " (" + yearToPredict + ")");
            if (predictedRuns > maxRuns) {
                maxRuns = predictedRuns;
                playerWithMaxRuns = playerName;
            }
            // Update minRuns and playerWithMinRuns
            if (predictedRuns < minRuns) {
                minRuns = predictedRuns;
                playerWithMinRuns = playerName;
            }
        }

        // Create a chart
        JFreeChart chart = ChartFactory.createBarChart(
                "Previous Performance vs Predicted Performance", // Chart title
                "Player", // X-axis label
                "Runs Scored", // Y-axis label
                dataset // Dataset
        );

        // Rotate the X-axis labels for better visibility
        chart.getCategoryPlot().getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        // Create and set up the window
        JFrame frame = new JFrame("Player Performance Prediction");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);

        // Add the chart to a panel and add the panel to the frame
        ChartPanel chartPanel = new ChartPanel(chart);
        frame.getContentPane().add(chartPanel, BorderLayout.CENTER);

        // Display the window
        frame.setVisible(true);
        // Create a dataset for predicted runs in 2025 for all players
        DefaultPieDataset pieDatasetAllPlayers = new DefaultPieDataset();
        for (Map.Entry<String, Double> entry : predictedRunsMap.entrySet()) {
            String playerName = entry.getKey();
            double predictedRuns = entry.getValue();
            pieDatasetAllPlayers.setValue(playerName, predictedRuns);
        }

        // Create a pie chart for predicted runs in 2025 for all players
        JFreeChart pieChartAllPlayers = ChartFactory.createPieChart(
                "Predicted Runs in 2025 for All Players", // Chart title
                pieDatasetAllPlayers, // Dataset
                true, // Include legend
                true,
                false);

        // Create and set up the window for the pie chart
        JFrame pieFrameAllPlayers = new JFrame("Predicted Runs in 2025 - Pie Chart for All Players");
        pieFrameAllPlayers.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pieFrameAllPlayers.setSize(800, 600);

        // Add the pie chart to a panel and add the panel to the frame
        ChartPanel pieChartPanelAllPlayers = new ChartPanel(pieChartAllPlayers);
        pieFrameAllPlayers.getContentPane().add(pieChartPanelAllPlayers, BorderLayout.CENTER);

        // Display the window for the pie chart
        pieFrameAllPlayers.setVisible(true);

        // Create a dataset for the histogram
HistogramDataset histogramDataset = new HistogramDataset();
double[] predictedRunsArray = predictedRunsList.stream().mapToDouble(Double::doubleValue).toArray();
histogramDataset.addSeries("Predicted Runs", predictedRunsArray, 10); // Adjust bin count as needed

// Create the histogram
JFreeChart histogramChart = ChartFactory.createHistogram(
        "Distribution of Predicted Runs in 2025", // Chart title
        "Predicted Runs", // X-axis label
        "Frequency", // Y-axis label
        histogramDataset // Dataset
);

// Create and set up the window for the histogram
JFrame histogramFrame = new JFrame("Predicted Runs in 2025 - Histogram");
histogramFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
histogramFrame.setSize(800, 600);

// Add the histogram to a panel and add the panel to the frame
ChartPanel histogramChartPanel = new ChartPanel(histogramChart);
histogramFrame.getContentPane().add(histogramChartPanel, BorderLayout.CENTER);

// Display the window for the histogram
histogramFrame.setVisible(true);




// more grahs




        System.out.println("\nStatistical Analysis of Predicted Runs for 2025:");
        System.out.println("Mean: " + mean);
        System.out.println("Median: " + median);
        System.out.println("Standard Deviation: " + stdDev);
        System.out.println(
                "\nPlayer with the Highest predicted runs in 2025: " + playerWithMaxRuns + " (" + maxRuns + ")");
        System.out
                .println("Player with the lowest predicted runs for 2025: " + playerWithMinRuns + " (" + minRuns + ")");
    }

    private static double calculateModelRSquared(Map<String, Double> rSquaredMap) {
        double sumRSquared = 0;
        for (double rSquared : rSquaredMap.values()) {
            sumRSquared += rSquared;
        }
        return sumRSquared / rSquaredMap.size();
    }

    private static List<PlayerData> loadDataFromCSV(String filePath) {
        List<PlayerData> playerDataList = new ArrayList<>();
        try (FileReader fileReader = new FileReader(filePath);
                BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            // Skip header
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                String playerName = parts[1];
                List<Integer> years = new ArrayList<>();
                List<Double> runsScored = new ArrayList<>();
                boolean containsInvalidData = false;
                for (int i = 2; i < parts.length; i++) {
                    if (i == 8 || i == 6 || i == 9 || i == 11 || i == 12 || i == 13 || i == 14 || i == 15 || i == 16
                            || i == 17 || i == 18 || i == 19 || i == 20 || i == 21 || i == 22 || i == 23 || i == 24
                            || i == 25) {
                        continue;
                    }
                    // Check if the value is "No stats" or contains invalid data
                    if (parts[i].equals("No stats") || parts[i].contains("/") || parts[i].equals("2024")) {
                        containsInvalidData = true;
                        break;
                    }

                    int year = 2008 + i - 2;
                    double runs;
                    if (parts[i].contains("*")) {
                        runs = Double.parseDouble(parts[i].substring(0, parts[i].indexOf("*")));
                    } else {
                        runs = Double.parseDouble(parts[i]);
                    }
                    if (runs >= 0) { // Skip if runs data is missing
                        years.add(year);
                        runsScored.add(runs);
                    }
                }
                // If invalid data is found, skip adding the player data
                if (!containsInvalidData) {
                    PlayerData playerData = new PlayerData(playerName, years, runsScored);
                    playerDataList.add(playerData);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return playerDataList;
    }

    private static List<PlayerData> filterPlayersByAverage(List<PlayerData> playerDataList, double threshold) {
        List<PlayerData> filteredPlayerDataList = new ArrayList<>();
        for (PlayerData playerData : playerDataList) {
            double averageRuns = playerData.getRunsScored().stream().mapToDouble(Double::doubleValue).average()
                    .orElse(0);
            if (averageRuns > threshold) {
                filteredPlayerDataList.add(playerData);
            }
        }
        return filteredPlayerDataList;
    }

    private static double calculateMedian(List<Double> list) {
        Collections.sort(list);
        int middle = list.size() / 2;
        if (list.size() % 2 == 0) {
            return (list.get(middle - 1) + list.get(middle)) / 2.0;
        } else {
            return list.get(middle);
        }
    }

    private static double calculateStandardDeviation(List<Double> list, double mean) {
        double variance = list.stream().mapToDouble(x -> Math.pow(x - mean, 2)).sum() / list.size();
        return Math.sqrt(variance);
    }
}

class PlayerData {
    private String playerName;
    private List<Integer> years;
    private List<Double> runsScored;

    public PlayerData(String playerName, List<Integer> years, List<Double> runsScored) {
        this.playerName = playerName;
        this.years = years;
        this.runsScored = runsScored;
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<Integer> getYears() {
        return years;
    }

    public List<Double> getRunsScored() {
        return runsScored;
    }
}
