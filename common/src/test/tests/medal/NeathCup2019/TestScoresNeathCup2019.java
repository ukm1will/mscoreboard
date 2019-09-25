package tests.medal.NeathCup2019;

import data.medal.NeathCup2019;
import models.Competition;
import models.Golfer;
import org.junit.Assert;
import org.junit.Test;
import service.StringHelper;

import java.util.Collections;

import static enums.ScoringSystem.MEDAL;
import static junit.framework.TestCase.assertEquals;

public class TestScoresNeathCup2019 {

    private final String currentDataFile = NeathCup2019.WHOLE_PAGE;
    private Competition competition = new Competition(currentDataFile);

    @Test
    public void CompetitionShouldBeMedal() {
        assertEquals(MEDAL, competition.getScoringSystem());
    }

    @Test
    public void ShouldAddResultsToCompetition() {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, "Handicap\n", "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        assertEquals(26, competition.results.size());
    }

    @Test
    public void ShouldAddGolfersToCompetition() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, "Handicap\n", "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
    }

    @Test
    public void DerekEvansShouldHaveAGrossOf99() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, "Handicap\n", "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        Golfer derekEvans = competition.find("Derek Evans");
        assertEquals(99, derekEvans.getGross());
    }

    @Test
    public void ChrisSmithShouldHaveAGrossOf81() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, "Handicap\n", "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        Golfer chrisSmith = competition.find("Christopher Smith");
        assertEquals(81, chrisSmith.getGross());
    }


    @Test
    public void RichieNetScoreShouldBe76() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, "Handicap\n", "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        Golfer richieEvans = competition.find("R. A. Evans");
        assertEquals(77, richieEvans.getGross());
        assertEquals(76, richieEvans.getNett());
    }

    @Test
    public void BeforeSortingCheckNames() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, "Handicap\n", "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        Golfer posOne = competition.golfers.get(0);
        Golfer posTwo = competition.golfers.get(1);
        Assert.assertEquals("Euan Edwards", posOne.getFullName());
        Assert.assertEquals("Mike Edwards", posTwo.getFullName());
    }

    @Test
    public void AfterSortingCheckNames() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, "Handicap\n", "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        Collections.sort(competition.golfers);
        Golfer posOne = competition.golfers.get(0);
        Golfer posTwo = competition.golfers.get(1);
        Assert.assertEquals("R. A. Evans", posOne.getFullName());
        Assert.assertEquals("Jonathan R Bevan", posTwo.getFullName());
    }

}


