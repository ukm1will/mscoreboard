package tests.stableford.Stableford07Sept;

import data.stableford.Stableford_07_SEP;
import models.Competition;
import models.Golfer;
import models.StablefordGolfer;
import org.junit.Test;
import service.StringHelper;

import java.util.Collections;

import static enums.ScoringSystem.STABLEFORD;
import static org.junit.Assert.assertEquals;

public class TestScores07Sept {

    private final String currentDataFile = Stableford_07_SEP.WHOLE_PAGE;
    private Competition competition = new Competition(currentDataFile);

    @Test
    public void CompetitionShouldBeMedal() {
        assertEquals(STABLEFORD, competition.getScoringSystem());
    }

    @Test
    public void ShouldAddResultsToCompetition() {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, "Handicap\n", "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        assertEquals(57, competition.results.size());
    }

    @Test
    public void ShouldAddGolfersToCompetition() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, "Handicap\n", "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        assertEquals(52, competition.golfers.size());
    }

    @Test
    public void MikeShouldHaveCorrectData() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, "Handicap\n", "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        StablefordGolfer mikeWilliams = (StablefordGolfer) competition.find("Mike Williams");
        assertEquals("Mike Williams", mikeWilliams.getFullName());
        assertEquals(75, mikeWilliams.getGross());
        assertEquals(70, mikeWilliams.getNett());
        assertEquals(5, mikeWilliams.getHandicap());
        assertEquals(38, mikeWilliams.getPts());
    }


    @Test
    public void ShouldShowTomBeforeSort() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, "Handicap\n", "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        StablefordGolfer tomTrippett = (StablefordGolfer) competition.find("Tom Trippett");
        assertEquals("Tom Trippett", tomTrippett.getFullName());
        assertEquals(27, tomTrippett.getPosition());
        assertEquals(81, tomTrippett.getGross());
        assertEquals(76, tomTrippett.getNett());
        assertEquals(5, tomTrippett.getHandicap());
        assertEquals(32, tomTrippett.getPts());
    }

    @Test
    public void ShouldShowTomsPositionAfterSort() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, "Handicap\n", "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        Collections.sort(competition.golfers);
        competition.updateRankings();
        StablefordGolfer tomTrippett = (StablefordGolfer) competition.find("Tom Trippett");
        assertEquals("Tom Trippett", tomTrippett.getFullName());
        assertEquals(13, tomTrippett.getPosition());
        assertEquals(81, tomTrippett.getGross());
        assertEquals(76, tomTrippett.getNett());
        assertEquals(5, tomTrippett.getHandicap());
        assertEquals(32, tomTrippett.getPts());
    }

    @Test
    public void BeforeSortingCheckNames() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, "Handicap\n", "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        Golfer posOne = competition.golfers.get(0);
        Golfer posTwo = competition.golfers.get(1);
        assertEquals("Lance L. Heycock", posOne.getFullName());
        assertEquals("Gareth J Davies", posTwo.getFullName());
    }

    @Test
    public void AfterSortingCheckNames() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, "Handicap\n", "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        Collections.sort(competition.golfers);
        Golfer posOne = competition.golfers.get(0);
        Golfer posTwo = competition.golfers.get(1);
        assertEquals("James Graham", posOne.getFullName());
        assertEquals("Mike Williams", posTwo.getFullName());
    }

}
