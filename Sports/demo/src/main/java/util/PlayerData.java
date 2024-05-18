package util;

public class PlayerData {
    private int matchesBatted;
    private double battingAverage;
    private double battingStrikeRate;
    private int runsScored;

    public PlayerData(int matchesBatted, double battingAverage, double battingStrikeRate, int runsScored) {
        this.matchesBatted = matchesBatted;
        this.battingAverage = battingAverage;
        this.battingStrikeRate = battingStrikeRate;
        this.runsScored = runsScored;
    }

    public int getMatchesBatted() {
        return matchesBatted;
    }

    public void setMatchesBatted(int matchesBatted) {
        this.matchesBatted = matchesBatted;
    }

    public double getBattingAverage() {
        return battingAverage;
    }

    public void setBattingAverage(double battingAverage) {
        this.battingAverage = battingAverage;
    }

    public double getBattingStrikeRate() {
        return battingStrikeRate;
    }

    public void setBattingStrikeRate(double battingStrikeRate) {
        this.battingStrikeRate = battingStrikeRate;
    }

    public int getRunsScored() {
        return runsScored;
    }

    public void setRunsScored(int runsScored) {
        this.runsScored = runsScored;
    }
}
