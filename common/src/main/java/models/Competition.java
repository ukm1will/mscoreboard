package models;

import enums.ScoringSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Competition {

    public List<Golfer> golfers = new ArrayList<>();
    public List<String> results = new ArrayList<>();

    private ScoringSystem scoringSystem;


    public Competition(String currentDataFile) {
        this.setScoringSystem(currentDataFile);

    }

    public ScoringSystem getScoringSystem() {
        return scoringSystem;
    }

    private void setScoringSystem(String dataSource) {
        if (dataSource.contains("pts"))
            this.scoringSystem = ScoringSystem.STABLEFORD;
        else
            this.scoringSystem = ScoringSystem.MEDAL;
    }

    public void addResultsToCompetition(String str) {
        String[] nextSplit = str.split("\n");
        Collections.addAll(results, nextSplit);
    }

    public void addGolfersToCompetition() throws Exception {
        if (scoringSystem == ScoringSystem.STABLEFORD) {
            addStablefordGolfers();
        } else if (scoringSystem == ScoringSystem.MEDAL) {
            addMedalGolfers();
        } else
            throw new UnsupportedOperationException("Trouble at mill in Competition");
    }

    private void addMedalGolfers() {
        for (String result : results) {
            Golfer golfer = new MedalGolfer();
            golfer.split(result);
            if (firstCharOfStringIsDigit(result)) {
                golfer.assignAttributes();
                golfers.add(golfer);
            }
        }
    }

    private void addStablefordGolfers() throws Exception {
        for (String result : results) {
            Golfer golfer = new StablefordGolfer();
            golfer.split(result);
            if (firstCharOfStringIsDigit(result)) {
                golfer.assignAttributes();
                golfers.add(golfer);
            }
        }
    }

    private boolean firstCharOfStringIsDigit(String str) {
        return str.length() > 0 && Character.isDigit(str.charAt(0));
    }

    public Golfer find(String name) {
        for (Golfer golfer : this.golfers) {
            if (golfer.getFullName().equals(name)) {
                return golfer;
            }
        }
        return null;
    }

    public void updateRankings() {
        // After sorting, the position will no longer be accurate, therefore they need to be re-adjusted
        for (int i = 0; i < golfers.size(); i++) {
            golfers.get(i).setPosition(i + 1);
        }
    }
}