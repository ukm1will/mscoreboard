package medal.NeathCup_26May_4236;

import data.medal.NeathCup_26May_4236;
import models.Competition;
import models.Golfer;
import org.junit.Assert;
import org.junit.Test;
import service.StringHelper;
import service.ToString;

import java.util.Collections;
import java.util.List;

import static enums.ScoringSystem.MEDAL;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class TestScoresNeathCup2019 {

    private final String currentDataFile = NeathCup_26May_4236.WHOLE_PAGE;
    private Competition competition = new Competition(currentDataFile);
    private String beforePart;


    public TestScoresNeathCup2019() throws Exception {
        beforePart = StringHelper.getBeforePart(competition.getMasterScoreboardFormat());
    }

    @Test
    public void CompetitionShouldBeMedal() {
        assertEquals(MEDAL, competition.getScoringSystem());
    }

    @Test
    public void ShouldAddResultsToCompetition() {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, beforePart, "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        assertEquals(26, competition.results.size());
    }

    @Test
    public void ShouldAddGolfersToCompetition() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, beforePart, "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        assertEquals(20, competition.golfers.size());
    }

    @Test
    public void DerekEvansShouldHaveCorrectData() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, beforePart, "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        Golfer derekEvans = competition.find("Derek Evans");
        assertEquals(18, derekEvans.getPosition());
        assertEquals(99, derekEvans.getGross());
        assertEquals(87, derekEvans.getNett());
        assertEquals(12, derekEvans.getHandicap());

    }

    @Test
    public void ChristopherSmithShouldHaveCorrectData() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, beforePart, "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        Golfer chrisSmith = competition.find("Christopher Smith");
        assertEquals(9, chrisSmith.getPosition());
        assertEquals(81, chrisSmith.getGross());
        assertEquals(77, chrisSmith.getNett());
        assertEquals(4, chrisSmith.getHandicap());
    }

    @Test
    public void BeforeSortingCheckNames() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, beforePart, "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        Golfer posOne = competition.golfers.get(0);
        Golfer posTwo = competition.golfers.get(1);
        assertEquals("Euan Edwards", posOne.getFullName());
        assertEquals(1, posOne.getPosition());
        assertEquals("Mike Edwards", posTwo.getFullName());
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
        Assert.assertEquals("R. A. Evans", posOne.getFullName());
        assertEquals(1, posOne.getPosition());
        Assert.assertEquals("Jonathan R Bevan", posTwo.getFullName());
        assertEquals(2, posTwo.getPosition());
    }
}
