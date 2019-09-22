package medal.NeathCup2019;

import data.medal.NeathCup2019;
import models.Competition;
import models.Golfer;
import org.junit.Before;
import org.junit.Test;
import service.StringHelper;

import static enums.ScoringSystem.MEDAL;
import static junit.framework.TestCase.assertEquals;

public class TestScoresNeathCup2019 {

    final String currentDataFile = NeathCup2019.WHOLE_PAGE;
    Competition competition;

    @Before
    public void doBefore() {
        competition = new Competition(currentDataFile);
        assertEquals(MEDAL, competition.getScoringSystem());
    }

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
}


