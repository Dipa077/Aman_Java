# Aman_Java
CAC
NAME: AMAN SINGH

REGISTRATION NO:23122105

CLASS: 3MSCDSB

`				`MACHINE LEARNING IN JAVA

**INTRODUCTION**


This Java program predicts future cricket player performance oy year 2025 using linear regression based on historical data(2008 - 2024). It reads performance data from a CSV file, cleans and processes it, and then uses the Apache Commons Math library to build regression models for each player. Predictions for the year 2025 are made and evaluated using R-squared values. The results, including past and predicted performances, are visualized using JFreeChart in a graphical user interface. This allows for easy comparison and analysis of player performance trends.

**DATASET DESCRIPTION**

The dataset contains detailed cricket player statistics, with each row representing a player's performance for a specific year. Key columns include Player\_Name, Matches\_Batted, Not\_Outs, and Runs\_Scored, providing insights into a player's batting performance. Additional batting metrics such as Highest\_Score, Batting\_Average, Balls\_Faced, and Batting\_Strike\_Rate are included. Bowling performance is captured in columns like Matches\_Bowled, Balls\_Bowled, Runs\_Conceded, and Wickets\_Taken, along with Bowling\_Average, Economy\_Rate, and Bowling\_Strike\_Rate. The dataset also records fielding stats, including Catches\_Taken and Stumpings. This comprehensive dataset enables thorough analysis and prediction of player performance.

**Data Import:** The CSV file containing player performance data is read and processed. The data is cleaned and structured into a format suitable for analysis.

![](Aspose.Words.18ac8287-45bf-4ed0-a399-cc79daf1c803.001.png)

**Regression Modeling:** A linear regression model is built for each player using their historical performance data. This model is used to predict future performance.

![](Aspose.Words.18ac8287-45bf-4ed0-a399-cc79daf1c803.002.png) ![](Aspose.Words.18ac8287-45bf-4ed0-a399-cc79daf1c803.003.png)

A linear regression model is built for each player using their historical performance data. This model is then used to predict future performance. For example, the program predicts the number of runs each player will score in 2025. The prediction accuracy is evaluated using R-squared values. Results, including past and predicted performances, are visualized in a bar chart using JFreeChart. This enables easy comparison and analysis of player performance trends. The accuracy of these predictions is evaluated using R-squared values. The results, encompassing both past and predicted performances, are visualized in a bar chart created with JFreeChart. This visualization allows for easy comparison and analysis of player performance trends over time, providing valuable insights for decision-making and performance improvement.

**Prediction:** The program predicts the runs each player is expected to score in the year 2025.

![](Aspose.Words.18ac8287-45bf-4ed0-a399-cc79daf1c803.004.png)

The program predicts the runs each player is expected to score in the year 2025 by building a linear regression model based on their historical performance data. Each player's model uses past data to forecast future runs. The accuracy of these predictions is evaluated using R-squared values, which measure how well the model fits the data. Results, including both past and predicted performances, are visualized in a bar chart created with JFreeChart. This visualization enables easy comparison and analysis of player performance trends, providing valuable insights for decision-making and performance improvement.

**Model Evaluation:** The program predicts the runs each player is expected to score in 2025 using a linear regression model built from their historical performance data. R-squared values are computed for each player’s model to assess its accuracy. These values indicate how well the model fits the historical data. The results, including past and predicted performances, are visualized in a bar chart using JFreeChart. This allows for easy comparison and analysis of performance trends, providing valuable insights for future performance expectations and decision-making.

![](Aspose.Words.18ac8287-45bf-4ed0-a399-cc79daf1c803.005.png)

**Output:**

![](Aspose.Words.18ac8287-45bf-4ed0-a399-cc79daf1c803.006.png)

**Visualization:**

**Bar Chart:** A bar chart is created to display the previous and predicted performance of each player, which is then shown in a GUI window. 

![](Aspose.Words.18ac8287-45bf-4ed0-a399-cc79daf1c803.007.png)


