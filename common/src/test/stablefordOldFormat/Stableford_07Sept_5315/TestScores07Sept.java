package stablefordOldFormat.Stableford_07Sept_5315;

import data.stablefordOldFormat.Stableford_07_SEP_5315;
import models.Competition;
import models.Golfer;
import org.junit.Test;
import service.StringHelper;

import java.util.Collections;

import static enums.ScoringSystem.STABLEFORD;
import static org.junit.Assert.assertEquals;

public class TestScores07Sept {

    private final String currentDataFile = Stableford_07_SEP_5315.WHOLE_PAGE;
    private Competition competition = new Competition(currentDataFile);
    private String beforePart;

    public TestScores07Sept() throws Exception {
        beforePart = StringHelper.getBeforePart(competition.getMasterScoreboardFormat());
    }

    @Test
    public void CompetitionShouldBeStableford() {
        assertEquals(STABLEFORD, competition.getScoringSystem());
    }

    @Test
    public void ShouldAddResultsToCompetition() {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, beforePart, "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        assertEquals(57, competition.results.size());
    }

    @Test
    public void ShouldAddGolfersToCompetition() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, beforePart, "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        assertEquals(52, competition.golfers.size());
    }

    @Test
    public void MikeShouldHaveCorrectData() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, beforePart, "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        Golfer mikeWilliams = competition.find("Mike Williams");
        assertEquals("Mike Williams", mikeWilliams.getFullName());
        assertEquals(75, mikeWilliams.getGross());
        assertEquals(70, mikeWilliams.getNett());
        assertEquals(5, mikeWilliams.getHandicap());
        assertEquals(38, mikeWilliams.getPts());
    }


    @Test
    public void ShouldShowTomBeforeSort() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, beforePart, "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        Golfer tomTrippett = competition.find("Tom Trippett");
        assertEquals("Tom Trippett", tomTrippett.getFullName());
        assertEquals(27, tomTrippett.getPosition());
        assertEquals(81, tomTrippett.getGross());
        assertEquals(76, tomTrippett.getNett());
        assertEquals(5, tomTrippett.getHandicap());
        assertEquals(32, tomTrippett.getPts());
    }

    @Test
    public void ShouldShowTomsPositionAfterSort() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, beforePart, "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        Collections.sort(competition.golfers);
        competition.updateRankings();
        Golfer tomTrippett = competition.find("Tom Trippett");
        assertEquals("Tom Trippett", tomTrippett.getFullName());
        assertEquals(13, tomTrippett.getPosition());
        assertEquals(81, tomTrippett.getGross());
        assertEquals(76, tomTrippett.getNett());
        assertEquals(5, tomTrippett.getHandicap());
        assertEquals(32, tomTrippett.getPts());
    }

    @Test
    public void BeforeSortingLanceIsFirst() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, beforePart, "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        Golfer posOne = competition.golfers.get(0);
        Golfer posTwo = competition.golfers.get(1);
        assertEquals("Lance L. Heycock", posOne.getFullName());
        assertEquals(1, posOne.getPosition());
        assertEquals("Gareth J Davies", posTwo.getFullName());
        assertEquals(2, posTwo.getPosition());
    }

    @Test
    public void AfterSortingJamesIsFirst() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, beforePart, "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        Collections.sort(competition.golfers);
        competition.updateRankings();
        Golfer posOne = competition.golfers.get(0);
        Golfer posTwo = competition.golfers.get(1);
        assertEquals("James Graham", posOne.getFullName());
        assertEquals(1, posOne.getPosition());
        assertEquals("Mike Williams", posTwo.getFullName());
        assertEquals(2, posTwo.getPosition());
    }
}
