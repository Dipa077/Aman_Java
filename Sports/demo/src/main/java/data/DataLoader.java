package data;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import util.PlayerData;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {

    public static List<PlayerData> loadPlayerData(String filePath) {
        List<PlayerData> playerDataList = new ArrayList<>();

        try (InputStream inputStream = DataLoader.class.getClassLoader().getResourceAsStream(filePath);
             InputStreamReader reader = new InputStreamReader(inputStream);
             CSVParser csvParser = CSVParser.parse(reader, CSVFormat.RFC4180.withFirstRecordAsHeader())) {

            for (CSVRecord record : csvParser) {
                try {
                    // Parse numeric fields
                    int matchesBatted = Integer.parseInt(record.get("Matches_Batted"));
                    double battingAverage = Double.parseDouble(record.get("Batting_Average"));
                    double battingStrikeRate = Double.parseDouble(record.get("Batting_Strike_Rate"));
                    int runsScored = Integer.parseInt(record.get("Runs_Scored"));
                    // Add other numeric fields as needed

                    // Create PlayerData object and add to list
                    PlayerData playerData = new PlayerData(matchesBatted, battingAverage, battingStrikeRate, runsScored);
                    playerDataList.add(playerData);
                } catch (NumberFormatException e) {
                    // Handle non-numeric fields (like player names)
                    System.err.println("Skipping non-numeric record: " + record.toString());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred while loading player data");
        }

        return playerDataList;
    }
}