![](Aspose.Words.18ac8287-45bf-4ed0-a399-cc79daf1c803.008.png)

**Pie Chart:** Another visualization tool employed by the program is the pie chart, which illustrates the predicted runs for all players in the year 2025. This chart provides a comprehensive overview of the distribution of predicted runs among different players.

![](Aspose.Words.18ac8287-45bf-4ed0-a399-cc79daf1c803.009.png)

**Output:** 

![](Aspose.Words.18ac8287-45bf-4ed0-a399-cc79daf1c803.010.png)


**Histogram:** The program also generates a histogram depicting the distribution of predicted runs for 2025. This visualization helps in understanding the spread and frequency(No of Players) of predicted runs across all players, facilitating deeper insights into performance expectations.

![](Aspose.Words.18ac8287-45bf-4ed0-a399-cc79daf1c803.011.png)

**Output:** 

![](Aspose.Words.18ac8287-45bf-4ed0-a399-cc79daf1c803.012.png)


**Statistical Analysis:** 

In addition to visualization, the program conducts statistical analysis on the predicted runs for the year 2025. This analysis includes:

**Mean:** The program calculates the mean (average) of the predicted runs, providing a measure of central tendency for the predicted performance data.

![](Aspose.Words.18ac8287-45bf-4ed0-a399-cc79daf1c803.013.png)

**Median:** It computes the median of the predicted runs, which represents the middle value of the data set. The median is less sensitive to outliers compared to the mean and provides insight into the central tendency of the data.

![](Aspose.Words.18ac8287-45bf-4ed0-a399-cc79daf1c803.014.png)

**Standard Deviation:** The program calculates the standard deviation of the predicted runs, indicating the dispersion or variability of the data around the mean. A higher standard deviation suggests greater variability in predicted performance.

![](Aspose.Words.18ac8287-45bf-4ed0-a399-cc79daf1c803.015.png)

**Player with Highest and Lowest Predicted Runs:** The program identifies the player with the highest and lowest predicted runs for 2025, providing insights into extreme cases within the dataset.

![](Aspose.Words.18ac8287-45bf-4ed0-a399-cc79daf1c803.016.png)

**Output:** 

![](Aspose.Words.18ac8287-45bf-4ed0-a399-cc79daf1c803.017.png)

**LIBRARIES:** Libraries are used in as follows:

**Apache Commons Math:** **Provides the SimpleRegression class for performing linear regression analysis, enabling the creation of predictive models based on historical data.**

**JFreeChart:** Used for creating bar charts and visualizations with classes such as ChartFactory, ChartPanel, JFreeChart, and DefaultCategoryDataset to display performance data.

**Java Swing:** A GUI toolkit for Java that allows the creation of windows (JFrame) and panels (ChartPanel) for displaying charts and interactive elements.

**Java AWT:** The Abstract Window Toolkit provides layout managers like BorderLayout, which is used to arrange components within the window.

**Java IO:** Includes classes like BufferedReader and FileReader for reading data from CSV files, and IOException for handling input/output exceptions**.**

**Java Util:** Contains utility classes such as ArrayList, HashMap, List, and Map for storing and managing collections of player data, regression models, and predictions.

![](Aspose.Words.18ac8287-45bf-4ed0-a399-cc79daf1c803.018.png)

**CONCLUSION**

The program effectively demonstrates the use of linear regression for predicting cricket player performance based on historical data. By leveraging Apache Commons Math, it builds regression models to forecast future runs for each player. The accuracy of these predictions is assessed using R-squared values, ensuring the reliability of the models. JFreeChart is employed to visualize both historical and predicted performances in an intuitive bar chart format. Java Swing facilitates the creation of a user-friendly graphical interface for displaying these charts. Java AWT and Java IO handle layout management and data processing from CSV files, respectively. Utilizing Java Util, the program efficiently manages collections of data. This comprehensive approach not only provides valuable insights into player performance trends but also offers a robust tool for sports analysts, coaches, and enthusiasts to informed decisions. Overall, the program exemplifies the predictions.

Code:






