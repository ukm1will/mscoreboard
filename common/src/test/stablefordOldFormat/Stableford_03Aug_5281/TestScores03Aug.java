package stablefordOldFormat.Stableford_03Aug_5281;

import data.stablefordOldFormat.Stableford_03_AUG_5281;
import models.Competition;
import models.Golfer;
import org.junit.Test;
import service.StringHelper;

import java.util.Collections;

import static enums.MasterScoreboardFormat.MS_OLD;
import static enums.ScoringSystem.STABLEFORD;
import static org.junit.Assert.assertEquals;

public class TestScores03Aug {

    private final String currentDataFile = Stableford_03_AUG_5281.WHOLE_PAGE;
    private Competition competition = new Competition(currentDataFile);
    private String beforePart;

    public TestScores03Aug() throws Exception {
        beforePart = StringHelper.getBeforePart(competition.getMasterScoreboardFormat());
    }

    @Test
    public void CompetitionShouldBeStableford() {
        assertEquals(STABLEFORD, competition.getScoringSystem());
    }

    @Test
    public void MasterScoreboardFormatShouldBeOld() {
        assertEquals(MS_OLD, competition.getMasterScoreboardFormat());
    }

    @Test
    public void ShouldAddResultsToCompetition() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, beforePart, "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        assertEquals(68, competition.results.size());
    }

    @Test
    public void ShouldAddGolfersToCompetition() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, beforePart, "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        assertEquals(68, competition.golfers.size());
    }

    @Test
    public void MikeShouldHaveCorrectData() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, beforePart, "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        Golfer mikeWilliams = competition.find("Mike Williams");
        assertEquals("Mike Williams", mikeWilliams.getFullName());
        assertEquals(76, mikeWilliams.getGross());
        assertEquals(71, mikeWilliams.getNett());
        assertEquals(5, mikeWilliams.getHandicap());
        assertEquals(37, mikeWilliams.getPts());
    }

    @Test
    public void ShouldShowDeanBeforeSort() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, beforePart, "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        Golfer deanMorris = competition.find("Dean Morris");
        assertEquals("Dean Morris", deanMorris.getFullName());
        assertEquals(2, deanMorris.getPosition());
        assertEquals(65, deanMorris.getGross());
        assertEquals(66, deanMorris.getNett());
        assertEquals(-1, deanMorris.getHandicap());
        assertEquals(42, deanMorris.getPts());
    }

    @Test
    public void ShouldShowDeansPositionAfterSort() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, beforePart, "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        Collections.sort(competition.golfers);
        competition.updateRankings();
        Golfer deanMorris = competition.find("Dean Morris");
        assertEquals("Dean Morris", deanMorris.getFullName());
        assertEquals(1, deanMorris.getPosition());
        assertEquals(65, deanMorris.getGross());
        assertEquals(66, deanMorris.getNett());
        assertEquals(-1, deanMorris.getHandicap());
        assertEquals(42, deanMorris.getPts());
    }

    @Test
    public void BeforeSortingCheckNames() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, beforePart, "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        Golfer posOne = competition.golfers.get(0);
        Golfer posTwo = competition.golfers.get(1);
        assertEquals("Brett Phippen", posOne.getFullName());
        assertEquals(1, posOne.getPosition());
        assertEquals("Dean Morris", posTwo.getFullName());
        assertEquals(2, posTwo.getPosition());

    }

    @Test
    public void AfterSortingCheckNames() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, beforePart, "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        Collections.sort(competition.golfers);
        competition.updateRankings();
        Golfer posOne = competition.golfers.get(0);
        Golfer posTwo = competition.golfers.get(1);
        assertEquals("Dean Morris", posOne.getFullName());
        assertEquals(1, posOne.getPosition());
        assertEquals("Brett Phippen", posTwo.getFullName());
        assertEquals(2, posTwo.getPosition());
    }
}
